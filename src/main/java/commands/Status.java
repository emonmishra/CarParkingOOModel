package main.java.commands;

import java.util.List;

public class Status extends GenericCommand {

	public Status(String commandName, List<String> args) {
		super(commandName, args);
	}

	@Override
	public void execute() {

		getParkingLotManager().status();

	}

}
