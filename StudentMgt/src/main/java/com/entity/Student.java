package com.entity;

import java.util.Random;

import org.springframework.stereotype.Component;
@Component("student")
public class Student {
	private int stuId;
	private String stuName;
	private long stuMobileNo;
	private String stuCity;
	private boolean isActive;

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public long getStuMobileNo() {
		return stuMobileNo;
	}

	public void setStuMobileNo(long stuMobileNo) {
		this.stuMobileNo = stuMobileNo;
	}

	public String getStuCity() {
		return stuCity;
	}

	public void setStuCity(String stuCity) {
		this.stuCity = stuCity;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Student(String stuName, int stuMobileNo, String stuCity, boolean isActive) {
		super();
		this.stuId = new Random().nextInt(1000000);
		this.stuName = stuName;
		this.stuMobileNo = stuMobileNo;
		this.stuCity = stuCity;
		this.isActive = isActive;
	}

	public Student() {
		super();
	}
}
