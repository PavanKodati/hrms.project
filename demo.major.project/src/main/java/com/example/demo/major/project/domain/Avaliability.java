package com.example.demo.major.project.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Avaliability {
	@Id
	@GeneratedValue
	private Integer id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date toDate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Booking.class)
	@JoinColumn(name = "avaliabilityId", referencedColumnName = "id")
	private List<Booking> bookings;

	

	

	public Avaliability(Integer id, Date fromDate, Date toDate, List<Booking> bookings) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Avaliability [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

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

	public Avaliability() {
		super();
	}

	

}
