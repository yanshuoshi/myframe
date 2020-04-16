package com.yss.cn.common.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * created by on ShuoShi Yan
 * md5加密解密、sha256加密、sha1加密
 */
public class EncryptionUtil {
	
	// 对字符串进行md5加密
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());

			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = (int) b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if (cc.length() == 1)
					sb.append('0');
				sb.append(cc);
			}

			return sb.toString();
		} catch (Exception e) {
		}
		return "";
	}

	public static void main(String[] args) {
		String s ="1123456201912121212";
		System.out.println(s.substring(s.length() - 12));
		System.out.println(s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));
	}
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

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }
	
	// 对字符串进行sha256加密
	public static String sha256(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());

			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = (int) b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if (cc.length() == 1)
					sb.append('0');
				sb.append(cc);
			}

			return sb.toString();
		} catch (Exception e) {
		}
		return "";
	}
	// 对字符串进行sha1加密
	public static String sha1(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());

			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = (int) b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if (cc.length() == 1)
					sb.append('0');
				sb.append(cc);
			}

			return sb.toString();
		} catch (Exception e) {
		}
		return "";
	}
	
}
