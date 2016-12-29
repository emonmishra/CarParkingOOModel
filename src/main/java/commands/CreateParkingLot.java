package main.java.commands;

import java.util.List;

public class CreateParkingLot extends GenericCommand {

	public CreateParkingLot(String commandName, List<String> args) {
		super(commandName, args);
	}

	@Override
	public void execute() {

		Integer slotSize = Integer.parseInt(args.get(0));
		getParkingLotManager().createParkingLot(slotSize);

	}

}
