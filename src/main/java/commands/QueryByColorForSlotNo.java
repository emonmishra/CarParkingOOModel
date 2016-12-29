package main.java.commands;

import java.util.List;

public class QueryByColorForSlotNo extends GenericCommand{


	public QueryByColorForSlotNo(String commandName, List<String> args) {
		super(commandName, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		getParkingLotManager().getSlotNosByColor(args.get(0));
		
	}

}
