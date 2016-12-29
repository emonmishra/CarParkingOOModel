package main.java.database;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import main.java.model.Car;

public class ParkingDatabase {

	private Map<Integer, Car> slotToCarMap;

	private boolean[] carSlots; // true = vacant, false = occupied
	private static ParkingDatabase database;

	private boolean ready;

	private ParkingDatabase() {

	}

	public static ParkingDatabase getInstance() {
		if (database == null) {
			database = new ParkingDatabase();
		}
		return database;
	}

	public void initializeDB(int parkingCapacity) {
		this.carSlots = new boolean[parkingCapacity];

		for (int i = 0; i < parkingCapacity; i++) {
			this.carSlots[i] = true;
		}

		this.slotToCarMap = new ConcurrentHashMap<>(parkingCapacity, 1, 1);
		this.ready = true;
	}

	public boolean[] getCarSlots() {
		return carSlots;
	}

	public Map<Integer, Car> getSlotToCarMap() {
		return slotToCarMap;
	}

	public boolean isReady() {
		return ready;
	}

}
