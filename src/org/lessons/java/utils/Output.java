package org.lessons.java.utils;

public class Output {
	public static String CYAN = "\u001B[36m";
	public static String YELLOW = "\u001B[33m";
	public static String GREEN = "\u001B[32m";
	public static String RESET = "\u001B[0m";

	public static void printSeparator() {
		System.out.println("\n---------------------------------------------------\n");
	}

	public static void printGoodbye() {
		System.out.println(Output.GREEN + "Arriverderci!" + Output.RESET);
		printSeparator();
	}
}
