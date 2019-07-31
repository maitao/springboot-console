package com.mt.console.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tools {

	/** The FieldPosition. */
	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

	/** This Format for format the data to special format. */
	private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");

	/** This Format for format the number to special format. */
	private final static NumberFormat numberFormat = new DecimalFormat("0000");

	/** This int is the sequence number ,the default value is 0. */
	private static int seq = 0;

	private static final int MAX = 9999;

	/**
	 * 时间格式生成序列
	 * 
	 * @return String
	 */
	public static synchronized String generateSequenceNo() {

		Calendar rightNow = Calendar.getInstance();

		StringBuffer sb = new StringBuffer();

		dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

		numberFormat.format(seq, sb, HELPER_POSITION);

		if (seq == MAX) {
			seq = 0;
		} else {
			seq++;
		}

		return sb.toString();
	}

	/**
	 * Html转换为TextArea文本:编辑时拿来做转换
	 * 
	 * @author zhengxingmiao
	 * @param str
	 * @return
	 */
	public static String Html2Text(String str) {
		if (str == null) {
			return "";
		} else if (str.length() == 0) {
			return "";
		}
		str = str.replaceAll("<br />", "\n");
		str = str.replaceAll("<br />", "\r");
		return str;
	}

	/**
	 * TextArea文本转换为Html:写入数据库时使用
	 * 
	 * @author zhengxingmiao
	 * @param str
	 * @return
	 */
	public static String Text2Html(String str) {
		if (str == null) {
			return "";
		} else if (str.length() == 0) {
			return "";
		}
		str = str.replaceAll("\r\n", "<br />");
		//str = str.replaceAll("\r", "<br />");
		return str;
	}

	public static boolean isEmail(String value) {
		return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}
	
	public static boolean isPhone(String value) {
		return value.matches("^[1][3,5,7,0,8,4,9,6]+\\d{9}");
	}

}
