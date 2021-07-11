package tris.model;

import java.util.Arrays;

public class MyBoard implements Board {

	private char board[][];
	
	public MyBoard() {
		board = new char [3][3];
	}

	public MyBoard(char s[][]) {
		if (s.length!=3 || s[0].length!=3) throw new IllegalArgumentException("argomento non è matrice 3x3");
		for(int k = 0; k < 3; k++) 
			if (s[k].length!=3) throw new IllegalArgumentException("argomento non è matrice 3x3");
		//for(int k=0; k<9; k++) if(!"OX".contains(String.valueOf(s[k/3][k%3]))) 
		for(int k=0; k<9; k++) 
			if(!BoardValue.getAllValues().contains(String.valueOf(s[k/3][k%3]))) 
			throw new IllegalArgumentException("argomento " + s[k/3][k%3] + " non è O o X");
		board = Arrays.copyOf(s, 3);
	}

	public MyBoard(String s) {
		if (s.length()!=9) throw new IllegalArgumentException("argomento non è stringa lunga 9");
		//for(int k=0; k<9; k++) if(!"OX".contains(s.substring(k,k))) 
		for(int k=0; k<9; k++) if(!BoardValue.getAllValues().contains(s.substring(k,k))) 
			throw new IllegalArgumentException("argomento " + s.substring(k,k) + " non è O o X");
		board = new char [3][3];
		for(int k=0; k<9; k++) 
			board[k/3][k%3] = s.charAt(k);
	}
	
	public String getRow(int i) {
		if (i<0 || i>2) throw new IllegalArgumentException("indice di riga fuori range");
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<3; k++) sb.append(board[i][k]);
		return sb.toString();
	}
	
	public String getColumn(int i) {
		if (i<0 || i>2) throw new IllegalArgumentException("indice di colonna fuori range");
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<3; k++) sb.append(board[k][i]);
		return sb.toString();
	}

	public String getDiagonal(int i) {
		if (i<0 || i>1) throw new IllegalArgumentException("indice di diagonale fuori range");
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<3; k++) sb.append(board[k][(i==0) ? k : 2-k]);
		return sb.toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < 9; k++) {
			sb.append(board[k / 3][k % 3]);
			sb.append(k%3 == 2 ? System.lineSeparator() : '\t');
		}
		return sb.toString();
	}
	
	public boolean winning() {
		for(int k=0; k<3; k++) if(winningRow(k)) return true;
		for(int k=0; k<3; k++) if(winningColumn(k)) return true;
		for(int k=0; k<2; k++) if(winningDiagonal(k)) return true;
		return false;
	}

	public boolean winningRow(int i) {
		return winningSequence(getRow(i));
	}

	public boolean winningColumn(int i) {
		return winningSequence(getColumn(i));
	}
	
	public boolean winningDiagonal(int i) {
		return winningSequence(getDiagonal(i));
	}
	
	public static boolean winningSequence(String s) {
		char first = s.charAt(0);
		return first!=BoardValue.EMPTY.toString().charAt(0) && first==s.charAt(1) && first==s.charAt(2);
	}

	@Override
	public int distanceFrom(Board that) {
		int distance=0;
		for (int i=0; i<3; i++) {
			String r1 = this.getRow(i), r2 = that.getRow(i);
			for (int j=0; j<3; j++)	
				distance += (r1.charAt(j)!=r2.charAt(j) ? 1 : 0); 
		}
		return distance;
	}
	
	@Override
	public boolean adjacent(Board that) {
		// NB: this is supposed to be OK, we only check the validity of that 
		// So, the method MUST be called in the form currentState.adjacent(newState), NOT VICEVERSA
		// If you want symmetry, calculate also the numbers of Os and Xs in this (other than that)
		// and add them to tha final AND condition
		long numberOfOs = that.toString().chars().filter(ch -> ch =='O').count();
		long numberOfXs = that.toString().chars().filter(ch -> ch =='X').count();		
		return this.distanceFrom(that)==1 && Math.abs(numberOfOs-numberOfXs)<=1;
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
		if (!(obj instanceof Board)) return false;
		return this.distanceFrom((Board)obj)==0;
	}
	
}

