package com.learning.java.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locationstats")
public class LocationStats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name = "total_number")
	private int totalNumber;
	@Column(name = "diff_from_previous_day")
	private int diffFromPreviousDay;

	public int getDiffFromPreviousDay() {
		return diffFromPreviousDay;
	}

	public void setDiffFromPreviousDay(int diffFromPreviousDay) {
		this.diffFromPreviousDay = diffFromPreviousDay;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", totalNumber=" + totalNumber
				+ ", diffFromPreviousDay=" + diffFromPreviousDay + "]";
	}

}
