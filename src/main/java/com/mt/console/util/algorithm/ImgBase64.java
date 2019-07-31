package com.mt.console.util.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author mt
 *	对于比较大的图片，转换为base64的字符串也比较大
 *
 */
public class ImgBase64 {
	public static void main(String[] args) {

		String imgFile = "C:\\fadada_tool1\\config\\resizeApi.png";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String base64Img = Decript.encryptBASE64(data);
		System.out.println(base64Img);
		byte[] b = Decript.decryptBASE64(base64Img);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		try {
			String textFile = "E:/fadada/test_img1/test.txt";
			String imgFilePath = "E:/fadada/test_img1/333.jpg";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();

			File txt = new File(textFile);
			if (!txt.exists()) {
				txt.createNewFile();
			}
			byte bytes[] = new byte[512];
			bytes = base64Img.getBytes(); // 新加的
			int b1 = base64Img.length(); // 改
			FileOutputStream fos = new FileOutputStream(txt);
			fos.write(bytes, 0, b1);
			fos.close();

			// **********************************************************
			/*
			 * int length = 0; //每一次读取的长度 char[] buffer = new char[2048];
			 * //设缓冲最大值为2048字符 //字符串转为字符流 BufferedReader br = new
			 * BufferedReader(new StringReader(base64Img)); while((length =
			 * br.read(buffer)) != -1){ //若读到的不是末尾 System.out.println(new
			 * String(buffer, 0, length)); //做你想做的事 }
			 */
		} catch (IOException e) {

		}
		// System.out.println();
		// data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAhkAAAHKBAMAAABcdkSkAAAAAXNSR0IArs4c6QAAABtQTFRF////AAAAEhISkZGRenp6xcXF7OzsOTk5YWFhVQfPjgAAAitJREFUeNrt3bEvQ1EYxuET5cZ6qsIqBsYiYpbQuUh0rRh07cBu8H8L4fbcaONIDOJ7nrHjbzjf2+mmBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/H/NzcnJ6a0O72Z7+c3BUIqU1vOHgRZpY/5Zoy9G85zVaF1nNRYvaFaj1dtTY/FozLMareOsRmuS1fg6u9QoZ5ca5exSo5xdasyyGstnV/QazTyrsWJ2Ba8xyWqsnF2ha3Rn1+A8dI3u7NoZH4Wu0Zld/WEKXaPXeStGKXaNqzLGYwpeo3w19lP0GsUkH0yj12jKc5Ki19goz4ka5TlRozwnapTnRI3ynKhRnpPwNZrynISv8b6+RkmNdpk/JjU+PPRfkhqtaVJjNTXUUEMNNdRQQw011FBDDTXUUEMNNdRQQw011FBDDTXUUEMNNdRQQw011FBDDTXUUEMNNdRQQw3UUEMNNdRQQw011FBDDTXUUEMNNdRQQw011FBDDTXUUEMNNdRQQw011FBDDTXUUEMNNdRQQw01UEMNNdRQ4yfuL7oWXwTt/HwZIsbSjygvM4xQY7MyRt6OUOOutsZuqEfzOyEe1fPaGnkaoMZzdY1xgBpzNdRQ41ffjQivaP1NiXBhr2pj7ESosVZbYytCjV5tjacQf2LP6mIcThMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD8Ha+sAcARVGA6tQAAAABJRU5ErkJggg==
	}
}
