package com.example.demo.util;


import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @Date 2020/7/30 16:03
 * @Author hezhan
 * 文件类型转换工具类
 */
@Component
public class ConversionUtil {

    @Value("${image.url}")
    private String imagePath;

    public String convertToHtml(InputStream inputStream, String fileName) throws Exception {
        String substring = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if ("docx".equals(substring)){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
            XHTMLOptions options = XHTMLOptions.create();
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);
            ImageManager imageManager = new ImageManager(new File(imagePath), "image");
            options.setImageManager(imageManager);
            XHTMLConverter.getInstance().convert(document, out, options);
            return out.toString();
        } else if ("doc".equals(substring)){
            HWPFDocument wordDocument = new HWPFDocument(inputStream);
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            wordToHtmlConverter.processDocument(wordDocument);
            Document htmlDocument = wordToHtmlConverter.getDocument();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer serializer = factory.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            outStream.close();
            return outStream.toString("UTF-8");
        }
        return null;
    }
}
