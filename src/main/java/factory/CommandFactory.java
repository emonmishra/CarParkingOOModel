package main.java.factory;

import java.util.List;

import main.java.commands.Command;
import main.java.commands.CreateParkingLot;
import main.java.commands.Leave;
import main.java.commands.Park;
import main.java.commands.QueryByColorForRegNo;
import main.java.commands.QueryByColorForSlotNo;
import main.java.commands.QueryByRegNoForSlotNo;
import main.java.commands.Status;

public class CommandFactory {

	public Command createCommand(String commandName, List<String> args) {
		switch (commandName) {
		case "create_parking_lot":
			if (args.size() != 1 || !args.get(0).matches("\\d+"))
				return null;
			return new CreateParkingLot("commandName", args);
		case "park":
			if (args.size() != 2)
				return null;
			return new Park(commandName, args);
		case "leave":
			if (args.size() != 1 || !args.get(0).matches("\\d+"))
				return null;
			return new Leave(commandName, args);
		case "status":
			if (args.size() != 0)
				return null;
			return new Status(commandName, args);
		case "registration_numbers_for_cars_with_colour":
			if (args.size() != 1)
				return null;
			return new QueryByColorForRegNo(commandName, args);
		case "slot_numbers_for_cars_with_colour":
			if (args.size() != 1)
				return null;
			return new QueryByColorForSlotNo(commandName, args);
		case "slot_number_for_registration_number":
			if (args.size() != 1)
				return null;
			return new QueryByRegNoForSlotNo(commandName, args);

		default:
			return null;
		}

	}
}
