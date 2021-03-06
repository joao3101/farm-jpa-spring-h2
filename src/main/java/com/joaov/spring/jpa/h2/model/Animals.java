package com.joaov.spring.jpa.h2.model;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "farm_id")
	private Farm farm;

	@Column(name = "tag")
	private String tag;

	public Animals() {

	}

	public Animals(String tag, Farm farm) {
		this.tag = tag;
		this.farm = farm;
	}

	public Long getId() {
		return id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", tag=" + tag + "]";
	}

}