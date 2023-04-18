package com.example.demo.major.project.DTO;

import com.example.demo.major.project.domain.Avaliability;

public class SlotDTO {

	
	private Integer id;

	private String slotNumber;

	private String floornumber = "1";

	private String divisionNo = "1";

	private Avaliability avaliability;
	
	public Avaliability getAvaliability() {
		return avaliability;
	}

	public void setAvaliability(Avaliability avaliability) {
		this.avaliability = avaliability;
	}

	

	public SlotDTO(Integer id, String slotNumber, String floornumber, String divisionNo, Avaliability avaliability) {
		super();
		this.id = id;
		this.slotNumber = slotNumber;
		this.floornumber = floornumber;
		this.divisionNo = divisionNo;
		this.avaliability = avaliability;
	}

	@Override
	public String toString() {
		return "SlotDto [id=" + id + ", slotNumber=" + slotNumber + ", floornumber=" + floornumber + ", divisionNo="
				+ divisionNo + "]";
	}

	public SlotDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getFloornumber() {
		return floornumber;
	}

	public void setFloornumber(String floornumber) {
		this.floornumber = floornumber;
	}

	public String getDivisionNo() {
		return divisionNo;
	}

	public void setDivisionNo(String divisionNo) {
		this.divisionNo = divisionNo;
	}

}
