package main.java.commands;

import java.util.List;

import main.java.model.Car;

public class Park extends GenericCommand {

	private Car car;

	public Park(String commandName, List<String> args) {
		super(commandName, args);
		this.car = new Car(args.get(0),args.get(1));
	}

	@Override
	public void execute() {
		getParkingLotManager().park(car);
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
