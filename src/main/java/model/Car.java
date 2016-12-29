package main.java.model;

public class Car {

	private String color;
	private String regNo;

	public Car(String regNo, String color) {
		this.regNo = regNo;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
}
