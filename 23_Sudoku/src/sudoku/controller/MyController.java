package sudoku.controller;

import sudoku.model.SudokuBoard;

public class MyController extends AbstractController{

	
	public MyController(SudokuBoard sud) {
		super(sud);
	}

	@Override
	public String getCellLabel(int row, int col) {
		if (sudoku.getCell(row, col).isEmpty())
			return " ";
		int num = sudoku.getCell(row, col).getAsInt();
		
		return num + "";
	}

	@Override
	public boolean setCell(int row, int col, String value) {
		if (value.isEmpty() || value.isBlank())
			return sudoku.setCell(row, col, 0);
		return sudoku.setCell(row, col, Integer.parseInt(value));
	}
	

}
