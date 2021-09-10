package classiinnerlocali;

import java.util.ArrayList;
import java.util.List;

public class Appartamento {
	private int piano;
	
	private List<StanzaStandard> stanzeSt;
	private List<StanzaInner> stanzeIn;
	
	public void aggiungiStanza(double mq, String accessori) {
		class CalcolaPerimetro {
			public double calcola(double lato1, double lato2) {
				return lato1+lato2;
			}
		}
		
		CalcolaPerimetro cp = new CalcolaPerimetro();
		cp.calcola(3, 6);
		
		getStanzeIn().add(new StanzaInner(mq, accessori));
	}
	
	class StanzaInner {
		private double mq;
		private String accessori;
		
		public StanzaInner(double mq, String accessori) {
			super();
			this.mq = mq;
			this.accessori = accessori;
		}

		/**
		 * @return the mq
		 */
		public double getMq() {
			return mq;
		}

		/**
		 * @param mq the mq to set
		 */
		public void setMq(double mq) {
			this.mq = mq;
		}

		/**
		 * @return the accessori
		 */
		public String getAccessori() {
			return accessori;
		}

		/**
		 * @param accessori the accessori to set
		 */
		public void setAccessori(String accessori) {
			this.accessori = accessori;
		}
		
		
	}


	/**
	 * @return the piano
	 */
	public int getPiano() {
		return piano;
	}


	/**
	 * @param piano the piano to set
	 */
	public void setPiano(int piano) {
		this.piano = piano;
	}


	/**
	 * @return the stanzeSt
	 */
	public List<StanzaStandard> getStanzeSt() {
		if(stanzeSt == null) {
			stanzeSt = new ArrayList<StanzaStandard>();
		}
		
		return stanzeSt;
	}


	/**
	 * @param stanzeSt the stanzeSt to set
	 */
	public void setStanzeSt(List<StanzaStandard> stanzeSt) {
		this.stanzeSt = stanzeSt;
	}


	/**
	 * @return the stanzeIn
	 */
	public List<StanzaInner> getStanzeIn() {
		if(stanzeIn == null) {
			stanzeIn = new ArrayList<StanzaInner>();
		}
		
		return stanzeIn;
	}


	/**
	 * @param stanzeIn the stanzeIn to set
	 */
	public void setStanzeIn(List<StanzaInner> stanzeIn) {
		this.stanzeIn = stanzeIn;
	}
}
