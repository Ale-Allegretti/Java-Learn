package battleship.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import battleship.model.ShipBoard;
import battleship.model.ShipItem;

public class MyBattleshipReader implements BattleshipReader {

	private ShipBoard playerBoard;
	private ShipBoard solutionBoard;
	
	public MyBattleshipReader() {
		this.playerBoard = new ShipBoard();
		this.solutionBoard = new ShipBoard();
	}
	
	protected ShipBoard readBoard(Reader reader, String admissibleChars) throws BadFileFormatException, IOException {
		if (reader == null)
			throw new BadFileFormatException("Reader nullo");
		BufferedReader buffReader = new BufferedReader(reader);
		String line;
		ShipBoard resultBoard = new ShipBoard();
		int row = 0;
		int numLines = 1;
		while ((line = buffReader.readLine()) != null) {
			if (numLines > 8)
				throw new BadFileFormatException("Numero di linee diverso dalle attese (= 8)");
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			if (tokenizer.countTokens() != 8)
				throw new BadFileFormatException("Numero di token diverso dalle attese (= 8)");
			for (int col = 0; col < 8; col++) {
					String itemsString = tokenizer.nextToken();
					if (itemsString.contains("\t"))
						throw new BadFileFormatException("Spazio eccessivo non previsto");
					if (!admissibleChars.contains(itemsString.trim()))
						throw new BadFileFormatException("Carattere " + itemsString + " non concesso");
					ShipItem shipItem = ShipItem.of(itemsString.trim());
					resultBoard.setCell(row, col, shipItem);
			}
			numLines++;
			row++;
		}
		 return resultBoard;
	}
	
	@Override
	public ShipBoard getSolutionBoard(Reader reader) throws BadFileFormatException, IOException {
		if (this.solutionBoard == null)
			this.solutionBoard = readBoard(reader, "< > ^ v x o ~");
		return this.solutionBoard;
	}

	@Override
	public ShipBoard getPlayerBoard(Reader reader) throws BadFileFormatException, IOException {
		if (this.playerBoard == null)
			this.playerBoard = readBoard(reader, "< > ^ v x o #");
		return this.playerBoard;
	}


}
