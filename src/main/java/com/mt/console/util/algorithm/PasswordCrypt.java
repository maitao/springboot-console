package com.mt.console.util.algorithm;

public class PasswordCrypt {
	private static String SALT = "tm";
	
	public static String crypt(String account, String password){		
		return Decript.SHA1(account + password + SALT).toUpperCase();
	}
	
	public static byte[] ecrypt(String account, String password){
		return _DES.encryptMode(_DES.KEY_1, password.getBytes());
	}
	
	public static byte[] dcrypt(byte[] value){
		return _DES.decryptMode(_DES.KEY_1, value);
	}
	
	public static void main(String[] args) {
		
		//每次生成的密文都是不一样
		byte[] evalue = ecrypt("changlaiai@sina.com","123456");
		//byte[] encoded = _DES.encryptMode(_DES.KEY_1, "222".getBytes());
		byte[] srcBytes1 = dcrypt(evalue);
		System.out.println("加密后的字符串:" + evalue);
		System.out.println("加密后的字符串:" + (new String(srcBytes1)));

		//byte[] reqPassword = Base64.decode(pword);
//		byte[] srcBytes = _DES.decryptMode(_DES.KEY_1, encoded);
//		byte[] srcBytes1 = dcrypt(evalue);
//		System.out.println("解密后的字符串:" + (new String(srcBytes)));
//		System.out.println(srcBytes1);
//		System.out.println(srcBytes1.toString());
//		System.out.println(new String(srcBytes1));
//		System.out.println("解密后的字符串:" + (new String(srcBytes1)));
		
		System.out.println(crypt("changlaiai@sina.com","123456"));
	}
}
