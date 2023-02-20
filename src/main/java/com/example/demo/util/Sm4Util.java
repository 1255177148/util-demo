package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <简述>sm4加密、解密与加密结果验证
 * <详细描述>
 * @author   gelongyu
 * @version  $Id$
 * @since
 * @see
 */
public class Sm4Util {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    /**
     * @Fields LOGGER : 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Sm4Util.class);
    
    public static final String sKey = "6d476239336e624a6966383135346441";
    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    private static final String DBKey = "b6vhedgjior655gj";//"b6vhedgjior655gj";
    private static final String jksKey = "ergthghrjkwtw55h";
    private static final String jkDBKey = "b6vhedgjior655gj";
    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;
    
    /**
     * 生成ECB暗号
     * @explain ECB模式（电子密码本模式：Electronic codebook）
     * @param algorithmName
     *            算法名称
     * @param mode
     *            模式
     * @param key
     * @return
     * @throws Exception
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }
    //方法1======================================
     /* 自动生成密钥
     * @explain
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static byte[] generateKey() throws Exception {
        return generateKey(DEFAULT_KEY_SIZE);
    }
    
    /** 
     * @explain
     * @param keySize
     * @return
     * @throws Exception
     */
    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }
    //方法1======================================结束
    
    //方法二：自己提供16进制的密钥==================================


     /* sm4加密
      *    方法二：自己提供16进制的密钥
     * @explain 加密模式：ECB
     *          密文长度不固定，会随着被加密字符串长度的变化而变化
     * @param hexKey
     *            16进制密钥（忽略大小写）
     * @param paramStr
     *            待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     */
    public static String encryptEcb(String hexKey, String paramStr) throws Exception {
//        String cipherText = "";
        // 16进制字符串--&gt;byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String--&gt;byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
        // byte[]--&gt;hexString
//        cipherText = ByteUtils.toHexString(cipherArray);
        return ByteUtils.toHexString(cipherArray);
    }

    public static String encryptEcb2(String hexKey, String paramStr) throws Exception {
//        String cipherText = "";
        // 16进制字符串--&gt;byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String--&gt;byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
//        System.out.println(cipherArray.length);
//        long startTime = System.currentTimeMillis();
        // byte[]--&gt;hexString
//        cipherText = getString(cipherArray);
//        cipherText = byteArrToHex(cipherArray);
//        System.out.println(String.valueOf((System.currentTimeMillis()-startTime)/1000));
        return byteArrToHex(cipherArray);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String byteArrToHex(byte... bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String getString(byte[] var1){
        String originalString = new BASE64Encoder().encode(var1);
        originalString = originalString.replaceAll("\n", "");
        originalString = originalString.replaceAll("\r", "");
        return originalString;
    }
    /**
     * 加密模式之Ecb
     * @explain
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
    
    //方法二：自己提供16进制的密钥==================================结束
    
    
    //SM4解密=================================================
    
    /* sm4解密
    * @explain 解密模式：采用ECB
    * @param hexKey
    *            16进制密钥
    * @param cipherText
    *            16进制的加密字符串（忽略大小写）
    * @return 解密后的字符串
    * @throws Exception
    */
    public static String decryptEcb(String cipherText)  {
        String decryptStr = "";
        try {
            // 用于接收解密后的字符串
            // hexString--&gt;byte[]
            byte[] keyData = ByteUtils.fromHexString(sKey);
            // hexString--&gt;byte[]
            byte[] cipherData = ByteUtils.fromHexString(cipherText);
            // 解密
            byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
            // byte[]--&gt;String
            decryptStr = new String(srcData, ENCODING);
        }catch (Exception e) {
            log.info(e.toString());
        }

        return decryptStr;
    }
        
    /**
     * 解密
     * @explain
     * @param key
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }
    public static Map<String, Object>  getParams(String params){
        Map<String, Object> info = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(params)) {
                String strValue = Sm4Util.decryptEcb(params);
                info = JSONObject.parseObject(strValue, Map.class);
            } 
        }catch(Exception e) {
            
        }
        return info;
    }
    
    
    
    /**
     *〈简述〉解析 前端参数 params  用于 get请求
     *〈详细描述〉
     * @author gelongyu
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object>  getParamsForGet(String params){
        Map<String, Object> info = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(params)) {
            String strValue = Sm4Util.decryptEcb(params);
            info = JSONObject.parseObject(strValue, Map.class);
            if (!info.isEmpty()) {
                for (Map.Entry<String, Object> entry : info.entrySet()) {
                    String mapKey = entry.getKey();
                    Object mapValue = entry.getValue();
                    if (mapValue != null && 
                            (mapValue.toString().contains("%") ||mapValue.toString().contains("_") || mapValue.toString().contains("\\"))) {
//                        String str = StringUtils.replace(mapValue.toString(), "_", "\\_");
//                        str = StringUtils.replace(str, "\\", "\\\\");
                        String[] strs=mapValue.toString().split("");
                        String str= "";
                        for(String s:strs){
                         if(s.equals("_")){
                            str+="\\"+s;
                        }else if(s.equals("\\")){
                            str+="\\"+s;
                        } else  if(s.equals("%")){
                            str+="\\"+s;
                        }  
                        
                        else{
                            str+=s;
                        }
                        info.put(mapKey, str.toString());
                        }
                    }
                }
                
            }
        } 

        return info;
    }
    public static String decryptForJKWeChat(String sSrc)  {//解密

        if (jksKey == null) {
            log.info("Key为空null");
            return null;
        }
        if (jksKey.length() != 16) {
            log.info("Key的长度不是16位");
            return null;
        }
        return  decrypt(jksKey, sSrc);
    }
    public static Map<String, Object>  getParamsForGetForWeChat(String params){
        Map<String, Object> info = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(params)) {
            String strValue = Sm4Util.decryptForJKWeChat(params);
            info = JSONObject.parseObject(strValue, Map.class);
            if (!info.isEmpty()) {
                for (Map.Entry<String, Object> entry : info.entrySet()) {
                    String mapKey = entry.getKey();
                    Object mapValue = entry.getValue();
                    if (mapValue != null &&
                            (mapValue.toString().contains("%") ||mapValue.toString().contains("_") || mapValue.toString().contains("\\"))) {
//                        String str = StringUtils.replace(mapValue.toString(), "_", "\\_");
//                        str = StringUtils.replace(str, "\\", "\\\\");
                        String[] strs=mapValue.toString().split("");
                        String str= "";
                        for(String s:strs){
                            if(s.equals("_")){
                                str+="\\"+s;
                            }else if(s.equals("\\")){
                                str+="\\"+s;
                            } else  if(s.equals("%")){
                                str+="\\"+s;
                            }

                            else{
                                str+=s;
                            }
                            info.put(mapKey, str.toString());
                        }
                    }
                }

            }
        }
        return info;
    }
    /**
     * key,作为参数传递
     * @param sKey
     * @param sSrc
     * @return
     * @throws Exception
     */
    public static String encrypt(String sKey,String sSrc) {  //加密
        String cipherText = "";
        try {
            sKey = Sm4Util.strTo16(sKey);
            // 16进制字符串--&gt;byte[]
            byte[] keyData = ByteUtils.fromHexString(sKey);
            // String--&gt;byte[]
            byte[] srcData = sSrc.getBytes(ENCODING);
            // 加密后的数组
            byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
            // byte[]--&gt;hexString
            cipherText = ByteUtils.toHexString(cipherArray);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return cipherText;
    }  
    public static String encryptDB(String sSrc)  {  
        if (DBKey == null) {  
            log.info("Key为空null");
            return null;  
        }  
        if (DBKey.length() != 16) {//判断key是否为16位  
            log.info("Key的长度不是16位");
            return null;  
        }  
        return  encrypt(DBKey, sSrc);
    }
    /**
     * key,作为参数传递  解码
     * @param sKey
     * @param sSrc
     * @return
     */
    public static String decrypt(String sKey,String sSrc)  {//解密 
        sKey = Sm4Util.strTo16(sKey);
        String decryptStr = "";
        try {
            // 用于接收解密后的字符串
            // hexString--&gt;byte[]
            byte[] keyData = ByteUtils.fromHexString(sKey);
            // hexString--&gt;byte[]
            byte[] cipherData = ByteUtils.fromHexString(sSrc);
            // 解密
            byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
            // byte[]--&gt;String
            decryptStr = new String(srcData, ENCODING);
        }catch (Exception e) {
            log.info(e.toString());
        }
        return decryptStr;
    } 
    public static String decryptdb(String sSrc)  {//解密  
        if (DBKey == null) {
            log.info("Key为空null");
            return null;  
        }  
        if (DBKey.length() != 16) {
            log.info("Key的长度不是16位");
            return null;  
        }  
        return  decrypt(DBKey, sSrc);
}
    
    public static String encryptForJKWeChat(String sSrc) throws Exception {
        if (jksKey == null) {
            log.info("Key为空null");
            return null;
        }
        if (jksKey.length() != 16) {//判断key是否为16位
            log.info("Key的长度不是16位");
            return null;
        }
        return  encrypt(jksKey, sSrc);//"mGb93nbJif8154dA"
    }
    /* 校验加密前后的字符串是否为同一数据
    * @explain
    * @param hexKey
    *            16进制密钥（忽略大小写）
    * @param cipherText
    *            16进制加密后的字符串
    * @param paramStr
    *            加密前的字符串
    * @return 是否为同一数据
    * @throws Exception
    */
   public static boolean verifyEcb(String cipherText, String paramStr) throws Exception {
       // 用于接收校验结果
       boolean flag = false;
       // hexString--&gt;byte[]
       byte[] keyData = ByteUtils.fromHexString(sKey);
       // 将16进制字符串转换成数组
       byte[] cipherData = ByteUtils.fromHexString(cipherText);
       // 解密
       byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
       // 将原字符串转换成byte[]
       byte[] srcData = paramStr.getBytes(ENCODING);
       // 判断2个数组是否一致
       flag = Arrays.equals(decryptData, srcData);
       return flag;
   }
   
   public static String handleCardid(String cardid,Object idtype) {
       if  (StringUtils.isBlank(cardid)) {
           return "";
       }
       if("01".equals(idtype) && cardid.length()>=4) {
           return StringUtils.isNotBlank(cardid)?cardid.substring(0,4)+"****"+ cardid.substring(cardid.length()-4,cardid.length()):null;
       }else {
           if(StringUtils.isNotBlank(cardid)) {
               if(cardid.length()<4) {
                   return "******";
               }else {
                   return cardid.substring(0,2)+"******"+ cardid.substring(cardid.length()-2,cardid.length());
               }
           }else {
               return null;
           }
           
       }
       
       
   } 
   public static String decrypt32(String sKey,String sSrc)  {//解密 
//     sKey = Sm4Util.strTo16(sKey);
     String decryptStr = "";
     try {
         // 用于接收解密后的字符串
         // hexString--&gt;byte[]
         byte[] keyData = ByteUtils.fromHexString(sKey);
         // hexString--&gt;byte[]
         byte[] cipherData = ByteUtils.fromHexString(sSrc);
         // 解密
         byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
         // byte[]--&gt;String
         decryptStr = new String(srcData, ENCODING);
     }catch (Exception e) {
         log.info(e.toString());
     }
     return decryptStr;
 } 
   
   public static Map<String, Object>  getParamsForWeChat(String params){
       Map<String, Object> info = new HashMap<String, Object>();
       if (StringUtils.isNotBlank(params)) {
           String strValue = Sm4Util.decryptForJKWeChat(params);
           info = JSONObject.parseObject(strValue, Map.class);
       }
       return info;
   }
   
   /**
    * 字符串转化成为16进制字符串
    * @param s
    * @return
    */
   public static String strTo16(String s) {
       String str = "";
       for (int i = 0; i < s.length(); i++) {
           int ch = (int) s.charAt(i);
           String s4 = Integer.toHexString(ch);
           str = str + s4;
       }
       return str;
   }


    public static Object setParams(Object com)  {
        String result = null;
        try {
            result = encryptEcb(sKey, JSON.toJSONString(com));
        } catch(Exception e) {
            log.error(e.getMessage(),e);
        } 
        return result;
    }

    public static Object setParams2(Object com)  {
        String result = null;
        try {
            result = encryptEcb2(sKey, JSON.toJSONString(com));
        } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        String str = "9ceef14df14d80708bb3d58a823988eba785d45d50fd4ca7e7f9d5ae82af151a4552fa75b149ced1d4dcd040f91572373e23a75ee33349f777704d041205dbba8f60a1deb979c0eb59748681ecfe8df15d22e0248f906b11026d09589eb1f9d135eb842733e75c92ef1c796540a42510";
        Map<String, Object> info = Sm4Util.getParams(str);
        System.out.println(JSON.toJSONString(info));

        Map<String, Object> map = new HashMap<>();
        map.put("startDate", "2022-09-06");
        map.put("endDate", "2022-09-09");
        map.put("jzInfo", "{\"exposureDisposal\":\"1\",\"jzMethod\":\"1\",\"jzSituation\":\"3\"}");
        map.put("station_code", "3701020201");
        map.put("data", "[{\"bactPrice\":\"23\",\"bactcode\":\"28\",\"bactname\":\"狂犬病疫苗\",\"corpcode\":\"17\",\"corpname\":\"辽宁成大\",\"jc\":\"1\",\"productname\":\"液体狂犬病疫苗（Vero）\",\"productno\":\"2801\",\"result\":1, \"inocSource\" : \"S\"}]");
        map.put("deleteShotIds", "");
        map.put("user_name", "王清霞");
        map.put("fchildno", "374102010193000003");
        map.put("station_name", "历下医院预防接种门诊");
        map.put("birthday", "2000-02-01");
        String str1 = Sm4Util.encryptEcb(sKey, JSON.toJSONString(map));
        System.out.println(str1);


        System.out.println(DateUtils.addDays(new Date(), 0));

        String s = "49.0.0,,";
        String[] arr = s.split("[,]");
        System.out.println(JSON.toJSONString(arr));
    }

}
