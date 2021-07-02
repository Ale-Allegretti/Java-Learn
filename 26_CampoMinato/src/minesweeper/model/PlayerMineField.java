package minesweeper.model;

public class PlayerMineField extends MineField {

	private int hiddenCells;
	
	public PlayerMineField(int size) {
		super(size);
		init();
	}
	
	@Override
	protected void init() {
		this.hiddenCells = this.getSize()*this.getSize();
		for (int i = 0; i < this.getSize(); i++)
			for (int j = 0; j < this.getSize(); j++)
				super.setCell(i, j, new Cell(CellType.HIDDEN));
	}
	
	protected void setCell(int n, int m, Cell cell) {
		if (cell.getType().compareTo(CellType.HIDDEN) == 0)
			throw new UnsupportedOperationException("Cella vuota sostituita con cella vuota - Errore");
		super.setCell(n, m, cell);
		this.hiddenCells--;
	}
	
	public int getHiddenCellsNumber() {
		return hiddenCells;
	}
	
	

}
