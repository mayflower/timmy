package de.mayflower.timmy;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.text.SimpleDateFormat;


public class Request {
	protected String description;
	protected HashMap<String, Object> params;
	protected Calendar calendarStart;
	protected Calendar calendarEnd;
	protected String timePattern;
	protected String timeStart;
	protected String timeEnd;
	protected SimpleDateFormat formatter;
	
	public static final int START = 0;
	public static final int END = 1;
	
	Request() {
		this.description = new String();
		this.params = new HashMap<String, Object>();
		
		this.calendarStart = Calendar.getInstance();
		this.calendarEnd = Calendar.getInstance();
		
		this.timePattern = "\\d{2}:\\d{2}";
		this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.123'Z'");
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStartTime(String time) {
		setTime(time, Request.START);
	}
	public void setEndTime(String time) {
		setTime(time, Request.END);
	}
	
	protected boolean setTime(String time, int which) {
		if (time.matches(this.timePattern)) {
			String[] parts = time.split(":");
			if (which == Request.START) {
				this.calendarStart.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
				this.calendarStart.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
				
				return true;
			} else if (which == Request.END) {
				this.calendarEnd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
				this.calendarEnd.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
				
				return true;
			}
		}
		return false;
	}
	
	public HashMap<String, Object> format() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		this.formatter.setTimeZone(tz);
		this.params.put("description", this.description);
		this.params.put("start", this.formatter.format(this.calendarStart.getTime()));
		this.params.put("end", this.formatter.format(this.calendarEnd.getTime()));
		
		return this.params;
	}
}
