package com.anyin.ailibuli.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author HuangWenwei
 * 
 * @date 2014年10月9日
 */
public class TimeZoneUtil {
	
	
	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	 
		/**
		 * 以友好的方式显示时间
		 * 
		 * @param sdate
		 * @return
		 */
		public static String friendly_time(long timeSt) {
			
			
			Date d = new Date(timeSt*1000);
			//2016-01-03 09:17:55
	       // yyyy-MM-dd-HH-mm-ss
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String sdate = sf.format(d);
			
			
			
			Date time = null;

			if (TimeZoneUtil.isInEasternEightZones())
				time = toDate(sdate);
			else
				time = TimeZoneUtil.transformTime(toDate(sdate),
						TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());

			if (time == null) {
				return "Unknown";
			}
			String ftime = "";
			Calendar cal = Calendar.getInstance();

			// 判断是否是同一天
			String curDate = dateFormater2.get().format(cal.getTime());
			String paramDate = dateFormater2.get().format(time);
			if (curDate.equals(paramDate)) {
				int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
				if (hour == 0)
					ftime = Math.max(
							(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
							+ "分钟前";
				else
					ftime = hour + "小时前";
				return ftime;
			}

			long lt = time.getTime() / 86400000;
			long ct = cal.getTimeInMillis() / 86400000;
			int days = (int) (ct - lt);
			if (days == 0) {
				int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
				if (hour == 0)
					ftime = Math.max(
							(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
							+ "分钟前";
				else
					ftime = hour + "小时前";
			} else if (days == 1) {
				ftime = "昨天";
			} else if (days == 2) {
				ftime = "前天 ";
			} else if (days > 2 && days < 31) {
				ftime = days + "天前";
			} else if (days >= 31 && days <= 2 * 31) {
				ftime = "一个月前";
			} else if (days > 2 * 31 && days <= 3 * 31) {
				ftime = "2个月前";
			} else if (days > 3 * 31 && days <= 4 * 31) {
				ftime = "3个月前";
			} else {
				ftime = dateFormater2.get().format(time);
			}
			return ftime;
		}

		public static String friendly_time2(String sdate) {
			String res = "";
			if (StringUtils.isEmpty(sdate))
				return "";

			String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
			String currentData = getDataTime("MM-dd");
			int currentDay = StringUtils.toInt(currentData.substring(3));
			int currentMoth = StringUtils.toInt(currentData.substring(0, 2));

			int sMoth = StringUtils.toInt(sdate.substring(5, 7));
			int sDay = StringUtils.toInt(sdate.substring(8, 10));
			int sYear = StringUtils.toInt(sdate.substring(0, 4));
			Date dt = new Date(sYear, sMoth - 1, sDay - 1);

			if (sDay == currentDay && sMoth == currentMoth) {
				res = "今天 / " + weekDays[getWeekOfDate(new Date())];
			} else if (sDay == currentDay + 1 && sMoth == currentMoth) {
				res = "昨天 / " + weekDays[(getWeekOfDate(new Date()) + 6) % 7];
			} else {
				if (sMoth < 10) {
					res = "0";
				}
				res += sMoth + "/";
				if (sDay < 10) {
					res += "0";
				}
				res += sDay + " / " + weekDays[getWeekOfDate(dt)];
			}

			return res;
		}

		/**
		 * 获取当前日期是星期几<br>
		 * 
		 * @param dt
		 * @return 当前日期是星期几
		 */
		public static int getWeekOfDate(Date dt) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			return w;
		}

		/**
		 * 将字符串转位日期类型
		 * 
		 * @param sdate
		 * @return
		 */
		public static Date toDate(String sdate) {
			return toDate(sdate, dateFormater.get());
		}

		public static Date toDate(String sdate, SimpleDateFormat dateFormater) {
			try {
				return dateFormater.parse(sdate);
			} catch (ParseException e) {
				return null;
			}
		}

		public static String getDateString(Date date) {
			return dateFormater.get().format(date);
		}

		/**
		 * 返回当前系统时间
		 */
		public static String getDataTime(String format) {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(new Date());
		}

	
	
	/**
	 * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
	 * @return
	 */
	public static boolean isInEasternEightZones() {
		boolean defaultVaule = true;
		if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
			defaultVaule = true;
		else
			defaultVaule = false;
		return defaultVaule;
	}

	/**
	 * 根据不同时区，转换时间 2014年7月31日
	 * @param time
	 * @return
	 */
	public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
		Date finalDate = null;
		if (date != null) {
			int timeOffset = oldZone.getOffset(date.getTime())
					- newZone.getOffset(date.getTime());
			finalDate = new Date(date.getTime() - timeOffset);
		}
		return finalDate;
	}
}
