package model.entities;

public class BrazilTaxService implements TaxService {

	@Override
	public double tax(double amount) {
		
		double total = 0.0;
		if (amount <= 100.0) {
			total = amount * 0.2;
		}
		else {
			total = amount * 0.15;
		}
		return total;
		
	}
	
}
