package com.leeneko.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss", Locale.getDefault());
	
	public static boolean checkDate(String sDate) {
		String _sDate = validDateTime(sDate);
		
		String year = _sDate.substring(0, 4);
		String month = _sDate.substring(4, 6);
		String day = _sDate.substring(6);
		
		return checkDate(year, month, day);
	}
	
	public static String validDateTime(String sDate) {
		String _sDate = sDate;
		
		if (sDate == null || !(sDate.trim().length() == 8 || sDate.trim().length() == 10)) {
			throw new IllegalArgumentException("Invalid date format: " + sDate);
		}
		
		if (sDate.length() == 10) {
			_sDate = removeMinusChar(sDate);
		}
		
		return _sDate;
	}
	
	public static String removeMinusChar(String sDate) {
		return sDate.replaceAll("-", "");
	}
	
	public static boolean checkDate(String year, String month, String day) {
		try {
			Date date = dateFormatter.parse(year + month + day);
			String result = dateFormatter.format(date);
			
			if (result.equalsIgnoreCase(year + month + day)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
//			e.printStackTrace();
			return false;
		}
	}
	
	public static String addDate(String sDate, int year, int month, int day) {
		String _sDate = validDateTime(sDate);
		
		Calendar calendar = Calendar.getInstance();
		
		try {
			calendar.setTime(dateFormatter.parse(_sDate));
		} catch (ParseException pe) {
			throw new IllegalArgumentException("Invalid date format: " + sDate);
		}
		
		if (year != 0) {
			calendar.add(Calendar.YEAR, year);
		}
		if (month != 0) {
			calendar.add(Calendar.MONTH, month);
		}
		if (day != 0) {
			calendar.add(Calendar.DATE, day);
		}
		
		return dateFormatter.format(calendar.getTime());
	}
	
	public static String addYear(String sDate, int year) {
		return addDate(sDate, year, 0, 0);
	}
	
	public static String addMonth(String sDate, int month) {
		return addDate(sDate, 0, month, 0);
	}
	
	public static String addDay(String sDate, int day) {
		return addDate(sDate, 0, 0, day);
	}
	
	public static int getDaysDiff(String sDate1, String sDate2) {
		String _sDate1 = validDateTime(sDate1);
		String _sDate2 = validDateTime(sDate2);
		
		if (!checkDate(sDate1) || !checkDate(sDate2)) {
			throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + ", args[1]=" + sDate2);
		}
		
		Date date1;
		Date date2;
		
		try {
			date1 = dateFormatter.parse(_sDate1);
			date2 = dateFormatter.parse(_sDate2);
		} catch (ParseException pe) {
			throw new IllegalArgumentException("Invalid date format: args[0]=" + _sDate1 + ", args[1]=" + _sDate2);
		}
		
		int days1 = (int) ((date1.getTime() / 3600000) / 24);
		int days2 = (int) ((date2.getTime() / 3600000) / 24);
		
		return days2 - days1;
	}
	
	public static String getCurrentDate() {
		Date now = new Date();
		return dateFormatter.format(now);
	}
	
	public static String getCurrentDateTime() {
		Date now = new Date();
		return dateTimeFormatter.format(now);
	}
	
	public static String getDbDate() {
		try {
			return DbUtils.selectOne("SELECT to_char(current_date, 'YYYYMMDD') as date");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getDbDateTime() {
		try {
			return DbUtils.selectOne("SELECT to_char(current_timestamp, 'YYYY-MM-DD HH24:MI:SS') as datetime");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
