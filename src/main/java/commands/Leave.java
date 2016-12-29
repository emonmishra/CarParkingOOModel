package main.java.commands;

import java.util.List;

public class Leave extends GenericCommand {

	public Leave(String commandName, List<String> args) {
		super(commandName, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		int slotNo = Integer.parseInt(args.get(0));
		getParkingLotManager().leave(slotNo);

	}

}
