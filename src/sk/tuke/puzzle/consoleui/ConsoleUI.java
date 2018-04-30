package sk.tuke.puzzle.consoleui;

import java.util.Scanner;

import sk.tuke.puzzle.core.Direction;
import sk.tuke.puzzle.core.Field;
import sk.tuke.puzzle.core.Tile;

public class ConsoleUI {
	private final Field field;

	private final Scanner scanner = new Scanner(System.in);

	public ConsoleUI(Field field) {
		this.field = field;
	}

	public void run() {
		do {
			printField();
			processInput();
		} while (!field.isSolved());

		printField();

		System.out.println("You won!");
	}

	private void processInput() {
		System.out.print("Enter tile number to move or X to exit: ");
		String line = scanner.nextLine().toLowerCase().trim();
		switch (line) {
		case "x":
			System.exit(0);
			break;
		case "w":
			field.move(Direction.UP);
			break;
		case "a":
			field.move(Direction.LEFT);
			break;
		case "s":
			field.move(Direction.DOWN);
			break;
		case "d":
			field.move(Direction.RIGHT);
			break;
		default:
			try {
				int tile = Integer.parseInt(line);
				field.move(tile);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	private void printField() {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				System.out.print(" ");
				if (tile == null) {
					System.out.print("  ");
				} else {
					System.out.printf("%2d", tile.getValue());
				}
			}
			System.out.println();
		}
	}
}
