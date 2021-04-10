package fondt2.tlc;

public class PhonePlan {
    private String name;
    private Rate[] rates;

    public PhonePlan(String name, Rate[] rates) {
        this.name = name;
        this.rates = rates;
    }

    public String getName() {
        return name;
    }
    
    public double getCallCost(PhoneCall call) {
		return 0.2;
	}

	public boolean isValid() {
		return true;
	}
    
}
