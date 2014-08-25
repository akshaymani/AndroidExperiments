package com.paypal.cal.report;

import java.util.HashMap;
import java.util.Map;

public enum NoOfDaysInMonth {

	JANUARY 	(1,31),
	FEBRUARY 	(2,28),
	MARCH 		(3,31),
	APRIL 		(4,30),
	MAY 		(5,31),
	JUNE		(6,30),
	JULY		(7,31),
	AUGUST 		(8,31),
	SEPTEMBER	(9,30),
	OCTOBER		(10,31),
	NOVEMBER	(11,30),
	DECEMBER 	(12,31);
	
	private int index;
	private int days;
	
	private static Map<Integer, Integer> indexToDaysMapping;
	
	private NoOfDaysInMonth(int index, int days) {
		this.index = index;
		this.days = days;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getDays() {
		return this.days;
	}
	
	public static int getDaysinMonth(int key) {
		if (null == indexToDaysMapping ) {
			initMapping();
		}
		return indexToDaysMapping.get(key);
	}

	private static void initMapping() {
		indexToDaysMapping = new HashMap<Integer,Integer>();
		for (NoOfDaysInMonth n : NoOfDaysInMonth.values()) {
			indexToDaysMapping.put(n.index, n.days);
		}
	}
}
