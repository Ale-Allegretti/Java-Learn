package fondt2.tlc;

import java.util.Arrays;

public class PhonePlan {
    private String name;
    private Rate[] rates;

    public PhonePlan(String name, Rate[] rates) {
        this.name = name;
        this.rates = Arrays.copyOf(rates, rates.length);
    }

    public String getName() {
        return name;
    }
    
    private Rate getRate(PhoneCall call) {
		for(Rate r: rates) 
			if(r.isApplicableTo(call.getDestNumber())) 
				return r;
		
		return null;
	}
    
    public double getCallCost(PhoneCall call) {
    	Rate toUse = this.getRate(call);
		if (toUse == null)
			return -1;
		else
			return toUse.getCallCost(call);
	}

	public boolean isValid() {
		for (Rate r : this.rates) {
			if (r.isValid())  {
				return true;
			}
		}
		return false;
	}
    
}
