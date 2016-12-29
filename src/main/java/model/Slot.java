package main.java.model;

public class Slot {

	private Status status;
	private Car car;
	private int slotNo;

	public Slot(int slotNo) {
		this.slotNo = slotNo;
	}

	public Status getStatus() {
		return status;
	}

	public Car getCar() {
		return car;
	}

	public void parkCar(Car car) {
		this.car = car;
		this.status = Status.OCCUPIED;
	}

	public Car unparkCar() {
		Car removedCar = this.car;
		this.car = null;
		this.status = Status.FREE;
		return removedCar;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public static enum Status {

		FREE, OCCUPIED;
	}

}
