package com.example.timetable;

public class Module {
	
	private String code;
	private String name;
	private String selectLL;
	private String week;
	private String selectTime1;
	private String selectTime2;
	private String location;
	private String comment;
	
	public Module(String code, String name, String selectLL, String week, String selectTime1, String selectTime2,
			String location, String comment) {
		this.code = code;
		this.name = name;
		this.selectLL = selectLL;
		this.week = week;
		this.selectTime1 = selectTime1;
		this.selectTime2 = selectTime2;
		this.location = location;
		this.comment = comment;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelectLL() {
		return selectLL;
	}

	public void setSelectLL(String selectLL) {
		this.selectLL = selectLL;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getSelectTime1() {
		return selectTime1;
	}

	public void setSelectTime1(String selectTime1) {
		this.selectTime1 = selectTime1;
	}

	public String getSelectTime2() {
		return selectTime2;
	}

	public void setSelectTime2(String selectTime2) {
		this.selectTime2 = selectTime2;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
