package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Invoice;
import model.entities.RentalService;
import model.entities.Vehicle;

public class Program {

	public static void main(String[] args) throws ParseException {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		
		Vehicle vehicle = new Vehicle(carModel);
		
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();

		System.out.println("FATURA:");
		
		CarRental carRental = new CarRental(start, finish, vehicle, new Invoice());
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay);
		rentalService.processInvoice(carRental);
		
		System.out.printf("Pagamento basico: %.2f%n", carRental.getInvoice().getBasicPayment());
		System.out.printf("Imposto: %.2f%n", carRental.getInvoice().getTax());
		System.out.printf("Pagamento total: %.2f%n", carRental.getInvoice().totalPayment());
		
		sc.close();
	}
}
