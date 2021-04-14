package cn.kunli.una.utils.common;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//验证码工具类
public class CaptchaUtil {
	private CaptchaUtil() {
	}

	/*
	 * 随机字符字典
	 */
	private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','a','b','c','d','e',
			'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };

	/*
	 * 随机数
	 */
	private static Random random = new Random();

	/*
	 * 获取6位随机数
	 */
	private static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	/*
	 * 获取随机数颜色
	 */
	private static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	/*
	 * 返回某颜色的反色
	 */
	/*private static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}*/

	
	/*
	 * 返回验证码
	 */
	public static void outputCaptcha(HttpServletRequest request, HttpServletResponse response, String sessionName)
			throws IOException {

		response.setContentType("image/jpeg");

		String randomString = getRandomString();
		request.getSession(true).setAttribute(sessionName, randomString);

		int width = 200;
		int height = 60;

		Color color = getRandomColor();

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bi.createGraphics();
		graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
		//graphics.setColor(color);
		
		graphics.fillRect(0, 0, width, height);
		for (int i = 0; i < 4; i++) {
			graphics.setColor(getRandomColor());
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}
		graphics.setColor(color);
		graphics.drawString(randomString, 55, 40);
		for (int i = 0, n = random.nextInt(100); i < n; i++) {
			graphics.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}

		// 转成JPEG格式
		// 输出内存图片到输出流
		ImageIO.write(bi, "png", response.getOutputStream());
	}

	public static void outputCaptchaWithLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 内存图片对象(TYPE_INT_BGR 选择图片模式RGB模式)
		BufferedImage image = new BufferedImage(90, 30, BufferedImage.TYPE_INT_BGR);
		// 得到画笔
		Graphics graphics = image.getGraphics();
		// 画之前要设置颜色，设置画笔颜色
		graphics.setColor(Color.white);
		//填充矩形区域（指定要画的区域设置区）
		graphics.fillRect(0, 0, 90, 30);
		//为了防止黑客软件通过扫描软件识别验证码。要在验证码图片上加干扰线
        //给两个点连一条线graphics.drawLine();
		for (int i = 0; i < 5; i++) {
			graphics.setColor(getRandomColor());
			int x1 = random.nextInt(90);
			int y1 = random.nextInt(30);
			int x2 = random.nextInt(90);
			int y2 = random.nextInt(30);
			graphics.drawLine(x1, y1, x2, y2);
		}

		// 拼接4个验证码，画到图片上
		char[] arrays = { 'A', 'B', 'C', 'D', 'E', '+' };
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			// 设置字符的颜色

			int index = random.nextInt(arrays.length);
			builder.append(arrays[index]);
		}
		// 创建session对象将生成的验证码字符串以名字为checkCode保存在session中
		request.getSession().setAttribute("checkCode", builder.toString());
		// 将4个字符画到图片上graphics.drawString(str ,x,y);一个字符一个字符画
		for (int i = 0; i < builder.toString().length(); i++) {
			graphics.setColor(getRandomColor());
			char item = builder.toString().charAt(i);
			graphics.drawString(item + "", 10 + (i * 20), 15);
		}
		// 输出内存图片到输出流
		ImageIO.write(image, "png", response.getOutputStream());

	}
	
	
}
