package de.mayflower.timmy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.nfc.FormatException;

public class DateFormatter {

	static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	public static String convertToString(Date date) {
		
		return format1.format(date);
		
	}
	
	public static Date convertToDate(String dateString) {
		
		try {
			return format1.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		
	}
	
}
