package minesweeper.model;

public class RealMineField extends MineField {

	private int mines;
	private static int DEFAULT = 10;
	
	public RealMineField(int size) {
		this(size, DEFAULT);
	}
	
	public RealMineField(int size, int mines) {
		super(size);
		this.mines = mines;
		init();
	}
	
	public int getMinesNumber() {
		return mines;
	}
	
	private void calcAdjacentMines(int row, int col) {
		int jMin = (row==0) ? 0 : row-1; 
		int iMin = (col==0) ? 0 : col-1; 
		int jMax = (row==this.getSize()-1) ? row : row+1; 
		int iMax = (col==this.getSize()-1) ? col : col+1; 
		int res=0;
		for (int j=jMin; j <= jMax; j++)
			for (int i=iMin; i<=iMax; i++)
				if(i==col && j==row)
					continue;
				else if(this.getCell(j,i)!=null && this.getCell(j,i).getType()==CellType.MINE) 
					res++;
		this.setCell(row, col, new Cell(CellType.NUM,res));
	}

	
	@Override
	protected void init() {
		int createdMines = 0;
		while(createdMines < this.getMinesNumber()) {
			int row = (int) Math.floor(Math.random()*this.getSize());
			int col = (int) Math.floor(Math.random()*this.getSize());
			if (this.getCell(row,col)==null) {
				this.setCell(row, col, new Cell(CellType.MINE));
				createdMines++;
			}
		}
		for (int row = 0; row < this.getSize(); row++)
			for (int col = 0; col<this.getSize(); col++)
				if(this.getCell(row,col) == null || this.getCell(row,col).getType() == CellType.NUM) {
					calcAdjacentMines(row,col);
				}
	}
}
