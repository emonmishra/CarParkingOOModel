package main.java.commands;

import java.util.List;

import main.java.manager.ParkingLotManager;

public abstract class GenericCommand implements Command {

	protected String commandName;
	protected List<String> args;
	protected ParkingLotManager parkingLotManager;

	GenericCommand(String commandName, List<String> args) {
		this.commandName = commandName;
		this.args = args;
	}

	protected ParkingLotManager getParkingLotManager() {
		return ParkingLotManager.getInstance();
	}

}
