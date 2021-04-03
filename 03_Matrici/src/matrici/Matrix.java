package matrici;

public class Matrix {
	
	private double[][] values;

	public Matrix(double[][] values) {
		this.values = new double[values.length][values[0].length];
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getCols(); j++) {
				this.setValue(i, j, values[i][j]);
			}
		}
	}

	private Matrix(int rows, int cols){
		this.values = new double[rows][cols];
	}
	
	
	
	public int getCols() {
		return this.values[0].length;
	}

	public int getRows() {
		return this.values.length;
	}
	
	public double getValue(int row, int col) {
		return this.values[row][col];
	}

	private void setValue(int row, int col, double value) {
		this.values[row][col] = value;
	}
	
	public boolean isSquared() {
		return this.getRows() == this.getCols();
	}
	
	
	public Matrix sum(Matrix that) {
		if (that.getRows() == this.getRows() && that.getCols() == this.getCols()) {
			Matrix c = new Matrix(that.getRows(), that.getCols());
			for (int i=0; i < that.getRows(); i++)
				for (int j=0; j < that.getCols(); j++)
					c.values[i][j] = this.values[i][j] + that.values[i][j];
			return c;
			}
			else 
				return null;
	}

	public Matrix mul(Matrix that) {
		if (that.getRows() == this.getCols()) {
			Matrix c = new Matrix(that.getRows(), this.getCols());
			for (int i = 0; i < that.getRows(); i++)
				for (int j = 0; j < that.getRows(); j++) {
					double somma = 0;
					for (int k = 0; k < that.getRows(); k++)
						somma = somma + this.values[i][k] * that.values[k][j];
					c.values[i][j] = somma;
				}
				return c;
			}
			else
				return null;
	}

	

	public Matrix extractMinor(int excludedRow, int excludedCol) {
		if (!this.isSquared() || excludedRow > this.getRows() || excludedCol > this.getCols()) {
			return null;
		}
		if (this.getRows() <= 1 || this.getCols() <= 1) {
			return this;
		}
		int x = 0, y;
		Matrix ris = new Matrix(this.getRows() - 1, this.getCols() - 1);
		for (int i = 0; i < this.getRows(); i++) {
			if (i != excludedRow) {
				y = 0;
				for (int j = 0; j < this.getRows(); j++) {
					if (j != excludedCol) {
						ris.setValue(x, y, this.getValue(i, j));
						y++;
					}
				}
				x++;
			}
		}
		return ris;
	}

	private double calcDet() { 
		double determinant = 0.0;
		if(this.getRows() == 1) {
			return this.getValue(0, 0);
		}
		for (int j = 0; j < this.getRows(); j++) {		// calcolo sempre cancellando la prima riga, colonne j variabili
			determinant += Math.pow(-1, j) * this.getValue(0, j) * this.extractMinor(0, j).det();
		}
		return determinant;
	}

	public double det() {
		return isSquared() ? calcDet() : Double.NaN;
	}
	
	public Matrix extractSubMatrix(int rowStart, int colStart, int dimRow, int dimCol) {
		if(rowStart + dimRow > this.getRows() || colStart + dimCol > this.getCols()) {
			return null; 
		}
		Matrix ris = new Matrix(dimRow, dimCol);
		for (int i = rowStart; i < rowStart + dimRow; i++) {
			for (int j = colStart; j < colStart + dimCol; j++) {
				ris.setValue(i - rowStart, j - colStart, this.getValue(i, j));
			}
		}
		return ris;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getCols(); j++) {
				sb.append(this.getValue(i, j) + "\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
