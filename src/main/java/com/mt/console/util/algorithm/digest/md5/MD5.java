package com.mt.console.util.algorithm.digest.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式只为数字
	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	// 转换字节数组为16进制字串
	private static String getMD5(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String getMD5(MessageDigest md, String str) {
		String outputStr = null;

		md.update(str.getBytes());
		// 获得密文
		byte[] b = md.digest();
		// 把密文转换成十六进制的字符串形式
		StringBuffer hexString = new StringBuffer();
		// 字节数组转换为 十六进制 数
		for (int i = 0; i < b.length; i++) {
			String shaHex = Integer.toHexString(b[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		outputStr = hexString.toString();
		return outputStr;
	}

	public static String getMD5ToUpperCase(String str){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return getMD5(md, str).toUpperCase();
	}
	
	public static void main(String[] args) {
		String str = "123123";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(getMD5ToUpperCase(str));
		System.out.println(getMD5(md, str));
		System.out.println(getMD5(md.digest(str.getBytes())));

	}

}
