package cn.kunli.una.utils.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 作者 : Ponzio
 * @version 创建时间：2019年7月4日 上午11:03:47
 * 类说明 :时间工具类，获取时间
 */
public class DateUtil {
	/**
	 * 时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String SEC_WITH_HYPHEN = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDD = "yyyy-MM-dd";


	public static void main(String[] args) throws ParseException {
//		System.out.println(strToDate("2020-03-10"));

		System.out.println(getDayNumOnString("2020-09-05 00:00:00","2020-09-06 00:00:00"));
	}

	// 获取当前日期前第n个月的日期
	public static Date getTimeBeforeMonths(int i) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -i);
		Date m = c.getTime();
		return m;
	}

	// 获取最近n个月
	public static String[] getLastMonths(int i) {
		String[] months = new String[i];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		for (int num = 0; num < i; num++) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MONTH, -num);
			Date m = c.getTime();
			String month = sdf.format(m);
			months[i-1-num] = month;
		}
		return months;
	}

	/**
	 * 方法实现说明  获取某个时间的年月日
	 *
	 * @param
	 * @return
	 * @author y_xiaopeng
	 * @date 2020/1/13 11:46
	 */
	public static String[] getDateYMD(String stringDate) throws ParseException {
		Date date = new SimpleDateFormat(YYYYMMDD).parse(stringDate);
		Calendar ca = Calendar.getInstance();

		ca.setTime(date);
		int day = ca.get(Calendar.DAY_OF_YEAR);//一年中的第几天

		int week = ca.get(Calendar.WEEK_OF_YEAR);//一年中的第几周

		int month = ca.get(Calendar.MONTH);//第几个月

		int year = ca.get(Calendar.YEAR);//年份数值

		return null;
	}


	/**
	 * @return
	 * @Author yang_xp
	 * @Description 格式化时间
	 * @Date 17:12 2019/11/21
	 * @Param
	 **/
	public static String format(Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(date);
	}


	/**
	 * @return
	 * @Author yang_xp
	 * @Description 格式化时间
	 * @Date 17:12 2019/11/21
	 * @Param
	 **/
	public static String formatyyyyMMdd(Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(YYYYMMDD);
		return simpledateformat.format(date);
	}

	/**
	 * 方法名：getNowDayTime 描述 ：得到当前的时间
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDayTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getNowDayTime 描述 ：得到当前的时间
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDayTime(String format) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getNowDayTimeNoFm 描述 ： 无格式符合 日期时间
	 *
	 * @return
	 */
	public static String getNowDayTimeNoFm() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getNowDayTimeNoFm 描述 ：无格式符合 日期时间
	 *
	 * @param date
	 * @return
	 */
	public static String getNowDayTimeNoFm(String date) {
		String dateStr = "";
		if (date != null && !"".equals(date)) {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式

			ParsePosition pos = new ParsePosition(0);
			Date datetime = df1.parse(date, pos);

			dateStr = df2.format(datetime);
		}

		return dateStr;
	}

	/**
	 * 方法名：getNowDayFirstTime 描述 ：得到当天的开始时间
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDayFirstTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 获取今天结束时间
	 */
	public static String getEndTime() {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return simpledateformat.format(date) + " 23:59:59";

	}

	/**
	 * 方法实现说明  得到当年开始时间
	 *
	 * @param
	 * @return
	 * @author y_xiaopeng
	 * @date 2020/3/10 15:16
	 */
	public static String getCurrYearFirst() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());

	}

	/**
	 * 方法名：getNowMonthFirstDay 描述 ： 得到本月的第一天
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getMonthLastDay 描述 ：得到本月的最后一天
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getNowWeekFirstDay 描述 ：得到本周的第一天
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowWeekFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getWeekLastDay 描述 ：得到本周的最后一天
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getWeekLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getPrevDayFirstTime 描述 ：得到上一天的开始时间
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getPrevDayFirstTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：getPrevDayLastTime 描述 ：得到上一天 的结束时间
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getPrevDayLastTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法名：strDateFormatToInt 描述 ：将日期格式为 yyyy-MM-dd HH:mm:ss 的字符串 ，转化成 整数
	 *
	 * @param dateStr
	 * @return
	 */
	public static int strDateFormatToInt(String dateStr) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		int intDate = 0;
		if (dateStr != null && !"".equals(dateStr) && !"NAN".equals(dateStr) && !"undefined".equals(dateStr)) {
			try {
				date = sdFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			intDate = (int) (date.getTime() / 1000);
		}
		return intDate;
	}

	/**
	 * 方法名：strDateFormatToLong 描述 ：将日期格式为 yyyy-MM-dd HH:mm:ss 的字符串 ，转化成 整数
	 *
	 * @param dateStr
	 * @return
	 */
	public static long strDateFormatToLong(String dateStr) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		long intDate = 0;
		if (dateStr != null && !"".equals(dateStr) && !"NAN".equals(dateStr) && !"undefined".equals(dateStr)) {
			try {
				date = sdFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			intDate = (date.getTime() / 1000);
		}
		return intDate;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		if (strtodate == null) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			strtodate = formatter.parse(strDate, pos);
		}
		return strtodate;
	}

	/**
	 * 方法名：getDayParam 描述 ：计算向前、向后N天的日期，delay 由正负数来计算
	 *
	 * @param nowdate
	 * @param delay
	 * @return
	 */
	public static String getDayParam(String nowdate, int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 方法名：getDayNumOnInt 描述 ：计算两个日期整数之间天数，日期时间参数为整数
	 *
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static int getDayNumOnInt(int beginTime, int endTime) {
		return (endTime - beginTime) / (24 * 60 * 60);
	}

	/**
	 * 方法名：getDayNumOnString 描述 ：计算两个日期整数之间天数，日期时间参数为字符串，字符串格式为：2016-12-01 00:00:00
	 *
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static int getDayNumOnString(String beginTime, String endTime) {
		int bTime = strDateFormatToInt(beginTime);
		int eTime = strDateFormatToInt(endTime);
		return (eTime - bTime) / (24 * 60 * 60);
	}

	/**
	 * 方法名：getDateFormatConver 描述 ： 日期格式转换 yyyy/MM/dd 转化为 yyyy-MM-dd
	 *
	 * @param date
	 * @return
	 */
	public static String getDateFormatConver(String date) {

		SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");// 设置日期格式
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

		ParsePosition pos = new ParsePosition(0);
		Date datetime = df1.parse(date, pos);

		String dateStr = df2.format(datetime);
		return dateStr;
	}

	/**
	 * 方法名：getConverTimeZero 描述 ：日期格式转换 yyyy/MM/dd 转化为 yyyy-MM-dd 00:00:00
	 *
	 * @param date
	 * @return
	 */
	public static String getConverTimeZero(String date) {
		String dateStr = "";
		if (date != null && !"".equals(date)) {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");// 设置日期格式
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

			ParsePosition pos = new ParsePosition(0);
			Date datetime = df1.parse(date, pos);

			dateStr = df2.format(datetime);
		}

		return dateStr;
	}

	/**
	 * 方法名：getConverTimeEnd 描述 ：日期格式转换 yyyy/MM/dd 转化为 yyyy-MM-dd 23:59:59
	 *
	 * @param date
	 * @return
	 */
	public static String getConverTimeEnd(String date) {
		String dateStr = "";
		if (date != null && !"".equals(date)) {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");// 设置日期格式
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");// 设置日期格式

			ParsePosition pos = new ParsePosition(0);
			Date datetime = df1.parse(date, pos);

			dateStr = df2.format(datetime);
		}

		return dateStr;
	}

	/**
	 * 方法名：getDateDiffHour 描述 ： 两个时间相差的小时数
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Long getDateDiffHour(String startTime, String endTime) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			// System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" + (min -
			// day * 24 * 60) + "分钟" + sec + "秒。");
			// System.out.println("hour=" + hour + ",min=" + min);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hour;
	}

	/**
	 * 方法名：getDateDiffDay 描述 ： 两个时间相差的天数
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Long getDateDiffDay(String startTime, String endTime) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			// System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" + (min -
			// day * 24 * 60) + "分钟" + sec + "秒。");
			// System.out.println("hour=" + hour + ",min=" + min);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return day;
	}

	/**
	 * 方法名：getDateNumOfTimeslot 描述 ：
	 *
	 * @param beginDate
	 * @param endDate
	 * @param uploadDate
	 * @return
	 */
	public static int getDateNumOfTimeslot(String beginDate, String endDate, String uploadDate) {
		int num = DateUtil.getDayNumOnString(beginDate, endDate) + 1;
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.strToDate(uploadDate));
		int upload = c.get(Calendar.DATE);
		for (int i = 0; i < num; i++) {
			String tempDate = DateUtil.getDayParam(beginDate, i);
			c.setTime(DateUtil.strToDate(tempDate));
			int start = c.get(Calendar.DATE);
			if (upload == start) {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * 方法名：getAddDateDay 描述 ：
	 *
	 * @param day
	 * @return
	 */
	public static String getAddDateDay(String format, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));// 今天的日期
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);// 让日期加1
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 过去一周的周一时间
	 *
	 * @return
	 */
	public static String lastMonday() {

		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int offset = 1 - dayOfWeek;
		calendar.add(Calendar.DATE, offset - 7);

		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return simpledateformat.format(calendar.getTime());
	}

	/**
	 * 方法实现说明   获取上周最后一天
	 *
	 * @param
	 * @return
	 * @author y_xiaopeng
	 * @date 2019/12/31 9:08
	 */
	public static String getPreviousWeekSunday() {

		int weeks = -1;

		int mondayPlus = getMondayPlus();

		GregorianCalendar currentDate = new GregorianCalendar();

		currentDate.add(5, mondayPlus + weeks);


		Date monday = currentDate.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);

		//System.out.println("上周最后一天："+CommonUtils.parseDateToLong(preMonday+" 23:59:59", "yyyy-MM-dd hh:mm:ss"));

		return preMonday + " 23:59:59";
	}

	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();

		int dayOfWeek = cd.get(7) - 1;

		if (dayOfWeek == 1) {
			return 0;
		}

		return (1 - dayOfWeek);
	}

	/**
	 * Date转化为dateFormat时间格式的字符串
	 *
	 * @param date       Date
	 * @param dateFormat 时间格式
	 * @return dateFormat时间格式的字符串
	 */
	public static String date2Str(Date date, String dateFormat) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(date);
	}


	/**
	 * 方法名：dateToShortCode
	 * 描述  ：
	 * 时间类型转换成字符串并按照yyyy-MM-dd显示
	 *
	 * @param date
	 * @return
	 */
	public static String dateToShortCode(Date date) {
		return dateToShortCode(date, "yyyy-MM-dd");
	}

	/**
	 * 方法名：dateToShortCode
	 * 描述  ：
	 * 时间类型转换成字符串并按照指定的格式显示
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToShortCode(Date date, String format) {
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
			return simpledateformat.format(date);
		} catch (Exception localException) {
		}
		return "";
	}

	/**
	 * 获得该月第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());
		return firstDayOfMonth;
	}

	/**
	 * 获得该月最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			try {
				format = new SimpleDateFormat("yyyy-MM-dd");
				format.setLenient(false);
				format.parse(str);
			} catch (Exception e2) {
				convertSuccess = false;
			}
		}
		return convertSuccess;
	}

	/**
	 * 解析字符串日期,不报错  异常返回null
	 *
	 * @param d
	 * @param format
	 * @return
	 * @author lsg
	 * @created 2018-11-27 下午4:30:35
	 */
	public static Date parseDate(String d, String format) {
		try {
			return new SimpleDateFormat(format).parse(d);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析对象到日期
	 *
	 * @return
	 * @author lsg
	 * @created 2018-11-27 下午4:08:10
	 */
	public static Date parseDate(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof Date) {
			return (Date) o;
		}
		if (o instanceof java.sql.Date) {
			return (Date) o;
		}
		if (o instanceof String) {
			// yyyy-MM-dd HH:mm:ss  /
			String d = (String) o;
			StringBuilder format = new StringBuilder("yyyy");
			if (d.charAt(4) == '-') {
				format.append("-MM-dd");
			} else if (d.charAt(4) == '/') {
				format.append("/MM/dd");
			} else if (d.charAt(4) == '_') {
				format.append("_MM_dd");
			} else {
				format.append("MMdd");
			}
			if (d.length() < format.length()) {
				return null;
			} else if (d.length() == format.length()) {
				return parseDate(d, format.toString());
			}
			if (d.charAt(format.length()) == ' ') {
				format.append(' ');
			}
			if (d.charAt(format.length() + 2) == ':') {
				format.append("HH:mm:ss");
			} else if (d.charAt(format.length() + 2) == '/') {
				format.append("HH/mm/ss");
			} else {
				format.append("HHmmss");
			}
			if (d.length() < format.length()) {
				return null;
			}
			if (d.length() == format.length()) {
				return parseDate(d, format.toString());
			}
			if (d.charAt(format.length()) == '.' && d.length() == (format.length() + 4)) {
				format.append(".SSS");
			} else if (d.length() == (format.length() + 3)) {
				format.append("SSS");
			} else {
				d = d.substring(0, format.length());
			}
			return parseDate(d, format.toString());
		}
		if (o instanceof Long) {
			long l = (long) o;
			if (l < 10000000000l) {
				return new Date(l * 1000);
			}
			return new Date(l);
		}
		if (o instanceof Integer) {
			long l = (int) o * 1000;
			return new Date(l);
		}
		return null;
	}

	/**
	 * 日期比较，如果s>=e,返回true，否则返回false
	 * @param s
	 * @param e
	 * @return
	 */
	public static boolean compareDate(Date s,Date e) {
		if(s==null || e ==null){
			return false;
		}
		return s.getTime()>=e.getTime();
	}

	//获取日期的字符串表示
	public static String getStrOfDateTime(Date d){
		if(d!=null){
			SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = s.format(d); //转为字符串
			return dateStr;
		}
		return null;
	}

	//获取日期的字符串表示
	public static String getStrOfTime(Date d){
		if(d!=null){
			SimpleDateFormat s= new SimpleDateFormat("HH:mm:ss");
			String dateStr = s.format(d); //转为字符串
			return dateStr;
		}
		return null;
	}

	//获取日期的字符串表示
	public static String getStrOfDate(Date d){
		if(d!=null){
			SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = s.format(d); //转为字符串
			return dateStr;
		}
		return null;
	}
	//获取日期的字符串表示
	public static String getStrOfDate(Date d,String format){
		if(d!=null){
			SimpleDateFormat s= new SimpleDateFormat(format);
			String dateStr = s.format(d); //转为字符串
			return dateStr;
		}
		return null;
	}

	// 获取某天的结束时间
	public static Date getEndOfDate(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	// 获取当天的开始时间
	public static Date getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获取当天的结束时间
	public static Date getDayEnd() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	// 获取昨天的开始时间
	public static Date getBeginDayOfYesterday() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	// 获取昨天的结束时间
	public static Date getEndDayOfYesterDay() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	// 获取明天的开始时间
	public static Date getBeginDayOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	// 获取明天的结束时间
	public static Date getEndDayOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	// 获取本周的开始时间
	@SuppressWarnings("unused")
	public static Date getBeginDayOfWeek() {
		Date date = new Date();
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek);
		return getDayStartTime(cal.getTime());
	}

	// 获取本周的结束时间
	public static Date getEndDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginDayOfWeek());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date weekEndSta = cal.getTime();
		return getDayEndTime(weekEndSta);
	}

	// 获取上周的开始时间
	@SuppressWarnings("unused")
	public static Date getBeginDayOfLastWeek() {
		Date date = new Date();
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek - 7);
		return getDayStartTime(cal.getTime());
	}

	// 获取上周的结束时间
	public static Date getEndDayOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginDayOfLastWeek());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date weekEndSta = cal.getTime();
		return getDayEndTime(weekEndSta);
	}

	// 获取本月的开始时间
	public static Date getBeginDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		return getDayStartTime(calendar.getTime());
	}

	// 获取本月的结束时间
	public static Date getEndDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(getNowYear(), getNowMonth() - 1, day);
		return getDayEndTime(calendar.getTime());
	}

	// 获取上月的开始时间
	public static Date getBeginDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 2, 1);
		return getDayStartTime(calendar.getTime());
	}

	// 获取上月的结束时间
	public static Date getEndDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 2, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(getNowYear(), getNowMonth() - 2, day);
		return getDayEndTime(calendar.getTime());
	}

	// 获取本年的开始时间
	public static Date getBeginDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getNowYear());
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		return getDayStartTime(cal.getTime());
	}

	// 获取本年的结束时间
	public static Date getEndDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getNowYear());
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DATE, 31);
		return getDayEndTime(cal.getTime());
	}

	// 获取某个日期的开始时间
	public static Timestamp getDayStartTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d)
			calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	// 获取某个日期的结束时间
	public static Timestamp getDayEndTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d)
			calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return new Timestamp(calendar.getTimeInMillis());
	}

	// 获取今年是哪一年
	public static Integer getNowYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}

	// 获取本月是哪一月
	public static int getNowMonth() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(2) + 1;
	}

	// 两个日期相减得到的天数
	public static int getDiffDays(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null) {
			throw new IllegalArgumentException("getDiffDays param is null!");
		}
		long diff = (endDate.getTime() - beginDate.getTime())
				/ (1000 * 60 * 60 * 24);
		int days = new Long(diff).intValue();
		return days;
	}

	// 两个日期相减得到的毫秒数
	public static long dateDiff(Date beginDate, Date endDate) {
		long date1ms = beginDate.getTime();
		long date2ms = endDate.getTime();
		return date2ms - date1ms;
	}

	// 获取两个日期中的最大日期
	public static Date max(Date beginDate, Date endDate) {
		if (beginDate == null) {
			return endDate;
		}
		if (endDate == null) {
			return beginDate;
		}
		if (beginDate.after(endDate)) {
			return beginDate;
		}
		return endDate;
	}

	// 获取两个日期中的最小日期
	public static Date min(Date beginDate, Date endDate) {
		if (beginDate == null) {
			return endDate;
		}
		if (endDate == null) {
			return beginDate;
		}
		if (beginDate.after(endDate)) {
			return endDate;
		}
		return beginDate;
	}

	// 返回某月该季度的第一个月
	public static Date getFirstSeasonDate(Date date) {
		final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int sean = SEASON[cal.get(Calendar.MONTH)];
		cal.set(Calendar.MONTH, sean * 3 - 3);
		return cal.getTime();
	}

	// 返回某个日期下几天的日期
	public static Date getNextDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
		return cal.getTime();
	}

	// 返回某个时间后几个小时的时间
	public static Date getNextSecond(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + i);
		return cal.getTime();
	}

	// 返回某个时间后几个小时的时间
	public static Date getNextHour(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + i);
		return cal.getTime();
	}

	// 返回某个日期前几天的日期
	public static Date getFrontDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
		return cal.getTime();
	}

	// 获取某年某月到某年某月按天的切片日期集合(间隔天数的集合)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getTimeList(int beginYear, int beginMonth, int endYear,
								   int endMonth, int k) {
		List list = new ArrayList();
		if (beginYear == endYear) {
			for (int j = beginMonth; j <= endMonth; j++) {
				list.add(getTimeList(beginYear, j, k));
			}
		} else {
			{
				for (int j = beginMonth; j < 12; j++) {
					list.add(getTimeList(beginYear, j, k));
				}
				for (int i = beginYear + 1; i < endYear; i++) {
					for (int j = 0; j < 12; j++) {
						list.add(getTimeList(i, j, k));
					}
				}
				for (int j = 0; j <= endMonth; j++) {
					list.add(getTimeList(endYear, j, k));
				}
			}
		}
		return list;
	}

	// 获取某年某月按天切片日期集合(某个月间隔多少天的日期集合)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getTimeList(int beginYear, int beginMonth, int k) {
		List list = new ArrayList();
		Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
		int max = begincal.getActualMaximum(Calendar.DATE);
		for (int i = 1; i < max; i = i + k) {
			list.add(begincal.getTime());
			begincal.add(Calendar.DATE, k);
		}
		begincal = new GregorianCalendar(beginYear, beginMonth, max);
		list.add(begincal.getTime());
		return list;
	}

	/**
	 * 获得时间字符串
	 *
	 * @param date
	 * @param format 格式化格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateString(Date date, String format) {
		String str = null;
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		str = formatter.format(date);
		return str;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	/**
	 * 返回传入时间是星期几
	 * 1-7
	 * @param date
	 * @return
	 */
	public static int getWeekByTime(Date date){
		int week=0;
		if(date==null){
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();//使用日历类
		cal.setTime(new Date());
		//获取年
		int year = cal.get(Calendar.YEAR);
		//获取月份，0表示1月份
		int month = cal.get(Calendar.MONTH);
		//获取当前天数
		int day = cal.get(Calendar.DAY_OF_MONTH);
		week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}
		return week;
	}

	/**
	 * 获取两个日期之间的日期，包括开始结束日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		result.add(start);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	//合成两个时间
	public static Date getDateByTwo(Date date,Date time){
		Calendar dateCal = new GregorianCalendar();
		Calendar timeCal = new GregorianCalendar();
		dateCal.setTime(date);
		timeCal.setTime(time);
		dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
		dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
		dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
		return dateCal.getTime();
	}

	// 返回某个时间前几秒的时间
	public static Date getFrontSecond(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - i);
		return cal.getTime();
	}

	/**
	 * 将（18:00:00）时间格式字符串转换为时间 今天的yyyy-MM-dd HH:mm:ss
	 *
	 * @param strDate
	 * @return
	 */
	public static Date getTodayTimeByTimeStr(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		Date dateByTwo = getDateByTwo(new Date(), strtodate);
		return dateByTwo;
	}

	/**
	 * 返回两个年月日字符串之间的完整年月日
	 * @param start yyyy-MM-dd
	 * @param end yyyy-MM-dd
	 * @return
	 */
	public static List<String> getBetweenDateStrs(String start, String end) {
		Set<String> dates = new TreeSet<>();
		List<Date> betweenDates = getBetweenDates(DateUtil.strToDateLong(start+" 00:00:00"),
				DateUtil.strToDateLong(end+" 00:00:01"));
		for (int i = 0; i < betweenDates.size(); i++) {
			dates.add(DateUtil.getDateString(betweenDates.get(i),"yyyy-MM-dd"));
		}
		List<String> result = new ArrayList<>(dates);
		return result;
	}
}
