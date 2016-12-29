package main.java.commands;

import java.util.List;

public class QueryByRegNoForSlotNo extends GenericCommand {

	public QueryByRegNoForSlotNo(String commandName, List<String> args) {
		super(commandName, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		getParkingLotManager().getSlotNosByReg(args.get(0));

	}

}
