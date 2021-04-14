package cn.kunli.una.utils;

import cn.kunli.una.utils.common.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPicUrlUtils {

	public static String getPicUrl() throws IOException {
		//获得资源文件输入流
		InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("uploadfilePath.properties");
		Properties uplodePath = new Properties();
		uplodePath.load(inStream);
		String picUrl = null;
		//判断是window还是linux
		boolean isLunux = SystemUtil.isOSLinux();
		if (isLunux) {
			//图片上传路径如果不存在创建
			picUrl = uplodePath.getProperty("picUrl2");
			File dir=new File(picUrl);
			dir.setWritable(true, false);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return picUrl;
		} else {
			//图片上传路径如果不存在创建
			picUrl = uplodePath.getProperty("picUrl1");
			File dir=new File(picUrl);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return picUrl;
		}
	}
	public static String getPicUrlByClassName(String className) throws IOException {
		//获得资源文件输入流
		InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("uploadfilePath.properties");
		Properties uplodePath = new Properties();
		uplodePath.load(inStream);
		String picUrl = null;
		//判断是window还是linux
		boolean isLunux = SystemUtil.isOSLinux();
		if (isLunux) {
			//图片上传路径如果不存在创建
			picUrl = uplodePath.getProperty("picUrl2")+className+"/";
			File dir=new File(picUrl);
			dir.setWritable(true, false);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return picUrl;
		} else {
			//图片上传路径如果不存在创建
			picUrl = uplodePath.getProperty("picUrl1")+className+"\\";
			File dir=new File(picUrl);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return picUrl;
		}
	}

	public static String getPicUrl(String pictype,String cusname) throws IOException {
		//获得资源文件输入流
		InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("uploadfilePath.properties");
		Properties uplodePath = new Properties();
		uplodePath.load(inStream);
		String picUrl = null;
		//判断是window还是linux
		boolean isLunux = SystemUtil.isOSLinux();
		if (isLunux) {
			//图片上传路径如果不存在创建
			picUrl = uplodePath.getProperty("picUrl2")+pictype+File.separator+cusname+File.separator;
			File dir=new File(picUrl);
			dir.setWritable(true, false);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return picUrl;
		} else {
			//图片上传路径如果不存在创建
			picUrl = uplodePath.getProperty("picUrl1")+pictype+File.separator+cusname+File.separator;
			System.out.println(picUrl);
			File dir=new File(picUrl);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return picUrl;
		}
	}
	
	public static String getUploadUrlBySystemType(String windowsUploadUrl,String linuxUploadUrl) throws IOException {
		//判断是window还是linux
		boolean isLinux = SystemUtil.isOSLinux();
		if (isLinux) {
			//图片上传路径如果不存在创建
			File dir=new File(linuxUploadUrl);
			dir.setWritable(true, false);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return linuxUploadUrl;
		} else {
			//图片上传路径如果不存在创建
			File dir=new File(windowsUploadUrl);
			if(!(dir.exists())){
				dir.mkdirs();
			}
			return windowsUploadUrl;
		}
	}
}
