package com.mt.console.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getCurrentDate() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}

	public static String getCurrentDate(String fromatStr) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(fromatStr);
		return sdf.format(dt);
	}

	public static Date getDateByString(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
//		System.out.println(getCurrentDate());
		System.out.println(getCurrentDate("yyyymmddhhmmss"));
	}
}
