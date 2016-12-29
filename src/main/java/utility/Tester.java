package main.java.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Tester {

	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		

		StringJoiner slots = new StringJoiner(", ");
		for (Integer s : list) {
			slots.add(s.toString());
		}
		
		System.out.println(slots.toString());

	}

}
