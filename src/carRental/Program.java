package carRental;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import carRental.entities.CarRental;
import carRental.entities.Vehicle;
import carRental.services.BrazilTaxService;
import carRental.services.RentalService;

public class Program {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Enter rental data ...");
        System.out.print("Car model: ");
        String car = sc.nextLine();

        System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
        Date start = sdf.parse(sc.nextLine());

        System.out.print("Return (dd/MM/yyyy hh:mm): ");
        Date finish = sdf.parse(sc.nextLine());

        CarRental cr = new CarRental(start, finish, new Vehicle(car));

        System.out.print("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rs = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());

        rs.processInvoice(cr);

        System.out.println("Invoice:");
        System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));

        sc.close();
    }
}