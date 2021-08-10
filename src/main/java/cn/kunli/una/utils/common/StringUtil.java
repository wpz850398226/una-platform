package cn.kunli.una.utils.common;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ponzio
 * @version 2019年7月25日 上午11:05:42
 * 字符串工具类
 */
public class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

	//大写转小写+下划线
	public static String upperCharToUnderLine(String param) {
		if(param==null ||param.equals("")){
			return "";
		}
        Pattern p=Pattern.compile("[A-Z]");
		StringBuilder builder=new StringBuilder(param);
		Matcher mc=p.matcher(param);
		int i=0;

		while (mc.find()) {
			builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
			i++;
		}

		if('_' == builder.charAt(0)){
			builder.deleteCharAt(0);
		}

		return builder.toString();
	}

    //小写+下划线转大写
    public static String underLineToUpperChar (String param) {
        param = param.toLowerCase();
        Matcher matcher = linePattern.matcher(param);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }



	/**
     * 把中文转成Unicode码
     */
    public static String chineseToUnicode(String str) {
    	String result="";
        for (int i = 0; i < str.length(); i++){
            int chr1 = (char) str.charAt(i);
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
                result+="\\u" + Integer.toHexString(chr1);
            }else{
                result+=str.charAt(i);
            }
        }
        return result;
    }



    //英文首字符大写
    public static String capitalizeInitial(String str) {
    	String result = str.substring(0, 1).toUpperCase()+str.substring(1);
    	return result;
    }

    //英文首字符小写
    public static String lowerInitial(String str) {
        String result = str.substring(0, 1).toLowerCase()+str.substring(1);
        return result;
    }

    //判断字符串是否是数字
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        if(str.indexOf(".")>0){//判断是否有小数点
            if(str.indexOf(".")==str.lastIndexOf(".") && str.split("\\.").length==2){ //判断是否只有一个小数点
                return pattern.matcher(str.replace(".","")).matches();
            }else {
                return false;
            }
        }else {
            return pattern.matcher(str).matches();
        }
    }

    /**
     * 获取指定位数的随机数字验证码
     * Param size 数字长度
     */
    public static String getCaptcha(Integer size){
        int shortMessageCaptcha = (int)(Math.random()*10000);
        String captcha = String.format("%04d", shortMessageCaptcha);  //每次调用生成一次四位数的随机数
        return captcha;
    }

    /**
     * 判断以特殊符号分隔的字符串，分隔后是否有某个字符串
     * @param parentStr     被判断的父字符串
     * @param subStr        判断被包含的子字符串
     * @param regex        父字符串以特殊符号分隔为数组,默认为逗号
     * @return
     */
    public static Boolean containSubString(String parentStr,String subStr,String regex){
        if(StringUtils.isBlank(parentStr)||StringUtils.isBlank(subStr))return false;
        if(StringUtils.isBlank(regex))regex = ",";
        String[] parentStrArray = parentStr.split(regex);
        List<String> parentStrList= Arrays.asList(parentStrArray);
        if(parentStrList.contains(subStr)){
            return true;
        }
        return false;
    }


    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }


}
