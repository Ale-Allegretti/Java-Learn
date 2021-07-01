package tris.persistence;

import java.io.PrintWriter;

import tris.model.Match;

public class MyMatchPrinter implements MatchPrinter {
	
	@Override
	public void print(PrintWriter os, Match match) { 
		os.println(match);
		os.flush();
	}	
	
}
