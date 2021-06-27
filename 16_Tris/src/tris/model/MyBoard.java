package tris.model;



import java.util.Arrays;

public class MyBoard implements Board {

	private char[][] board;
	
	public MyBoard(char[][] board) {
		if (board.length != 3 || board[0].length != 3)
			throw new IllegalArgumentException("Struttura non valida");
		for(int k = 0; k < 3; k++) 
			if (board[k].length != 3) 
				throw new IllegalArgumentException("argomento non è matrice 3x3");
		for(int k = 0; k < 9; k++)
			if(!BoardValue.getAllValues().contains(String.valueOf(board[k / 3][k % 3]))) 
				throw new IllegalArgumentException("argomento " + board[k / 3][k % 3] + " non è O o X");
	
		board = Arrays.copyOf(board, 3);
	}
	
	public MyBoard(String sBoard) {
		if (sBoard.length() != 9)
			throw new IllegalArgumentException("Struttura non valida");
		for(int k=0; k < 9; k++) 
			if(!BoardValue.getAllValues().contains(sBoard.substring(k,k))) 
				throw new IllegalArgumentException("argomento " + sBoard.substring(k,k) + " non è O o X");
		board = new char [3][3];
		for(int k = 0; k < 9; k++) 
			board[k/3][k % 3] = sBoard.charAt(k);
	}
	
	public MyBoard() {
		board = new char [3][3];
	}
	
	public char getCC(int i, int k) {
		return this.board[i][k];
	}
	
	@Override
	public String getRow(int i) {
		if (i < 0 || i > 2)
			throw new IllegalArgumentException("Struttura non valida");
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < 3; k++) sb.append(board[i][k]);
		return sb.toString();
	}

	@Override
	public String getColumn(int i) {
		if (i < 0 || i > 2)
			throw new IllegalArgumentException("Struttura non valida");
				
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < 3; k++) 
			sb.append(board[k][i]);
		return sb.toString();
	}

	@Override
	public String getDiagonal(int i) {
		if (i < 0 || i > 2)
			throw new IllegalArgumentException("Struttura non valida");
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < 3; k++) 
			sb.append(board[k][(i == 0) ? k : 2 - k]);
		
		return sb.toString();
	}

	@Override
	public boolean winning() {
		for (int i = 0; i < 3; i++) {
			if (winningRow(i) || winningColumn(i) || winningDiagonal(i))
				return true;
		}
		return false;
	}
	
	public static boolean winningSequence(String seq) {
		char first = seq.charAt(0);
		return first != BoardValue.EMPTY.toString().charAt(0) && 
			   first == seq.charAt(1) && first == seq.charAt(2);
	}

	@Override
	public boolean winningRow(int i) {
		return winningSequence(getRow(i));
	}

	@Override
	public boolean winningColumn(int i) {
		return winningSequence(getColumn(i));
	}

	@Override
	public boolean winningDiagonal(int i) {
		return winningSequence(getDiagonal(i));
	}

	@Override
	public boolean adjacent(Board that) {
		long numberOfOs = that.toString().chars().filter(ch -> ch == 'O').count();
		long numberOfXs = that.toString().chars().filter(ch -> ch == 'X').count();		
		return this.distanceFrom(that) == 1 && Math.abs(numberOfOs - numberOfXs) <= 1;
	}

	@Override
	public int distanceFrom(Board that) {
		int distance=0;
		for (int i = 0; i < 3; i++) {
			String r1 = this.getRow(i), r2 = that.getRow(i);
			for (int j = 0; j < 3; j++)	
				distance += (r1.charAt(j) != r2.charAt(j) ? 1 : 0); 
		}
		return distance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MyBoard))
			return false;
		MyBoard other = (MyBoard) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < 9; k++) {
			sb.append(board[k/3][k%3]);
			sb.append(k % 3 == 2 ? System.lineSeparator() : '\t');
		}
		return sb.toString();
	}

}
