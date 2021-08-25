package battleship.controller;

import battleship.model.ShipBoard;
import battleship.model.ShipItem;


public class MyController extends AbstractController {

	public MyController(ShipBoard solutionBoard, ShipBoard initialBoard) {
		super(solutionBoard, initialBoard);
	}

	@Override
	public int verify() {
		int result = 0;
		int emptyCell = 0;
		int sameCell = 0;
		for (int row = 0; row < playerBoard.getSize(); row++) {
			for (int col = 0; col < playerBoard.getSize(); col++) {
				if (getPlayerBoard().getCell(row, col) == ShipItem.EMPTY)
					emptyCell++;
				else if (getPlayerBoard().getCell(row, col) != getSolutionBoard().getCell(row, col))
					result++;
				else 
					sameCell++;
			}
		}
		System.out.println(result);
		System.out.println(emptyCell);
		if (emptyCell == 0 && sameCell == 64)
			this.gameOver = true;
		return result;
	}

	

}
