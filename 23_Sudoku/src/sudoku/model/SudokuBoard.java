package sudoku.model;

import java.util.OptionalInt;

public class SudokuBoard {

	private OptionalInt[][] board;
	private static int DIM = 9;
	private int fullCellNumber;
	
	
	public SudokuBoard() {
		this.board = new OptionalInt[DIM][DIM];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++)
				this.board[i][j] = OptionalInt.empty();
		}
		// Arrays.stream(board).forEach(arr -> Arrays.fill(arr, OptionalInt.empty()));
		this.fullCellNumber = DIM * DIM;
	}
	
	public boolean isFull() {
		boolean is = true;
		for (int i = 0; i < DIM; i++) 
			for (int j = 0; j < DIM; j++) 
				if (this.board[i][j].isEmpty())
					is = false;
				
		return is;
	}
	
	public void setCellRow(int row, int[] numValues) {
		if (row > 8 || row < 0)
			throw new IllegalArgumentException("Riga inesistente");
		if (numValues.length > fullCellNumber)
			throw new IllegalArgumentException("Valori in eccesso");
		if (numValues.length < 9)
			throw new IllegalArgumentException("Valori mancanti");
		for (int i = 0; i < DIM; i++) {
			if(numValues[i] < 0 || numValues[i] > 9 )
				throw new IllegalArgumentException("Valore non concesso");
			if(numValues[i] == 0)
				this.board[row][i] = OptionalInt.empty();
			else
				this.board[row][i] = OptionalInt.of(numValues[i]);
		}
	}
	
	public OptionalInt getCell(int row, int col) {
		if (row > 8 || row < 0 || col > 8 || col < 0)
			throw new IllegalArgumentException("Cella inesistente");
		
		return this.board[row][col];
	}
	
	public boolean setCell(int row, int col, int digit) {
		if (row > 8 || row < 0 || col > 8 || col < 0)
			throw new IllegalArgumentException("Cella inesistente");
		if (digit < 0 || digit > 9 )
			throw new IllegalArgumentException("Valore non concesso");
		
		boolean is = true;
		if (digit == 0) {
			this.board[row][col] = OptionalInt.empty();
			return is;
		}
		
		int num;
		for (int i = 0; i < DIM; i++) {
			if (!this.board[row][i].isEmpty()) {
				num = this.board[row][i].getAsInt();
				if (digit == num)
					is = false;
			}
		}
		for (int j = 0; j < DIM; j++) {
			if (!this.board[j][col].isEmpty()) {
				num = this.board[j][col].getAsInt();
				if (digit == num)
					is = false;
			}
		}
		
		if (is) 
			this.board[row][col] = OptionalInt.of(digit);
		
		return is;
	}
	
	public int getSize() {
		return DIM;
	}
	
	public int getEmpyCellNumber() {
		int res = 0;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (this.board[i][j].isEmpty())
					res++;
			}
		}
		return res;
	}
	
	public void clearCell(int row, int col) {
		if (row > 8 || row < 0 || col > 8 || col < 0)
			throw new IllegalArgumentException("Cella inesistente");
		this.board[row][col] = OptionalInt.empty();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SudokuBoard: \n");
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (this.board[i][j].isEmpty()) {
					builder.append("“ ”");
					builder.append("\t");
				}
				else {
					builder.append(this.board[i][j].getAsInt());
					builder.append("\t");
				}
			}
			builder.append(System.lineSeparator());
		}
		
		return builder.toString();
	}
	
	
	
	
	
	
}
