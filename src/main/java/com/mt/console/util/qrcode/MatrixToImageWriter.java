package com.mt.console.util.qrcode;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public final class MatrixToImageWriter {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static final String CHARSET = "utf-8";
	private static final String FORMAT = "JPG";

	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;

	private MatrixToImageWriter() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, FORMAT, file)) {
			throw new IOException("Could not write an image of format " + FORMAT + " to " + file);
		}
	}

	/**
	 * 
	 * @param matrix
	 *            二维码矩阵相关
	 * @param format
	 *            二维码图片格式
	 * @param qrFile
	 *            二维码图片文件
	 * @param logoPath
	 *            logo路径
	 * @throws IOException
	 */
	public static void writeToFile(String content, File qrFile, String logoPath) throws IOException {
		// Graphics2D gs = image.createGraphics();
		//
		// gs.drawImage(img, 125, 125, null);
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
					hints);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Image img = ImageIO.read(new File(logoPath));
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		if (true) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			img = image;
		}
		// 插入LOGO
		BufferedImage bi = toBufferedImage(bitMatrix);
		Graphics2D graph = bi.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(img, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
		img.flush();

		if (!ImageIO.write(bi, FORMAT, qrFile)) {
			throw new IOException("Could not write an image of format " + FORMAT + " to " + qrFile);
		}
	}

	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	public static void main(String[] args) {
		try {

			String content = "http://www.wchot.com";
						// 不带LOGO
			// MatrixToImageWriter.writeToFile(bitMatrix, "jpg", new
			// File("E:/img","餐巾纸1.jpg"));
			// 带LOGO
			writeToFile(content, new File("e:\\img\\logocode.jpg"), "e:\\img\\microMsg.1457853785532.jpg");
			String decodeText = decode(new File("e:\\img\\logocode.jpg"));
			System.out.println("decodeText = " + decodeText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}