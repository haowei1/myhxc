package com.hdy.myhxc.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 取得时间用的util类
 *
 */
public final class DateUtil {


	/**
	 * 获取当前时间
	 *
	 * @return 当前时间
	 */
	public static Date currentTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 获取当前时间的字符串
	 *
	 * @return 当前时间字符串
	 */
	public static String currentTimeInString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(Calendar.getInstance().getTime());
	}
	public static String currentYearInString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(Calendar.getInstance().getTime());
	}
	
	public static String currentMonthInString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(Calendar.getInstance().getTime());
	}
	/**
	 * 获取当前时间的字符串
	 *
	 * @return 当前时间字符串
	 */
	public static String currentDateTimeInString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm");
		return format.format(Calendar.getInstance().getTime());
	}
	/**
	 * 检查日期是否合法
	 *
	 * @param dateStr 日期
	 * @param format 格式
	 * @return 合法：true，非法：false
	 */
	public static boolean checkValidDate(String dateStr, String format) {
		if (!StringUtils.hasText(dateStr))
			return false;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);

		try {
			@SuppressWarnings("unused")
			Date date = sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	public static boolean isBelong(){

	    SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
	    Date now =null;
	    Date beginTime = null;
	    Date endTime = null;
	    try {
	        now = df.parse(df.format(new Date()));
	        beginTime = df.parse("08:00");
	        endTime = df.parse("09:00");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    Boolean flag = belongCalendar(now, beginTime, endTime);
	    return flag;
	}

    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
