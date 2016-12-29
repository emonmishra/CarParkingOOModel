package main.java.commands;

import java.util.List;

public class Query extends GenericCommand {

	public Query(String commandName, List<String> args) {
		super(commandName, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		switch (commandName) {
		case "registration_numbers_for_cars_with_colour":
			getParkingLotManager().getCarRegNosByColor(args.get(0));
			break;
		case "slot_numbers_for_cars_with_colour":
			getParkingLotManager().getSlotNosByColor(args.get(0));
			break;
		case "slot_number_for_registration_number":
			getParkingLotManager().getSlotNosByReg(args.get(0));
			break;
		default:
			System.out.println("Invalid Command");
		}

	}

}
