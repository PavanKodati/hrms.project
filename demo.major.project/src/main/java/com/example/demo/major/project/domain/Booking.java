package com.example.demo.major.project.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Booking {
	@Id
	@GeneratedValue
	private Integer id;

	private String status = "BOOKED";

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date bookingDate;

	@JsonIgnore
	@ManyToOne
	private User user;

	@JsonIgnore
	@ManyToOne
	private Slot slot;

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public Integer getSlot_bookingId() {
		return id;
	}

	public void setSlot_bookingId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	
	public Booking() {
		super();
	}

	public Booking(Integer id, String status, Date bookingDate, User user, Slot slot) {
		super();
		this.id = id;
		this.status = status;
		this.bookingDate = bookingDate;
		this.user = user;
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Slot_booking [id=" + id + ", status=" + status + ", bookingDate=" + bookingDate
				+ "]";
	}

}
