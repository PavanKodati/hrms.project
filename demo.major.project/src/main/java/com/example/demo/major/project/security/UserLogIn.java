package com.example.demo.major.project.security;

import java.util.List;

import com.example.demo.major.project.repository.Bookings;

public class UserLogIn {

	private String jwt;

	private List<Bookings> bookings;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public List<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(List<Bookings> bookings) {
		this.bookings = bookings;
	}

	public UserLogIn(String jwt, List<Bookings> bookings) {
		super();
		this.jwt = jwt;
		this.bookings = bookings;
	}

	public UserLogIn() {
		super();
	}

	@Override
	public String toString() {
		return "UserLogIn [jwt=" + jwt + ", bookings=" + bookings + "]";
	}



}
