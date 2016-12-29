package main.java.model;

public class ParkingLotStatus {

	private int slotNo;
	private String regNo;
	private String color;

	public ParkingLotStatus(int slotNo, String regNo, String color) {
		this.slotNo = slotNo;
		this.regNo = regNo;
		this.color = color;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public String getRegNo() {
		return regNo;
	}

	public String getColor() {
		return color;
	}

}
