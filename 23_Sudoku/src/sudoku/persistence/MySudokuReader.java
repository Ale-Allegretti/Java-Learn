package sudoku.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

import sudoku.model.SudokuBoard;

public class MySudokuReader implements SudokuReader {

	private SudokuBoard sudoku;
	
	public MySudokuReader(Reader reader) throws IOException, BadFileFormatException {
		try (BufferedReader fin = new BufferedReader(reader)) {
			this.sudoku = parseGrid(fin);
		}
		catch (IOException e) {
			throw new BadFileFormatException(e);
		}
	}
	
	private SudokuBoard parseGrid(BufferedReader reader) throws IOException, BadFileFormatException {
		SudokuBoard sud = new SudokuBoard();
		String line;
		int row = 0;
		while((line = reader.readLine()) != null) {
			String[] items = line.split("\\t+");
			if (items.length != sud.getSize())
				throw new BadFileFormatException("Wrong number of items in line: " + line);
		
			try {
				String values[] = Arrays.stream(items).map(String::trim).toArray(String[]::new);
				for (String s : values) {
					if (s.contentEquals("0")) {
						throw new BadFileFormatException("Invalid value 0");
					}
				}
				int[] numValues = Arrays.stream(values)
											.map( s -> s.equals("#") ? "0" : s)
											.mapToInt(Integer::parseInt).toArray();
				sud.setCellRow(row, numValues);
			}
			catch(NumberFormatException e) {
				throw new BadFileFormatException("Bad items in line: " + line);
			}
			catch(IllegalArgumentException e) {
				throw new BadFileFormatException("Too many lines in file or wrong content");
			}
			row++;
		}
         if(row != sud.getSize()) 
        	 throw new BadFileFormatException("Not enough lines");
         
         return sud;
	}
	
	@Override
	public SudokuBoard getSudoku() {
		return sudoku;
	}
	
	

}
