package battleship.model;

import java.util.Arrays;

public class ShipBoard {
	private ShipItem[][] board;
	private static final int DIM = 8;

	public ShipBoard() {
		board = new ShipItem[DIM][DIM];
		Arrays.stream(board).forEach(arr -> Arrays.fill(arr, ShipItem.EMPTY));
	}
	
	public ShipBoard(String eightLines) {
		board = new ShipItem[DIM][DIM];
		String[] lines = eightLines.split("\\r?\\n");
		for (int i=0; i<DIM; i++) setCellRow(i, lines[i]);
	}

	public void setCellRow(int row, String line) {
		//System.out.println("CHECK:: |" + line + "|");
		String[] items = line.split("\\s");
		//System.out.println("CHECK:: " + Arrays.asList(items));
		if (row < 0 || row >= DIM || items.length!=DIM)
			throw new IllegalArgumentException("Errore nei parametri");
		for (int col = 0; col < DIM; col++) {
				board[row][col] = ShipItem.of(items[col].trim());
		}
	}

	public ShipItem getCell(int row, int col) {
		if (row < 0 || row >= DIM || col < 0 || col >= DIM)
			throw new IllegalArgumentException("Errore nei parametri");
		return board[row][col];
	}

	public int getSize() {
		return DIM;
	}
	
	public int countShipCellsInRow(int row) {
		int res = 0;
		for (int col = 0; col < DIM; col++) {
			if (getCell(row, col).compareTo(ShipItem.EMPTY) != 0 &&
					getCell(row, col).compareTo(ShipItem.SEA) != 0)
				res++;
		}
		return res;
	}

	public int countShipCellsInColumn(int column) {
		int res = 0;
		for (int row = 0; row < DIM; row++) {
			if (getCell(row, column).compareTo(ShipItem.EMPTY) != 0 &&
					getCell(row, column).compareTo(ShipItem.SEA) != 0)
				res++;
		}
		return res;
	}
	
	public int[] getCountingRow() {
		int[] res = new int[DIM];
		for (int col = 0; col < DIM; col++) {
			res[col] = countShipCellsInColumn(col);
		}
		return res;
	}

	public int[] getCountingCol() {
		int[] res = new int[DIM];
		for (int row = 0; row < DIM; row++) {
			res[row] = countShipCellsInRow(row);
		}
		return res;
	}
	
	public void clearCell(int row, int col)	{
		if (row < 0 || row >= DIM || col < 0 || col >= DIM )
			throw new IllegalArgumentException("Errore nei parametri");
		   board[row][col]=ShipItem.EMPTY;
	}
	
	public void setCell(int row, int col, ShipItem item) {
		if (row < 0 || row >= DIM || col < 0 || col >= DIM)
			throw new IllegalArgumentException("Errore nei parametri");
		board[row][col] = item;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < DIM * DIM; k++) {
			sb.append(board[k / DIM][k % DIM].getValue());
			sb.append(k % DIM == DIM - 1 ? System.lineSeparator() : ' ');
		}
		return sb.toString();
	}

}
