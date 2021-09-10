package classiinnerlocali;

public class StanzaStandard {
	private double mq;
	private String accessori;
	
	public StanzaStandard(double mq, String accessori) {
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
