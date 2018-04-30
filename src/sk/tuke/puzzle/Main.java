package sk.tuke.puzzle;

import sk.tuke.puzzle.consoleui.ConsoleUI;
import sk.tuke.puzzle.core.Field;

public class Main {

	public static void main(String[] args) {
		Field field = new Field(4, 3);
		ConsoleUI ui = new ConsoleUI(field);
		ui.run();
	}
}
