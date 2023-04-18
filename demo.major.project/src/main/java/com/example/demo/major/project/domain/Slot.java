package com.example.demo.major.project.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Slot {
	@Id
	@GeneratedValue
	private Integer id;

	private String slotNumber;

	private String floornumber = "1";

	private String divisionNo = "1";

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "avaliabilityId")
	private Avaliability avaliability;

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

	public Avaliability getAvaliability() {
		return avaliability;
	}

	public void setAvaliability(Avaliability avaliability) {
		this.avaliability = avaliability;
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", slotNumber=" + slotNumber + ", floornumber=" + floornumber + ", divisionNo="
				+ divisionNo + "]";
	}

	public Slot(Integer id, String slotNumber, String floornumber, String divisionNo, Avaliability avaliability) {
		super();
		this.id = id;
		this.slotNumber = slotNumber;
		this.floornumber = floornumber;
		this.divisionNo = divisionNo;
		this.avaliability = avaliability;
	}

	public Slot() {
		super();
	}

}
