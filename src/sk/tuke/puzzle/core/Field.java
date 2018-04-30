package sk.tuke.puzzle.core;

import java.util.Random;

public class Field {
	private final int rowCount;

	private final int columnCount;
	
	private final Tile[][] tiles;

	private Coordinate emptyTileCoordinate;
	
	private final int GENERATE_MOVES = 1000;
	
	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];
		generate();
	}

	public int getRowCount() {
		return rowCount;
	}
	
	public int getColumnCount() {
		return columnCount;
	}
	
	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}
	
	public boolean move(Direction direction) {
		int row = emptyTileCoordinate.getRow() - direction.getDeltaRow();
		int column = emptyTileCoordinate.getColumn() - direction.getDeltaColumn();
		if(row >= 0 && row < rowCount && column >= 0 && column < columnCount) {
			return move(tiles[row][column].getValue());
		}
				
		return false;
	}

	public boolean move(int tile) {
		if(!(tile > 0 && tile < rowCount * columnCount))
			return false;
		Coordinate tileCoordinate = getTileCoordinate(tile);
		int dr = Math.abs(tileCoordinate.getRow() - emptyTileCoordinate.getRow());
		int dc = Math.abs(tileCoordinate.getColumn() - emptyTileCoordinate.getColumn());
		if((dr == 1 && dc == 0) || (dr == 0 && dc == 1)) {
			tiles[emptyTileCoordinate.getRow()][emptyTileCoordinate.getColumn()] = new Tile(tile);
			tiles[tileCoordinate.getRow()][tileCoordinate.getColumn()] = null;
			emptyTileCoordinate = tileCoordinate;
			return true;
		}
		return false;
	}
	
	private Coordinate getTileCoordinate(int tileNumber) {
		for(int row = 0; row < getRowCount(); row++) {
			for(int column = 0; column < getColumnCount(); column++) {
				Tile tile = tiles[row][column];
				if(tile != null && tile.getValue() == tileNumber)
					return new Coordinate(row, column);
			}
		}
		return null;
	}

	public boolean isSolved() {
		int tile = 1;
		for(int row = 0; row < getRowCount(); row++) {
			for(int column = 0; column < getColumnCount(); column++) {
				if(tiles[row][column] != null && tiles[row][column].getValue() != tile) 
					return false;
				tile++;
			}
		}
		return true;
	}
	
	private void generate() {
		initField();
		generateRandomMoves();
	}

	private void generateRandomMoves() {
		Random random = new Random(); 
		for(int i = 0; i < GENERATE_MOVES * rowCount * columnCount;) {
			if(move(random.nextInt(rowCount * columnCount - 1) + 1))
					i++;
		}
	}

	private void initField() {
		int tile = 1;
		for(int row = 0; row < getRowCount(); row++) {
			for(int column = 0; column < getColumnCount(); column++) {
				tiles[row][column] = new Tile(tile);
				tile++;
			}
		}
		tiles[rowCount - 1][columnCount - 1] = null;
		emptyTileCoordinate = new Coordinate(rowCount - 1, columnCount - 1);
	}
}
