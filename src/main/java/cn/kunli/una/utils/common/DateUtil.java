package cn.kunli.una.utils.common;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 【工程名】
 * <p>
 * 【版权所有】
 * 金卡银证
 * 【类文件名称】
 * DateUtil
 * 【类文件描述】
 * 时间工具类
 * <p>
 * 【历史信息】
 * 版本      日期      作者/修改者     内容描述
 * -------- ---------   ---------- ------------------------
 * 1.0.0    17:08 2019/11/21    yang_xp       最初版本
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
	 * 方法实现说明  返回某个日期下几天的日期
	 *
	 * @param
	 * @return
	 * @author y_xiaopeng
	 * @date 2020/1/2 9:14
	 */
	public static Date getNextDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
		return cal.getTime();
	}

	/**
	 * 方法实现说明  返回某个日期前几天的日期
	 *
	 * @param
	 * @return
	 * @author y_xiaopeng
	 * @date 2020/1/2 9:14
	 */
	public static Date getFrontDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
		return cal.getTime();
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
}
