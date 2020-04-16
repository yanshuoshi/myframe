package com.yss.cn.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
/**
 * @author:Shuoshi.Yan
 * @description: Base64
 * @date: 2019/12/25 10:57
 * @param: 
 * @return: 
 */
public class Base64Util {
	final static Base64.Encoder encoder = Base64.getEncoder();
	final static Base64.Decoder decoder = Base64.getDecoder();

	/**
	 * 给字符串加密
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		byte[] textByte = new byte[0];
		try {
			textByte = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String encodedText = encoder.encodeToString(textByte);
		return encodedText;
	}

	/**
	 * 将加密后的字符串进行解密
	 * @param encodedText
	 * @return
	 */
	public static String decode(String encodedText) {
		String text = null;
		try {
			text = new String(decoder.decode(encodedText), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static void main(String[] args) {
		System.out.println(encode("zhanghaoid:201912091212"));
		System.out.println(decode(encode("1:201912121212")));
	}
}
