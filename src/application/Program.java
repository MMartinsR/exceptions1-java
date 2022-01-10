package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		Integer resRoom = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		Date resInDate = sdf.parse(sc.next());
		System.out.print("Check-out date (dd/MM/yyyy): ");
		Date resOutDate = sdf.parse(sc.next());
		
		if (!resOutDate.after(resInDate)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date.");
		} else {
			Reservation reservation = new Reservation(resRoom, resInDate, resOutDate);
			System.out.print("Reservation: " + reservation);
			
			System.out.println("\n");
			
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			resInDate = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			resOutDate = sdf.parse(sc.next());
			
			Date now = new Date();
			if (resInDate.before(now) || resOutDate.before(now)) {
				System.out.println("Error in reservation: Reservation dates for update must be future dates.");
			} else if (!resOutDate.after(resInDate)) {
				
				System.out.println("Error in reservation: Check-out date must be after check-in date.");
			} else {
				reservation.updateDates(resInDate, resOutDate);
				System.out.print("Reservation: " + reservation);
			}	
		}
		sc.close();
	}

}
