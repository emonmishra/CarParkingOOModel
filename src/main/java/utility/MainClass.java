package main.java.utility;

import java.util.Arrays;
import java.util.Scanner;

import main.java.commands.Command;
import main.java.factory.CommandFactory;
import main.java.manager.ParkingLotManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClass {

	static ParkingLotManager parkingLotManager = ParkingLotManager.getInstance();
	static CommandFactory commandFactory = new CommandFactory();
	static CommandExecutor commandExecutor = new CommandExecutor();

	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				handleFileBasedInput(args[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			handleInteractiveInput();
		}
	}

	private static void handleFileBasedInput(String filepath) throws IOException {

		filepath = filepath.trim();
		Path path = Paths.get(filepath);
		Files.lines(path, StandardCharsets.UTF_8).forEach(line -> {
			commonInputHandler(line);
		});

	}

	private static void handleInteractiveInput() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String input = scanner.nextLine();
			if (input.equals("q:")) {
				break;
			}
			commonInputHandler(input);
		}
		scanner.close();
	}

	private static void commonInputHandler(String input) {
		input = input.trim();
		String[] ip = input.split(" ");

		if (!parkingLotManager.isDBReady() && !ip[0].equals("create_parking_lot")) {
			System.out.println("Please initialize DB first using command: create_parking_lot <capacity>");
			return;
		}

		Command command = commandFactory.createCommand(ip[0], Arrays.asList(Arrays.copyOfRange(ip, 1, ip.length)));
		
		if(command == null){
			System.out.println("Invalid command, try again!");
			return;
		}
		commandExecutor.execute(command);
	}

}
