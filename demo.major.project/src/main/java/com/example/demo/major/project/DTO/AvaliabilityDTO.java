package com.example.demo.major.project.DTO;

import java.util.Date;
import java.util.List;

import com.example.demo.major.project.domain.Booking;

public class AvaliabilityDTO {

	private Integer id;

	private Date fromDate;

	private Date toDate;

	private List<Booking> bookings;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public AvaliabilityDTO(Integer id, Date fromDate, Date toDate, List<Booking> bookings) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.bookings = bookings;
	}

	public AvaliabilityDTO() {
		super();
	}

	@Override
	public String toString() {
		return "AvaliabilityDTO [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", bookings=" + bookings
				+ "]";
	}

}
