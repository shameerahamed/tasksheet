package com.task.utils;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getDate() {		
		return DateToString(new java.util.Date());
	}
	
	public static String DateToString(Date inDate,String toTemplate) {
		FieldPosition f = new FieldPosition(2);
		SimpleDateFormat sd = new SimpleDateFormat(toTemplate);
		StringBuffer sb = new StringBuffer();		
		StringBuffer sb2 = new StringBuffer();		
		sb = sd.format(inDate,sb2,f);
		return sb.toString();
	}
	
	public static String DateToString(Date inDate) {
		String toTemplate = "dd-MMM-yy";
		return DateToString(inDate,toTemplate);
	}
	
	public static Date textToDate(String inDate, String toTemplate){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(toTemplate);
            return formatter.parse(inDate);
        }
        catch( Exception e ){
            e.printStackTrace();
                return null;
        }
    }
	
	public static String getMonthLong(String date) {
		return DateToString(textToDate(date),"MMMMM");
	}
	
	
	public static String getMonthShort(String date) {
		return DateToString(textToDate(date),"MMM");
	}
	
	public static String getYearLong(String date) {
		return DateToString(textToDate(date),"yyyy");
	}
	
	
	public static String getYearShort(String date) {
		return DateToString(textToDate(date),"yy");
	}	
	
	public static String getMonthYearLong(String date) {
		return getMonthLong(date)+"-"+getYearLong(date);
	}
	
	public static String getMonthYearShort(String date) {
		return getMonthShort(date)+"-"+getYearShort(date);
	}
	
	
	public static Date textToDate(String inDate) {
		String toTemplate = "dd-MMM-yy"; 
		return textToDate(inDate, toTemplate);		
	}
	
	public static int compareDate(String fromDate,String toDate)
	{
		int i=textToDate(fromDate,"dd-MMM-yy").compareTo(textToDate(toDate,"dd-MMM-yy"));
		return i;
	}
	
	public static boolean isFuture(String Date)
	{
		int i=textToDate(Date, "dd-MMM-yy").compareTo(textToDate(getDate(), "dd-MMM-yy"));
		return i>0;
	}
	
	public static String reportDate(String fromDatestr,String toDatestr,String titleType){		
		int fromMonth = textToDate(fromDatestr).getMonth();
		int fromYear = textToDate(fromDatestr).getYear();
		int toMonth = textToDate(toDatestr).getMonth();
		int toYear = textToDate(toDatestr).getYear();
		
		if(fromYear == toYear) {
			if(fromMonth == toMonth) {
				if(titleType.equals(UIConstants.reportName)) {
					return getMonthYearLong(fromDatestr);
				} else {
					return getMonthYearShort(fromDatestr);
				}
			} else {
				if(titleType.equals(UIConstants.reportName)) {
					return getMonthLong(fromDatestr)+" to "+getMonthYearLong(toDatestr);
				} else {
					return getMonthShort(fromDatestr)+" to "+getMonthYearShort(toDatestr);
				}
			}
		} else {			
				return getMonthYearShort(fromDatestr)+" to "+getMonthYearShort(toDatestr);			
		}			
	}
}
