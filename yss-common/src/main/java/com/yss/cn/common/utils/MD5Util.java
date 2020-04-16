package com.yss.cn.common.utils;

import java.security.MessageDigest;

/**
 * @author Shuoshi.Yan
 * @package:com.yss.common.utils
 * @className:MD5操纵
 * @description:
 * @date 2019-12-25 10:58
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/

public class MD5Util {
    //md5加密 可解密
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public static void main(String[] args) {
        String s ="1123456201912121212";
        System.out.println(s.substring(s.length() - 12));
        System.out.println(s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
    }

}
