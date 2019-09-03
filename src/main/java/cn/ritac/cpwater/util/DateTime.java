/*
 *
 *
 */
package cn.ritac.cpwater.util;

import java.util.*;

import org.springframework.util.StringUtils;

import java.text.*;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> DateTime
 *<br><b>CreatTime:</b> 2019年5月14日下午3:22:15
 */
@SuppressWarnings("deprecation")
public class DateTime {

	public DateTime() {
	}

	public static final long SECOND = 1000;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
	public static final long WEEK = 7 * DAY;
	public static final String SHORTFORMAT = "yyyy-MM-dd";
	public static final String SHORTFORMATNOSPIT = "yyyyMMdd";
	public static final String YEARMONTHFORMAT = "yyyy-MM";
	public static final String LONGFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
	public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String MONTH_PATTERN = "yyyy-MM";
	public static final String YEAR_PATTERN = "yyyy";
	public static final String MINUTE_ONLY_PATTERN = "mm";
	public static final String HOUR_ONLY_PATTERN = "HH";

	/**
	 * 获得yymmdd类型的日期
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		String year = (date.getYear() + 1900) + "";
		String mm = (date.getMonth() + 1) + "";
		if (Integer.valueOf(mm).intValue() < 10) {
			mm = "0" + mm;
		}
		String day = date.getDate() + "";
		return year + mm + day;
	}

	/**
	 * 获得yy-mm-dd类型的日期
	 * @param date
	 * @return
	 */
	public static String DateString(Date date) {
		String year = (date.getYear() + 1900) + "";
		String mm = (date.getMonth() + 1) + "";
		if (Integer.valueOf(mm).intValue() < 10) {
			mm = "0" + mm;
		}
		String day = date.getDate() + "";
		if (day.length() == 1) {
			day = "0" + day;
		}
		return year + "-" + mm + "-" + day;
	}

	// 得到某一天是星期几
	public static int getDateInWeek(String strDate) {
		DateFormat df = DateFormat.getDateInstance();
		try {
			df.parse(strDate);
			java.util.Calendar c = df.getCalendar();
			int day = c.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
			return day;
		} catch (ParseException e) {
			return -1;
		}
	}

	// 得到当前日期

	public static String getCurrentDate() {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		java.util.Date nowdate = new java.util.Date();
		String str_date = d.format(nowdate);
		return str_date;
	}

	// 得到当前日期时间
	public static String getCurrentDateTime() {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd HH:mm:ss");
		java.util.Date nowdate = new java.util.Date();
		String str_date = d.format(nowdate);
		return str_date;

	}

	// 根据日期获得日期时间
	public static String getDateTime(java.util.Date date) {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd HH:mm:ss");
		String str_date = d.format(date);
		return str_date;
	}

	// 获得某月的最后一天
	public static int getDayNum(int year, int month) {
		if (month == 2) {
			return year % 400 != 0 && (year % 4 != 0 || year % 100 == 0) ? 28 : 29;
		}
		String SmallMonth = ",4,6,9,11,";
		return SmallMonth.indexOf(
				String.valueOf(String.valueOf((new StringBuffer(",")).append(String.valueOf(month)).append(",")))) < 0
						? 31
						: 30;
	}

	// 返回两个日期之间隔了多少天

	public static int DateDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
		return i;
	}

	// 返回两个日期之间隔了多少小时

	public static long DateDiffHours(Date date1, Date date2) {
		long i = (long) ((date1.getTime() - date2.getTime()) / 3600 / 1000);
		return i;
	}

	// 从放有日期的字符串中得到，相应的年，月，日 style is "Y"or"y" 返回年 style is "M"or"m" 返回月 style
	// is "D"or"d" 返回日 日期字符串要求 "YYYY-MM-DD"
	public static int getYearMonthDate(String strDate, String style) {
		int year;
		int month;
		int day;
		int firstDash;
		int secondDash;
		if (strDate == null) {
			return 0;
		}
		firstDash = strDate.indexOf('-');
		secondDash = strDate.indexOf('-', firstDash + 1);
		if ((firstDash > 0) & (secondDash > 0) & (secondDash < strDate.length() - 1)) {
			year = Integer.parseInt(strDate.substring(0, firstDash));
			month = Integer.parseInt(strDate.substring(firstDash + 1, secondDash));
			day = Integer.parseInt(strDate.substring(secondDash + 1));
		} else {
			return 0;
		}
		if (style.equalsIgnoreCase("Y")) {
			return year;
		} else if (style.equalsIgnoreCase("M")) {
			return month;
		} else if (style.equalsIgnoreCase("D")) {
			return day;
		} else {
			return 0;
		}
	}
	// 某一天，过几天后是几号

	public static java.sql.Date DateAdd(java.sql.Date date, int addday) {
		java.sql.Date datenew = null;
		int year = DateTime.getYearMonthDate(date.toString(), "Y");
		int month = DateTime.getYearMonthDate(date.toString(), "M");
		int day = DateTime.getYearMonthDate(date.toString(), "D");
		day = day + addday;
		String dayStr = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
		datenew = java.sql.Date.valueOf(dayStr);
		// datenew.setTime(datenew.getTime()+day*3600*24*1000);
		// 有问题。 改
		return datenew;

	}

	// 某一天的前几天是几号

	public static java.sql.Date DateBefore(java.sql.Date date, int addday) {
		java.sql.Date datenew = null;
		int year = DateTime.getYearMonthDate(date.toString(), "Y");
		int month = DateTime.getYearMonthDate(date.toString(), "M");
		int day = DateTime.getYearMonthDate(date.toString(), "D");
		day = day - addday;
		String dayStr = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
		datenew = java.sql.Date.valueOf(dayStr);
		// datenew.setTime(datenew.getTime()+day*3600*24*1000);
		// 有问题。 改
		return datenew;
	}

	// 某一天是否是年的头一天

	public static boolean isYearFirstDay(java.sql.Date date) {
		boolean i = false;
		if ((DateTime.getYearMonthDate(date.toString(), "M") == 1)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 1)) {
			i = true;
		}
		return i;
	}

	// 某一天是否是半年的头一天

	public static boolean isHalfYearFirstDay(java.sql.Date date) {
		boolean i = false;
		if (((DateTime.getYearMonthDate(date.toString(), "M") == 1)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 1))
				|| ((DateTime.getYearMonthDate(date.toString(), "M") == 7)
						&& (DateTime.getYearMonthDate(date.toString(), "D") == 1))) {
			i = true;
		}
		return i;
	}

	public static String getHalfYearFirstDay(java.sql.Date date) {
		String month = "01";
		if (DateTime.getYearMonthDate(date.toString(), "M") >= 7) {
			month = "07";
		}
		String day = Integer.toString(DateTime.getYearMonthDate(date.toString(), "Y")) + "-" + month + "-01";
		return day;
	}

	// 某一天是否是半年的最后一天

	public static boolean isHalfYearLastDay(java.sql.Date date) {
		boolean i = false;
		if (((DateTime.getYearMonthDate(date.toString(), "M") == 12)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 31))
				|| ((DateTime.getYearMonthDate(date.toString(), "M") == 6)
						&& (DateTime.getYearMonthDate(date.toString(), "D") == 30))) {
			i = true;
		}
		return i;
	}

	public static String getHalfYearLastDay(java.sql.Date date) {
		String month = "-06-30";
		if (DateTime.getYearMonthDate(date.toString(), "M") >= 7) {
			month = "-12-31";
		}
		String day = Integer.toString(getYearMonthDate(date.toString(), "Y")) + "-" + month;
		return day;
	}

	// 某一天是否是年的最后一天

	public static boolean isYearLastDay(java.sql.Date date) {
		boolean i = false;
		if ((DateTime.getYearMonthDate(date.toString(), "M") == 12)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 31)) {
			i = true;
		}
		return i;
	}

	// 某一天是否是季的头一天

	public static boolean isQuarterFirstDay(java.sql.Date date) {
		boolean i = false;
		if (((DateTime.getYearMonthDate(date.toString(), "M") == 1)
				|| (DateTime.getYearMonthDate(date.toString(), "M") == 4)
				|| (DateTime.getYearMonthDate(date.toString(), "M") == 7)
				|| (DateTime.getYearMonthDate(date.toString(), "M") == 10))
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 1)) {
			i = true;
		}
		return i;
	}

	public static String getQuarterFirstDay(java.sql.Date date) {
		String month = "01";
		if (DateTime.getYearMonthDate(date.toString(), "M") >= 10) {
			month = "10";
		} else if (DateTime.getYearMonthDate(date.toString(), "M") >= 7) {
			month = "07";
		} else if (DateTime.getYearMonthDate(date.toString(), "M") >= 4) {
			month = "04";
		} else if (DateTime.getYearMonthDate(date.toString(), "M") >= 1) {
			month = "01";
		}

		String day = Integer.toString(DateTime.getYearMonthDate(date.toString(), "Y")) + "-" + month + "-01";
		return day;
	}

	// 某一天是否是季的最后一天

	public static boolean isQuarterLastDay(java.sql.Date date) {
		boolean i = false;
		if ((DateTime.getYearMonthDate(date.toString(), "M") == 3)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 31)) {
			i = true;
		}
		if ((DateTime.getYearMonthDate(date.toString(), "M") == 6)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 30)) {
			i = true;
		}
		if ((DateTime.getYearMonthDate(date.toString(), "M") == 9)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 30)) {
			i = true;
		}
		if ((DateTime.getYearMonthDate(date.toString(), "M") == 12)
				&& (DateTime.getYearMonthDate(date.toString(), "D") == 31)) {
			i = true;
		}
		return i;
	}

	public static String getQuarterLastDay(java.sql.Date date) {
		String month = "-01-31";
		if (DateTime.getYearMonthDate(date.toString(), "M") >= 10) {
			month = "-12-31";
		} else if (DateTime.getYearMonthDate(date.toString(), "M") >= 7) {
			month = "-09-30";
		} else if (DateTime.getYearMonthDate(date.toString(), "M") >= 4) {
			month = "-06-30";
		}

		String day = Integer.toString(DateTime.getYearMonthDate(date.toString(), "Y")) + "-" + month;
		return day;
	}
	// 某一天是否是月的最后一天

	public static boolean isMonthLastDay(java.sql.Date date) {
		boolean i = false;
		java.sql.Date des_date = null;
		String month;
		String str_date = date.toString();
		String year = str_date.substring(0, str_date.indexOf("-"));
		int m = new Integer(str_date.substring(str_date.indexOf("-") + 1, str_date.lastIndexOf("-"))).intValue() + 1;
		month = new Integer(m).toString();
		if (m < 10) {
			month = "0" + month;
		}
		java.sql.Date mid_date = null;
		mid_date = java.sql.Date.valueOf(year + "-" + month + "-01");
		des_date = DateTime.DateAdd(mid_date, -1);
		if (DateTime.DateDiff(des_date, date) == 0) {
			i = true;
		}
		return i;
	}

	// 某一天是否是月的第一天

	public static boolean isMonthFisrtDay(java.sql.Date date) {
		boolean i = false;
		if ((DateTime.getYearMonthDate(date.toString(), "D") == 1)) {
			i = true;
		}
		return i;
	}

	// 获得月的第一天
	public static String getMonthFisrtDay(java.sql.Date date)

	{
		String month;
		if (DateTime.getYearMonthDate(date.toString(), "M") > 9) {
			month = Integer.toString(DateTime.getYearMonthDate(date.toString(), "M"));
		} else {
			month = "0" + Integer.toString(DateTime.getYearMonthDate(date.toString(), "M"));
		}
		String day = Integer.toString(DateTime.getYearMonthDate(date.toString(), "Y")) + "-" + month + "-01";
		return day;
	}

	public static java.sql.Timestamp getTimestamp() {
		try {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			String mystrdate = myFormat.format(calendar.getTime());
			return java.sql.Timestamp.valueOf(mystrdate);
		} catch (Exception e) {
			return null;
		}
	}

	public static java.sql.Timestamp getTimestamp(String datestr) {
		try {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String mystrdate = myFormat.format(myFormat.parse(datestr));
			return java.sql.Timestamp.valueOf(mystrdate);
		} catch (Exception e) {
			return null;
		}
	}

	// 格式化日期（Y-年，M-月,D-日 HH:mm:ss 小时：分钟：秒）
	public static String getDate(java.util.Date date, String format) {
		String result = null;
		try {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat(format);
			result = myFormat.format(date);
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	public static String getDate(java.util.Date date) {
		return getDate(date, LONGFORMAT);
	}

	// 转换成时间戳
	public static java.sql.Timestamp getDate(String datestr) {
		try {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = myFormat.parse(datestr);
			myFormat.applyLocalizedPattern("yyyy-MM-dd HH:mm:ss");
			String mystrdate = myFormat.format(date);
			return java.sql.Timestamp.valueOf(mystrdate);
		} catch (Exception e) {
			return null;
		}
	}

	// 将日期格式化成yyyy-MM-dd形式
	public static java.util.Date format(String datestr) {
		try {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = myFormat.parse(datestr);
			return date;
		} catch (Exception e) {
			return new Date(datestr);
		}
	}

	// 格式化日期（Y-年，M-月,D-日 HH:mm:ss 小时：分钟：秒）
	public static java.util.Date format(String datestr, String format) {
		try {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat(format);
			Date date = myFormat.parse(datestr);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	// 获得数据库格式的日期
	public static java.sql.Date getSqlDate(java.util.Date date) {
		java.sql.Date result = null;
		try {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String mystrdate = myFormat.format(date);
			result = java.sql.Date.valueOf(mystrdate);
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	public static java.util.Date addMonth(java.util.Date date, int month) {
		String strdate = getDate(date, SHORTFORMAT);
		int curmonth = Integer.parseInt(strdate.substring(5, 7));
		int year = Integer.parseInt(strdate.substring(0, 4));
		int addyear = month / 12;
		year = year + addyear;
		curmonth = curmonth + (month % 12);
		if (curmonth > 12) {
			curmonth = 1;
			year = year + 1;
		}
		String strmonth = String.valueOf(curmonth);
		if (strmonth.length() == 1) {
			strmonth = "0" + strmonth;
		}
		strdate = String.valueOf(year) + "-" + strmonth + "-" + strdate.substring(8, 10);
		return format(strdate, SHORTFORMAT);
	}

	/**
	 * 传递日期，  获得上个月的最后1天
	 * @param dt
	 * @return
	 */
	public static String getUpMDate(Date dt) {
		dt.setDate(1);
		dt.setDate(dt.getDate() - 1);
		return dt.toLocaleString();
	}

	/**
	  *得到当前是那一天。
	  *
	  */
	public static String getDate() {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		java.util.Date nowdate = new java.util.Date();
		String str_date = d.format(nowdate);
		return str_date;
	}

	/**
	 * 将10位日期格式化为8位
	 * @param dt
	 * @return
	 */
	public static String getShortDate(String dt) {
		if (dt != null) {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = myFormat.parse(dt);
				return getDate(date, SHORTFORMATNOSPIT);
			} catch (ParseException e) {
				return dt;
			}
		} else
			return dt;
	}

	/**
	 * 将8位日期格式化为10位
	 * @param dt
	 * @return
	 */
	public static String getLongDate(String dt) {
		if (dt != null) {
			java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
			try {
				Date date = myFormat.parse(dt);
				return getDate(date, SHORTFORMAT);
			} catch (ParseException e) {
				return dt;
			}
		} else
			return dt;
	}

	/**
	 * 判断是否是当月
	 * @param month
	 * @return
	 */
	public static boolean isSameYearMonth(String date) {
		try {
			String currdate = getCurrentDate();
			currdate = getShortDate(currdate).substring(0, 6);
			String lastdate = getShortDate(date).substring(0, 6);
			if (lastdate.equals(currdate)) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 每天零点时间戳
	 * @return
	 */
	public static long getTimeOfZero() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTimeInMillis() / 1000;
	}

	/** 
	 * 日期相加减天数 
	 * @param date 如果为Null，则为当前时间 
	 * @param days 加减天数 
	 * @param includeTime 是否包括时分秒,true表示包含 
	 * @return 
	 * @throws ParseException  
	 */
	public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException {
		if (date == null) {
			date = new Date();
		}
		if (!includeTime) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			date = sdf.parse(sdf.format(date));
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/** 
	 * 时间格式化成字符串 
	 * @param date Date 
	 * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd 
	 * @return 
	 * @throws ParseException 
	 */
	public static String dateFormat(Date date, String pattern) throws ParseException {
		if (StringUtils.isEmpty(pattern)) {
			pattern = DATE_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/** 
	 * 字符串解析成时间对象 
	 * @param dateTimeString String 
	 * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd 
	 * @return 
	 * @throws ParseException 
	 */
	public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
		if (StringUtils.isEmpty(pattern)) {
			pattern = DATE_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateTimeString);
	}

	/** 
	 * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化） 
	 * @param dateTime Date 
	 * @return 
	 * @throws ParseException 
	 */
	public static String dateTimeToDateString(Date dateTime) throws ParseException {
		String dateTimeString = dateFormat(dateTime, DATE_TIME_PATTERN);
		return dateTimeString.substring(0, 10);
	}

	/** 
	 * 当时、分、秒为00:00:00时，将日期时间格式成只有日期的字符串， 
	 * 当时、分、秒不为00:00:00时，直接返回 
	 * @param dateTime Date 
	 * @return 
	 * @throws ParseException 
	 */
	public static String dateTimeToDateStringIfTimeEndZero(Date dateTime) throws ParseException {
		String dateTimeString = dateFormat(dateTime, DATE_TIME_PATTERN);
		if (dateTimeString.endsWith("00:00:00")) {
			return dateTimeString.substring(0, 10);
		} else {
			return dateTimeString;
		}
	}

	/** 
	 * 将日期时间格式成日期对象，和dateParse互用 
	 * @param dateTime Date 
	 * @return Date 
	 * @throws ParseException 
	 */
	public static Date dateTimeToDate(Date dateTime) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**  
	 * 时间加减小时 
	 * @param startDate 要处理的时间，Null则为当前时间  
	 * @param hours 加减的小时  
	 * @return Date  
	 */
	public static Date dateAddHours(Date startDate, int hours) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
		return c.getTime();
	}

	/** 
	 * 时间加减分钟 
	 * @param startDate 要处理的时间，Null则为当前时间  
	 * @param minutes 加减的分钟 
	 * @return 
	 */
	public static Date dateAddMinutes(Date startDate, int minutes) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
		return c.getTime();
	}

	/** 
	 * 时间加减秒数 
	 * @param startDate 要处理的时间，Null则为当前时间  
	 * @param minutes 加减的秒数 
	 * @return 
	 */
	public static Date dateAddSeconds(Date startDate, int seconds) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
		return c.getTime();
	}

	/**  
	 * 时间加减天数  
	 * @param startDate 要处理的时间，Null则为当前时间  
	 * @param days 加减的天数  
	 * @return Date  
	 */
	public static Date dateAddDays(Date startDate, int days) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
		return c.getTime();
	}

	/**  
	 * 时间加减月数 
	 * @param startDate 要处理的时间，Null则为当前时间  
	 * @param months 加减的月数  
	 * @return Date  
	 */
	public static Date dateAddMonths(Date startDate, int months) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
		return c.getTime();
	}

	/**  
	 * 时间加减年数 
	 * @param startDate 要处理的时间，Null则为当前时间  
	 * @param years 加减的年数  
	 * @return Date  
	 */
	public static Date dateAddYears(Date startDate, int years) {
		if (startDate == null) {
			startDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);
		return c.getTime();
	}

	/**  
	 * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0）  
	 * @param myDate 时间  
	 * @param compareDate 要比较的时间  
	 * @return int  
	 */
	public static int dateCompare(Date myDate, Date compareDate) {
		Calendar myCal = Calendar.getInstance();
		Calendar compareCal = Calendar.getInstance();
		myCal.setTime(myDate);
		compareCal.setTime(compareDate);
		return myCal.compareTo(compareCal);
	}

	/** 
	 * 获取两个时间中最小的一个时间 
	 * @param date 
	 * @param compareDate 
	 * @return 
	 */
	public static Date dateMin(Date date, Date compareDate) {
		if (date == null) {
			return compareDate;
		}
		if (compareDate == null) {
			return date;
		}
		if (1 == dateCompare(date, compareDate)) {
			return compareDate;
		} else if (-1 == dateCompare(date, compareDate)) {
			return date;
		}
		return date;
	}

	/** 
	 * 获取两个时间中最大的一个时间 
	 * @param date 
	 * @param compareDate 
	 * @return 
	 */
	public static Date dateMax(Date date, Date compareDate) {
		if (date == null) {
			return compareDate;
		}
		if (compareDate == null) {
			return date;
		}
		if (1 == dateCompare(date, compareDate)) {
			return date;
		} else if (-1 == dateCompare(date, compareDate)) {
			return compareDate;
		}
		return date;
	}

	/** 
	 * 获取两个日期（不含时分秒）相差的天数，不包含今天 
	 * @param startDate 
	 * @param endDate 
	 * @return 
	 * @throws ParseException  
	 */
	public static int dateBetween(Date startDate, Date endDate) throws ParseException {
		Date dateStart = dateParse(dateFormat(startDate, DATE_PATTERN), DATE_PATTERN);
		Date dateEnd = dateParse(dateFormat(endDate, DATE_PATTERN), DATE_PATTERN);
		return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
	}

	/** 
	 * 获取两个日期（不含时分秒）相差的天数，包含今天 
	 * @param startDate 
	 * @param endDate 
	 * @return 
	 * @throws ParseException  
	 */
	public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException {
		return dateBetween(startDate, endDate) + 1;
	}

	/** 
	 * 获取日期时间的年份，如2017-02-13，返回2017 
	 * @param date 
	 * @return 
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/** 
	 * 获取日期时间的月份，如2017年2月13日，返回2 
	 * @param date 
	 * @return 
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/** 
	 * 获取日期时间的第几天（即返回日期的dd），如2017-02-13，返回13 
	 * @param date 
	 * @return 
	 */
	public static int getDayOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}

	/** 
	 * 获取日期时间当月的总天数，如2017-02-13，返回28 
	 * @param date 
	 * @return 
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}

	/** 
	 * 获取日期时间当年的总天数，如2017-02-13，返回2017年的总天数 
	 * @param date 
	 * @return 
	 */
	public static int getDaysOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	/** 
	 * 根据时间获取当月最大的日期 
	 * <li>2017-02-13，返回2017-02-28</li> 
	 * <li>2016-02-13，返回2016-02-29</li> 
	 * <li>2016-01-11，返回2016-01-31</li> 
	 * @param date Date 
	 * @return 
	 * @throws Exception  
	 */
	public static Date maxDateOfMonth(Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int value = cal.getActualMaximum(Calendar.DATE);
		return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
	}

	/** 
	 * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象 
	 * @param date Date 
	 * @return 
	 * @throws Exception  
	 */
	public static Date minDateOfMonth(Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int value = cal.getActualMinimum(Calendar.DATE);
		return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
	}

	/**
	 * 把分钟转换成天,小时,分钟
	 * @param  int minutes 分钟数
	 * @return map { day 天数 ; hours 小时数 ; minutes 分钟数}
	 */
	public static Map<String, Object> minutesToHours(int minutes) {
		Map<String, Object> tMap = new HashMap<String, Object>();
		int day = 0, hours = 0, minute = 0;
		// 天
		day = minutes / (60 * 24);
		minutes -= day * 60 * 24;
		// 小时
		hours = minutes / 60;
		minutes -= hours * 60;
		// 分钟
		minute = minutes / 60;
		minutes -= minute * 60;
		tMap.put("day", day);
		tMap.put("hours", hours);
		tMap.put("minutes", minutes);
		return tMap;

	}

	public static void main(String[] args) throws Exception {
		// Date d = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		// System.out.println("每月初: " + sdf.format(minDateOfMonth(d)));
		// System.out.println("每月末: " + sdf.format(maxDateOfMonth(d)));
		// System.out.println("当月小时: " + getDaysOfMonth(d) * 24);
		// System.out.println("当日月第几天: " + getDayOfDate(d));
		// System.out.println("当日月份: " + getMonth(d));
		// Map map = minutesToHours(1445);
		// System.out.println("总共" + map.get("day") + "天" + map.get("hours") + "小时" +
		// map.get("minutes") + "分");

		// Date d1 = new Date();
		// Date d2 = dateAdd(d1, 1, true);
		//
		// long a = DateDiffHours(d2, d1);
		// int b = DateDiff(d2, d1);
		// System.out.println(a + " : " + b);
		// int e = 2;
		// e += 3;
		// System.out.println(e);
		System.out.println(dateCompare(new Date(),null));
	}
}