package com.example.demo.controller;

import com.example.demo.util.BarCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author elvis
 * @Date 2021/7/2 16:03
 */
@RestController
@RequestMapping("/barCode")
public class BarCodeController {

    @GetMapping("/getBarCode")
    public void getBarCode(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        BufferedImage image = BarCodeUtil.insertWords(BarCodeUtil.getBarCode(code), code);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
