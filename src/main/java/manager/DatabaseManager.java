package main.java.manager;

import java.util.Map;
import main.java.database.ParkingDatabase;
import main.java.model.Car;
import main.java.model.Slot;

public class DatabaseManager {

	private ParkingDatabase database;
	private int parkingCapacity;
	private static DatabaseManager databaseManager;

	private DatabaseManager() {
		this.database = ParkingDatabase.getInstance();
	}

	public static DatabaseManager getInstance() {

		if (databaseManager == null) {
			databaseManager = new DatabaseManager();
		}

		return databaseManager;
	}

	public void initializeDB(int capacity) {
		this.parkingCapacity = capacity;
		this.database.initializeDB(capacity);
	}

	public boolean isDBFull() {
		return this.database.getSlotToCarMap().size() == this.parkingCapacity;
	}

	public void addCar(Car car, Slot slot) {
		this.database.getSlotToCarMap().put(slot.getSlotNo(), car);
	}

	public Car removeCar(int slotNo) {

		this.database.getCarSlots()[slotNo - 1] = true;
		Car car = this.database.getSlotToCarMap().remove(slotNo);
		return car;
	}

	public void updateDB(Car car, Slot slot) {

		updateSlotStatus(slot);
		addCar(car, slot);

	}

	public Map<Integer, Car> getParkedCars() {

		return this.database.getSlotToCarMap();
	}

	public void updateSlotStatus(Slot slot) {

		if (slot.getStatus().equals(Slot.Status.OCCUPIED)) {
			this.database.getCarSlots()[slot.getSlotNo() - 1] = false;
		}
		if (slot.getStatus().equals(Slot.Status.FREE)) {
			this.database.getCarSlots()[slot.getSlotNo() - 1] = true;
		}
	}

	public ParkingDatabase getDatabase() {
		return database;
	}

	public boolean isDatabaseReady() {
		return this.database.isReady();
	}

}
