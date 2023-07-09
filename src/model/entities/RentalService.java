package model.entities;

import java.util.concurrent.TimeUnit;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;
	
	private TaxService taxService;
	
	public RentalService() {
	}

	public RentalService(Double pricePerHour, Double pricePerDay) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = new BrazilTaxService();
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	public TaxService getTaxService() {
		return taxService;
	}
	
	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		long durationInMilisseconds = carRental.getFinish().getTime() - carRental.getStart().getTime();
		double minutesToHours = Math.ceil(TimeUnit.MILLISECONDS.toMinutes(durationInMilisseconds) / 60.0);	
		
		if (minutesToHours <= 12.0) {
			double total = pricePerHour * minutesToHours;
			double tax = taxService.tax(total);
			carRental.getInvoice().setBasicPayment(total);
			carRental.getInvoice().setTax(tax);
		}
		else {
			double minutesToDays = Math.ceil(TimeUnit.MILLISECONDS.toMinutes(durationInMilisseconds) / 60.0 / 24.0);
			double total = pricePerDay * minutesToDays;
			double tax = taxService.tax(total);
			carRental.getInvoice().setBasicPayment(total);
			carRental.getInvoice().setTax(tax);
		}
	}	
}
