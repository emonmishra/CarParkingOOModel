package main.java.commands;

import java.util.List;

public class QueryByColorForRegNo extends GenericCommand{

	public QueryByColorForRegNo(String commandName, List<String> args) {
		super(commandName, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		getParkingLotManager().getCarRegNosByColor(args.get(0));
		
	}



}
