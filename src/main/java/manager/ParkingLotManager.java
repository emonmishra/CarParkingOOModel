package main.java.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringJoiner;

import main.java.model.Car;
import main.java.model.ParkingLotStatus;
import main.java.model.Slot;

public class ParkingLotManager {

	private DatabaseManager dbManager;
	private static ParkingLotManager parkingLotManager;

	private ParkingLotManager() {
		this.dbManager = DatabaseManager.getInstance();
	}

	public static ParkingLotManager getInstance() {

		if (parkingLotManager == null) {
			parkingLotManager = new ParkingLotManager();
		}

		return parkingLotManager;
	}

	public void createParkingLot(int slots) {

		this.dbManager.initializeDB(slots);
		System.out.println("Created a parking lot with " + slots + " slots");
	}

	public void park(Car car) {

		if (isParkingFull()) {
			System.out.println("Sorry, parking lot is full");
		} else {
			int slotNo = getFreeSlot();
			Slot slot = new Slot(slotNo);
			slot.parkCar(car);
			this.dbManager.updateDB(car, slot);
			System.out.println("Allocated slot number:  " + slotNo);
		}
	}

	public void leave(int slotNo) {

		if (!this.dbManager.getDatabase().getCarSlots()[slotNo - 1]) {
			this.dbManager.removeCar(slotNo);
			System.out.println("Slot number " + slotNo + " is free");
		} else {
			System.out.println("Sorry, there is no car at slot no: " + slotNo);
		}
	}

	public void status() {

		List<ParkingLotStatus> parkedCars = getParkingLotStatus();
		System.out.println("Slot No.\t Registration No \t Colour");

		for (ParkingLotStatus status : parkedCars) {

			System.out.println(status.getSlotNo() + "\t\t" + status.getRegNo() + "\t\t" + status.getColor());
		}

	}

	public void getCarRegNosByColor(String color) {

		List<String> carRegNoByColor = fetchCarRegNosByColor(color);

		if (carRegNoByColor.isEmpty()) {
			System.out.print("Not found!");
		}
		StringJoiner regNos = new StringJoiner(", ");
		for (String s : carRegNoByColor) {
			regNos.add(s);
		}
		System.out.println(regNos.toString());

	}

	public void getSlotNosByColor(String color) {

		List<Integer> slotNoByColor = fetchSlotNosByColor(color);

		if (slotNoByColor.isEmpty()) {
			System.out.print("Not found!");
		}
		StringJoiner slots = new StringJoiner(", ");
		for (Integer slot : slotNoByColor) {
			slots.add(slot.toString());
		}
		System.out.println(slots.toString());

	}

	public void getSlotNosByReg(String regNo) {

		Integer slotNo = fetchSlotNoByRegNo(regNo);

		if (null != slotNo) {
			System.out.println(slotNo);
		} else {
			System.out.println("Not found");
		}

	}

	// Helper functions starts from here

	public Integer fetchSlotNoByRegNo(String regNo) {

		for (Entry<Integer, Car> entry : this.dbManager.getParkedCars().entrySet()) {

			if (entry.getValue().getRegNo().equalsIgnoreCase(regNo)) {

				return entry.getKey();
			}
		}

		return null;

	}

	public List<Integer> fetchSlotNosByColor(String color) {

		List<Integer> slotNos = new ArrayList<>();
		for (Entry<Integer, Car> entry : this.dbManager.getParkedCars().entrySet()) {

			if (entry.getValue().getColor().equalsIgnoreCase(color)) {

				slotNos.add(entry.getKey());
			}
		}
		return slotNos;
	}

	public List<String> fetchCarRegNosByColor(String color) {

		List<String> regNos = new ArrayList<>();
		for (Entry<Integer, Car> entry : this.dbManager.getParkedCars().entrySet()) {

			if (entry.getValue().getColor().equalsIgnoreCase(color)) {

				regNos.add(entry.getValue().getRegNo());
			}
		}
		return regNos;

	}

	public List<ParkingLotStatus> getParkingLotStatus() {

		List<ParkingLotStatus> parkedCars = new ArrayList<>();
		for (Entry<Integer, Car> entry : this.dbManager.getParkedCars().entrySet()) {

			ParkingLotStatus parkingLotStatus = new ParkingLotStatus(entry.getKey(), entry.getValue().getRegNo(),
					entry.getValue().getColor());
			parkedCars.add(parkingLotStatus);
		}
		return parkedCars;

	}

	public boolean isParkingFull() {
		return this.dbManager.isDBFull();
	}

	public int getFreeSlot() {

		boolean[] slots = this.dbManager.getDatabase().getCarSlots();
		int slot = Integer.MIN_VALUE;
		for (int i = 0; i < slots.length; i++) {
			if (slots[i]) {
				slot = i;
				break;
			}
		}

		return slot + 1; // because array is 0 index based
	}

	public boolean isDBReady() {
		return this.dbManager.isDatabaseReady();
	}

	public DatabaseManager getDbManager() {
		return dbManager;
	}

}
