package com.example.demo.major.project.controller;

public class RestApiConfig {
	
	public final static String 	SIGNIN="/signin";
	public final static String	SIGNUP="/signup";

	
	public final static String BUILDING ="/buildings";
	public final static String BUILDING_CHANGES ="/building/{buildingNumber}";	
	public final static String BUILDING_NAME ="/building/search/{buildingName}";	
	public final static String BUILDING_PIN ="/building/{pincode}";
	
	
	public final static String SLOTS="/slots/{buildingNumber}";
	public final static String SLOT_CHANGES="/slot/{buildingNumber}/{slotNumber}";
	
	
	public final static String AVALIABILITY_CHANGES="/avaliability/{buildNumber}/{slotNo}";
	public final static String AVALIABILITY="/availability/{buildNumber}/{date}";
	
	
	public final static String BOOKING="/booking/{buildNumber}/{slotNo}/{date}";
	public final static String CANCEL_BOOKING="/booking/{slot_bookingId}";
}
