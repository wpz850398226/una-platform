package cn.kunli.una.utils.common;

import java.util.Properties;

/** 
* @author 作者 : Ponzio
* @version 创建时间：2019年7月2日 上午9:48:54 
* 类说明 :关于系统的工具
*/

//@Component
public class SystemUtil {


	/*
	 * 判断当前操作系统是否为linux
	 * 
	 */
	public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }
	
}
