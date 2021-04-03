package frazione;
import util.MyMath;

/**
 * Frazione come tipo di dato astratto (ADT)
 * 
 * @author Fondamenti di Informatica T-2
 * @version Feb 2021
 */
public class Frazione {
	private int num, den;

	/**
	 * Costruttore della Frazione
	 * 
	 * @param num
	 *            Numeratore
	 * @param den
	 *            Denominatore
	 */
	public Frazione(int num, int den) {
		boolean negativo = num * den < 0;
		this.num = negativo ? -Math.abs(num) : Math.abs(num);
		this.den = Math.abs(den);
	}

	/**
	 * Costruttore della Frazione
	 * 
	 * @param num
	 *            Numeratore
	 */
	public Frazione(int num) {
		this(num, 1);
	}

	/**
	 * Recupera il numeratore
	 * 
	 * @return Numeratore della frazione
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Recupera il denominatore
	 * 
	 * @return Denominatore della frazione
	 */
	public int getDen() {
		return den;
	}

	/**
	 * Calcola la funzione ridotta ai minimi termini.
	 * 
	 * @return Una nuova funzione equivalente all'attuale, ridotta ai minimi
	 *         termini.
	 */
	public Frazione minTerm() {
		if (getNum() == 0) 
			return new Frazione(getNum(), getDen());
		int mcd = MyMath.mcd(Math.abs(getNum()), getDen());
		int n = getNum() / mcd;
		int d = getDen() / mcd;
		return new Frazione(n, d);
	}

	public boolean equals(Frazione f) {
		return f.getNum() * this.den == f.getDen() * this.num;
	}
	
	public static int size(Frazione[] tutte) {
		int count = 0;
		for (int i = 0; i < tutte.length; i++) {
			if (tutte[i] != null)
				count++;
		}
		return count;
	}

	
	public Frazione sum(Frazione f) {
		int n = this.num * f.getDen() + this.den * f.getNum();
		int d = this.den * f.getDen();
		Frazione fSum = new Frazione(n, d);
		return fSum.minTerm();
	}
	
	public Frazione sumWithMcm(Frazione f){
		int mcm = MyMath.mcm(f.getDen(),this.den);
		int n = ((mcm / f.getDen()) * f.getNum()) + ((mcm / this.den) * this.num);
		int d = mcm;
		Frazione fSumWithMcm = new Frazione(n,d);
		return fSumWithMcm.minTerm();
	}
	
	public static Frazione sum(Frazione[] tutte) {
		Frazione sumTot = new Frazione(0, 0);
		for (int i = 0; i < tutte.length; i++) {
			sumTot = sumTot.sum(tutte[i]);
		}
		return sumTot;
	}
	
	public static Frazione[] sum(Frazione[] setA, Frazione[] setB) {
		if (size(setA) != size(setB)) 
			return null;
		Frazione[] result = new Frazione[size(setB)];
		for (int k = 0; k < result.length; k++) {
		result[k] = setA[k].sum(setB[k]);
		}
		return result;
	}
	
	
	public Frazione mul(Frazione f){
		int n = f.getNum() * this.num;
		int d = f.getDen() * this.den;
		Frazione fMul = new Frazione(n, d);
		return fMul.minTerm();
	}
	
	public static Frazione mul(Frazione[] tutte) {
		Frazione mulTot = new Frazione(1, 1);
		for (int i = 0; i < tutte.length; i++) {
			mulTot = mulTot.mul(tutte[i]);
		}
		return mulTot;
	}
	
	public static Frazione[] mul(Frazione[] setA, Frazione[] setB) {
		if (size(setA) != size(setB)) 
			return null;
		Frazione[] result = new Frazione[size(setB)];
		for (int k = 0; k < result.length; k++) {
			result[k] = setA[k].mul(setB[k]);
			}
		return result;
	}
		
	
	public Frazione div(Frazione f){
		int d = f.getNum() * this.den;
		int n = f.getDen() * this.num;
		Frazione fDiv = new Frazione(n,d);
		return fDiv.minTerm();
	}
	
	public Frazione reciprocal() {
		int n = this.den;
		int d = this.num;
		Frazione fReciprocal = new Frazione(n, d);
		return fReciprocal.minTerm();
	}
	
	public int compareTo(Frazione f) {
		int res = 0;
		if (f.getNum() * this.den == f.getDen() * this.num) 
			res = 0;
		if (f.getNum() * this.den > f.getDen() * this.num)
			res = 1;
		if (f.getNum() * this.den < f.getDen() * this.num)
			res = -1;
		return res;
	}
	
	public double getDouble() {
		double n = this.num;
		double d = this.den;
		double res = n / d;
		return res;
	}
	
	@Override
	public String toString() {
		if (getNum() == 0)
			return "" + getNum();
		else
			return getNum() + "/" + getDen();
	}

}
