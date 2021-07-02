package minesweeper.persistence;

import java.io.PrintWriter;
import minesweeper.model.Game;


public class MyGameSaver implements GameSaver {

	private PrintWriter pw;
	
	public MyGameSaver(PrintWriter printWriter) {
		this.pw = printWriter;
	}
	
	@Override
	public void print(Game match) {
		pw.print(match);
	}

	@Override
	public void close() {
		pw.close();
	}

}
