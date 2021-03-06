package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	// CONSTANTS
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  // one instance only
	
	// ATRIBUTES
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	// CONSTRUCTORS
	public Reservation() {
		
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates must be future dates.");	
		}
		
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date.");			
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	// GETTER/SETTER
	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}


	
	// CLASS METHODS
	public long duration() {  // days
		
		long diff = checkOut.getTime() - checkIn.getTime();  // returns the amount of milisseconds of a given date
		
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);  // converts a milissecond time unit to days
		
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates.");	
		} 
		
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date.");			
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;		
	}
	
	@Override
	public String toString() {
		
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}

}
