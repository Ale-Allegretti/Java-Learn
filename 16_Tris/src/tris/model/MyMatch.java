package tris.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyMatch implements Match {

	private List<Board> states;
	private LocalDateTime matchID;
	
	public MyMatch() {
		this(LocalDateTime.now());
	}
	
	public MyMatch(LocalDateTime matchID) {
		this.matchID=matchID;
		states=new ArrayList<>();
		states.add(new MyBoard("         "));
	}
	
	public LocalDateTime getMatchID() {
		return matchID;
	}
	
	@Override
	public List<Board> getHistory() {
		return states;
	}

	@Override
	public void add(Board newState) {
		// c'è sempre già lo stato iniziale con scacchiera vuota, inserito dal costruttore
		Board currentState = states.get(states.size()-1);
		if (currentState.adjacent(newState)) {
			states.add(newState);
		}
		else {
			throw new IllegalArgumentException("mossa incoerente con lo stato attuale della scacchiera: " + currentState + " non può evolvere in " + newState);
		}
	}

	@Override
	public Board getCurrentState() {
		return states.get(states.size()-1);
	}
	
	@Override
	public Board getState(int n){
		if (n<0 || n>= states.size()) throw new IllegalArgumentException();
		return states.get(n);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matchID == null) ? 0 : matchID.hashCode());
		result = prime * result + ((states == null) ? 0 : states.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyMatch other = (MyMatch) obj;
		if (matchID == null) {
			if (other.matchID != null)
				return false;
		} else if (!matchID.equals(other.matchID))
			return false;
		if (states == null) {
			if (other.states != null)
				return false;
		} else if (!states.equals(other.states))
			return false;
		return true;
	}
	
	public String toString() {
		/*
		StringBuilder sb = new StringBuilder();
		for(Board board: states) {
			sb.append(board+System.lineSeparator());
		}
		sb.append(System.lineSeparator());
		return sb.toString();
		*/
		return this.getHistory().stream().map(board -> board.toString()).collect(Collectors.joining(System.lineSeparator()));
	}

}
