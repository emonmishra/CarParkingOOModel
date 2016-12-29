package test.java.manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;
import main.java.manager.ParkingLotManager;
import main.java.model.Car;

public class TestParkingLotManager {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ParkingLotManager.getInstance());
	}

	@Test
	public void testCreateParkingLot() {
		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);

		boolean expected[] = { true, true, true, true };
		Assert.assertArrayEquals(expected, parkingManager.getDbManager().getDatabase().getCarSlots());
		Assert.assertNotNull(parkingManager.getDbManager().getDatabase().getSlotToCarMap());

	}

	@Test
	public void testParkingFull() {
		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		Assert.assertFalse(parkingManager.isParkingFull());
	}

	@Test
	public void testPark() {

		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);
		Car car = new Car("KA­01­HH­1234", "White");
		parkingManager.park(car);

		Assert.assertFalse(parkingManager.getDbManager().getDatabase().getCarSlots()[0]);
		Assert.assertSame(car, parkingManager.getDbManager().getDatabase().getSlotToCarMap().get(1));
	}

	@Test
	public void testParkWhenFull() {

		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(2);
		Car car1 = new Car("KA­01­HH­1234", "White");
		Car car2 = new Car("KA­01­BB­0001", "Black");
		Car car3 = new Car("KA­01­BB­0043", "Blue");
		parkingManager.park(car1);
		parkingManager.park(car2);

		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		parkingManager.park(car3);
		Assert.assertEquals("Sorry, parking lot is full", outContent.toString().trim());
	}

	@Test
	public void testLeave() {

		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);
		Car car1 = new Car("KA­01­HH­1234", "White");
		parkingManager.park(car1);

		Car car2 = new Car("KA­01­BB­0001", "Black");
		parkingManager.park(car2);

		parkingManager.leave(1);

		Assert.assertTrue(parkingManager.getDbManager().getDatabase().getCarSlots()[0]);
		Assert.assertNull(parkingManager.getDbManager().getDatabase().getSlotToCarMap().get(1));
	}

	@Test
	public void testLeave_Negative() {
		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);
		Car car1 = new Car("KA­01­HH­1234", "White");
		parkingManager.park(car1);

		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		parkingManager.leave(2);
		Assert.assertEquals("Sorry, there is no car at slot no: 2", outContent.toString().trim());
	}

	@Test
	public void testGetCarRegNosByColor() {
		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);
		Car car1 = new Car("KA­01­HH­1234", "White");
		parkingManager.park(car1);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		parkingManager.getCarRegNosByColor("White");
		Assert.assertEquals("KA­01­HH­1234,", outContent.toString().trim());
	}

	@Test
	public void testGetSlotNosByColor() {
		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);
		Car car1 = new Car("KA­01­HH­1234", "White");
		parkingManager.park(car1);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		parkingManager.getSlotNosByColor("White");
		Assert.assertEquals("1,", outContent.toString().trim());
	}

	@Test
	public void testGetSlotNosByRegNo() {
		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);
		Car car1 = new Car("KA­01­HH­1234", "White");
		parkingManager.park(car1);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		parkingManager.getSlotNosByReg("KA­01­HH­1234");
		Assert.assertEquals("1", outContent.toString().trim());
	}

	@Test
	public void testGetSlotNosByRegNo_Negative() {
		ParkingLotManager parkingManager = ParkingLotManager.getInstance();
		parkingManager.createParkingLot(4);
		Car car1 = new Car("KA­01­HH­1234", "White");
		parkingManager.park(car1);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		parkingManager.getSlotNosByReg("KA­01­HH­5555");
		Assert.assertEquals("Not found", outContent.toString().trim());
	}

}
