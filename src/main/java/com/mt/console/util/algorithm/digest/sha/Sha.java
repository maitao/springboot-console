package com.mt.console.util.algorithm.digest.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha {
	public static String sha1Encrypt(String Str) throws NoSuchAlgorithmException {

		MessageDigest alg = MessageDigest.getInstance("SHA-1");
		alg.update(Str.getBytes());
		byte[] bts = alg.digest();
		String result = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1)
				result += "0";
			result += tmp;
		}
		return result;
	}
}
