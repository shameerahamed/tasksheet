package com.task.dao;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;

public class DateUtils {
	public static String getDate() {
		FieldPosition f = new FieldPosition(2);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sb = new StringBuffer();		
		StringBuffer sb2 = new StringBuffer();		
		sb = sd.format(new java.util.Date(),sb2,f);
		return sb.toString();
	}
	
	public static java.util.Date textToDate(String inDate, String toTemplate){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(toTemplate);
            return formatter.parse(inDate);
        }
        catch( Exception e ){
            e.printStackTrace();
                return null;
        }
    }
	
	public static int compareDate(String fromDate,String toDate)
	{
		int i=textToDate(fromDate,"dd-mmm-yyyy").compareTo(textToDate(toDate,"dd-mmm-yyyy"));
		return i;
	}
	
	public static boolean isFuture(String Date)
	{
		int i=textToDate(Date, "dd-mmm-yyyy").compareTo(textToDate(getDate(), "dd-mmm-yyyy"));
		return i>0;
	}
}
