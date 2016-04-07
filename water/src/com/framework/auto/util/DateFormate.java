package com.framework.auto.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate 
{
	public static SimpleDateFormat directoryStyle = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	public static SimpleDateFormat standardStyle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat ymdStyle = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateStyle = new SimpleDateFormat("HH:mm:ss:SSS");
	
	public static String Format(Date date) 
	{
		try{
		return standardStyle.format(date);
		}catch (Exception ex)
		{
			System.out.println("Error when Format(Date date), return default date (new Date). ");
			return standardStyle.format(new Date());
		}
	}
	
	public static String ymdFormat(Date date)
	{
		return ymdStyle.format(date);
	}
	
	public static String directoryFormat(Date date)
	{
		return directoryStyle.format(date);
	}
	
	public static String FormatTime(long ms) 
	{
		StringBuffer sb = new StringBuffer();
		int hour;
		int minute;
		int second;
		long timeInSecond;
		int temp;
		timeInSecond = ms / 1000;

		hour = (int) timeInSecond / 3600;
		hour = hour % 24;
		temp = (int) timeInSecond % 3600;
		minute = temp / 60;
		second = temp % 60;
		sb.append(hour).append("hours--").append(minute).append("minutes--").append(second).append("seconds");
		return sb.toString();
	}

}
