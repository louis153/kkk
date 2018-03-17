package com.longti.upjc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>时间类</p>
 * <p>描述:</p>
 * @author liuxiaodong
 * @date 2015年9月9日 下午3:55:21
 */
public class DateUtils {
	protected final transient static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	/**
	 * 得到2天后时间
	 */
	public static Date get2DayAfterDate(){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 2);
		return now.getTime();
	}
	
	/**
	 * 得到n天后时间
	 */
	public static Date getDayAfterDate(int n) {
		try {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_YEAR, n);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(format.format(now.getTime()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	/**
	 * 得到n分钟后时间
	 */
	public static Date getMinuteAfterDate(int n) {
		try {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, n);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.parse(format.format(now.getTime()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	/**
	 * 指定日期的n天后
	 */
	public static Date getDayAfterDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date daten = null;
		try {
			daten = format.parse(format.format(calendar.getTime()));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		return daten;
	}
	
	/**
	 * 获取当前时间 格式为:yyyyMMddHHmmss
	 * 
	 * @return date日期
	 */
	public static String getDateToStrYMDHMS() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	/**
	 * 相差的天数
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	public static int getMinus(String beforeDate, String afterDate) {
		Calendar c1 = getCalendar(beforeDate);
		Calendar c2 = getCalendar(afterDate);
		long t1 = c1.getTimeInMillis();
		long t2 = c2.getTimeInMillis();
		long t = 1000 * 60 * 60 * 24;
		int minus = (int) ((t2 - t1) / t);
		return minus;
	}
	
	/**
	 * 相差的天数
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	public static int getMinus(Calendar beforeDate, Calendar afterDate) {
		long t1 = beforeDate.getTimeInMillis();
		long t2 = afterDate.getTimeInMillis();
		long t = 1000 * 60 * 60 * 24;
		int minus = (int) ((t2 - t1) / t);
		return minus;
	}
	
	/**
	 * 根据字符串时间得到当前时间
	 * @param @param dateStr
	 * @param @return
	 * @return Calendar
	 * @throws
	 */
	public static Calendar getCalendar(String dateStr) {
		Date date = null;
		SimpleDateFormat format = null;
		Calendar calendar = null;
		if (dateStr != null) {
			format = new SimpleDateFormat("yyyy-MM-dd");

			calendar = Calendar.getInstance();
			try {
				date = format.parse(dateStr);
				calendar.setTime(date);
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
		}
		return calendar;
	}
	
	/**
	 * 根据字符串时间得到时间对象
	 * @param dateStr
	 * @throws
	 */
	public static Date getDateToStr(String dateStr) {
		Date date = null;
		SimpleDateFormat format = null;
		if (dateStr != null) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(dateStr);
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
		}
		return date;
	}
	
	/**
	 * 当前的Calendar
	 * @param @return
	 * @return Calendar
	 * @throws
	 */
	public static Calendar getToDayCalendar() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		try {
			calendar.setTime(format.parse(format.format(new Date())));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		return calendar;
	}
	
	/**
	 * 当前的date
	 * @param @return
	 * @return Calendar
	 * @throws
	 */
	public static Date getToDay() {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(new Date()));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		return date;
	}
	
	/**
	 * 获取预定时间
	 */
	public static Date getReserveDay(Calendar calendar, int day) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DAY_OF_YEAR, day);
		Date date = new Date();
		try {
			date = format.parse(format.format(calendar.getTime()));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		return date;
	}
	
	/**
	 * 预定库存从哪一天开始
	 * @param opening_date_from	开放周期的开始时间
	 * @return Calendar
	 * @throws
	 */
	public static Calendar getReserveStartDay(String opening_date_from){
		// 开放周期的开始时间
		Calendar opening_calendar_from = getCalendar(opening_date_from);
		// 当前时间
		Calendar current_Calendar = getToDayCalendar();
		// 如果开放周期的开始时间 大于 当前时间 或者 等于 当前时间
		if (opening_calendar_from.after(current_Calendar)) {
			// || opening_calendar_from.equals(current_Calendar)
			return opening_calendar_from;
		}else{
			// 如果 小于 当前时间，当前时间+1
			current_Calendar.add(Calendar.DAY_OF_YEAR, 1);
			return current_Calendar;
		}
	}

	/**
	 * 库存生在的天数
	 * @param opening_date_from	开放周期从
	 * @param opening_date_from 开放周期至
	 * @param reserveTerm		预定期限
	 * @return int
	 * @throws
	 */
	public static int getReserveNum(String opening_date_from, String opening_date_to,
			int reserveTerm) {
		// 开放周期从
		Calendar opening_cal_from = getCalendar(opening_date_from);
		// 开放周期至
		Calendar opening_cal_to = getCalendar(opening_date_to);
		// 当前时间
		Calendar current_Calendar = DateUtils.getToDayCalendar();
		
		// 如果当前时间 在 开放周期至 之后，不生成库存
		if (current_Calendar.after(opening_cal_to)
				|| current_Calendar.equals(opening_cal_to)) {
			return 0;
		}
		
		int days = 0;
		// 如果当前时间 在 开放周期从 之后 或等于
		if (current_Calendar.after(opening_cal_from)
				|| current_Calendar.equals(opening_cal_from)) {
			
			// 如果当前时间+预定期限 大于 开放周期至 
			Calendar current_Calendar2 = DateUtils.getToDayCalendar();
			current_Calendar2.add(Calendar.DAY_OF_YEAR, reserveTerm);
			if(current_Calendar2.after(opening_cal_to)){
				// 当前时间与开放周期相差的天数
				days = getMinus(current_Calendar, opening_cal_to);
				if(days > 30){
					return 30;
				}else{
					return days;
				}
			}else{
				if(reserveTerm > 30){
					return 30;
				}else{
					return reserveTerm;
				}
			}
		}else{
			// 如果当前时间 在 开放周期从 之前
			days = getMinus(opening_cal_from, opening_cal_to) + 1;
			if(reserveTerm > days){
				reserveTerm = days;
			}
			if(reserveTerm > 30){
				reserveTerm = 30;
			}
			return reserveTerm;
		}
	}
	
	/**
	 * 星期
	 */
	public static int getWeekDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//一周第一天是否为星期天
		boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		//获取周几
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		//若一周第一天为星期天，则-1
		if(isFirstSunday){
		  weekDay = weekDay - 1;
		  if(weekDay == 0){
		    weekDay = 7;
		  }
		}
		return weekDay;
	}
	
	public static int getWeekDay(String weekStr){
		switch (weekStr) {
		case "周一":
			return 1;
		case "周二":
			return 2;
		case "周三":
			return 3;
		case "周四":
			return 4;
		case "周五":
			return 5;
		case "周六":
			return 6;
		case "周日":
			return 7;
		default:
			return 0;
		}
	}
	/**
	 * 时间转字符串
	 * @param  date
	 * @param  format
	 * @return String
	 * @throws
	 */
	public static String getDateToStr(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 字符串时间转date
	 */
	public static Date getStrToDate(String dateStr, String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Date getDateToDate(Date date, String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
}
