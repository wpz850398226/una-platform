package cn.kunli.una.utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/*import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;*/

/**
 * 二维码生成和读的工具类
 *
 */
public class QRCodeUtil {
    
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	/**
	 * 把生成的二维码存入到图片中
	 * @param matrix zxing包下的二维码类
	 * @return
	 */
	/*public static BufferedImage toBufferedImage(BitMatrix matrix) {
	 int width = matrix.getWidth();
	 int height = matrix.getHeight();
	 BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	 for (int x = 0; x < width; x++) {
	  for (int y = 0; y < height; y++) {
	   image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
	  }
	 }
	 return image;
	}*/
	/**
	 * 生成二维码并写入文件
	 * @param content 扫描二维码的内容
	 * @param format 图片格式 jpg
	 * @param file  文件
	 * @throws Exception
	 */
	/*public static void writeToFile(String content, String format, File file)
	  throws Exception {
	 MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
	 @SuppressWarnings("rawtypes")
	 Map hints = new HashMap();
	 //设置UTF-8， 防止中文乱码
	 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	 //设置二维码四周白色区域的大小
	 hints.put(EncodeHintType.MARGIN,3);
	 //设置二维码的容错性
	 hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
	 //画二维码
	 BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
	 BufferedImage image = toBufferedImage(bitMatrix);
	 if (!ImageIO.write(image, format, file)) {
	  throw new IOException("Could not write an image of format " + format + " to " + file);
	 }
	}*/
	/**
	 * 给二维码图片加上文字
	 * @param pressText 文字
	 * @param qrFile  二维码文件
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 */
	public static void pressText(String pressText, File qrFile, int fontStyle, Color color, int fontSize) throws Exception {
	 pressText = new String(pressText.getBytes(), "utf-8");
	 Image src = ImageIO.read(qrFile);
	 int imageW = src.getWidth(null);
	 int imageH = src.getHeight(null);
	 BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
	 Graphics g = image.createGraphics();
	 g.drawImage(src, 0, 0, imageW, imageH, null);
	 //设置画笔的颜色
	 g.setColor(color);
	 //设置字体
	 Font font = new Font("宋体", fontStyle, fontSize);
	 FontMetrics metrics = g.getFontMetrics(font);
	 //文字在图片中的坐标 这里设置在中间
	 int startX = (WIDTH - metrics.stringWidth(pressText)) / 2;
	 int startY = HEIGHT;
	 g.setFont(font);
	 g.drawString(pressText, startX, startY);
	 g.dispose();
	 FileOutputStream out = new FileOutputStream(qrFile);
	 ImageIO.write(image, "JPEG", out);
	 out.close();
	 System.out.println("image press success");
	}
	public static void main(String[] args) throws Exception {
		 File qrcFile = new File("d:","google1.jpg");
		 //writeToFile("www.google.com.hk", "jpg", qrcFile);
		 pressText("谷歌", qrcFile, 5, Color.RED, 32);
		}
  
}