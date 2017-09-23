package main;

import view.IView;

public class ConsoleMain {

	public static void main(String[] args) {
		IView start = new view.Console();
		start.startProgram();
	}
}
