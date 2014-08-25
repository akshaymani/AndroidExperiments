package com.paypal.cal.report;

public class DateUtility {

	public static final String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	public static String getMonth(int month) {
		return months[month-1];
	}

	/**
	 * 
	 * @param date in dd/mm/yyyy format
	 * @return Year
	 */
	public static int getYearFromDate(String date) {
		return Integer.valueOf(date.split("/")[2]);
	}

	/**
	 * 
	 * @param date in dd/mm/yyyy format
	 * @return Month
	 */
	public static int getMonthFromDate(String date) {
		return Integer.valueOf(date.split("/")[1]);
	}

	/**
	 * 
	 * @param date in dd/mm/yyyy format
	 * @return Day
	 */
	public static int getDayFromDate(String date) {
		return Integer.valueOf(date.split("/")[0]);
	}
	
	/**
	 * 
	 * @param year
	 * @return true if it's a Leap year else return false
	 */
	public static boolean isLeapyear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		} else {
			return false;
		}
	}

	private static String generateIncrementalDate(int currentday, int currentmonth,
			int currentyear) {
		
		String newdate = null;
		
		boolean is_leapYear = isLeapyear(currentyear);
		
		int noofdays_currentmonth = NoOfDaysInMonth.getDaysinMonth(currentmonth);
		
		if (is_leapYear && currentmonth == 2) {
			noofdays_currentmonth += 1; // For the month of February
		}
		
		int diff = noofdays_currentmonth - (currentday + 1 );
		
		if (diff < 0 && currentmonth <= 11) {
			newdate = Integer.toString(-diff)+"/"+Integer.toString(currentmonth+1)+"/"+currentyear;
		}
		else if(diff < 0 && currentmonth == 12) {
			newdate = Integer.toString(-diff)+"/"+Integer.toString(1)+"/"+currentyear;
		}
		else {
			newdate = Integer.toString(currentday + 1) + "/" + currentmonth + "/" + currentyear;
		}
		
		return newdate;		
	}
	
	public static String generateIncrementalDay(int currentday, int currentmonth,
			int currentyear) {
		String date = generateIncrementalDate(currentday,currentmonth,currentyear);
		return date.split("/")[0];
	}


	public static String generateIncrementalMonth(int currentday, int currentmonth,
			int currentyear) {
		String date = generateIncrementalDate(currentday,currentmonth,currentyear);
		return date.split("/")[1];
	}

	public static String generateIncrementalYear(int currentday, int currentmonth,
			int currentyear) {
		String date = generateIncrementalDate(currentday,currentmonth,currentyear);
		return date.split("/")[2];
	}
	
}
