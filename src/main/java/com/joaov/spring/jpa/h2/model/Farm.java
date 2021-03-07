package com.joaov.spring.jpa.h2.model;

import javax.persistence.*;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "farms")
public class Farm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Código da fazenda")
	private long id;

	@Column(name = "name")
	@ApiModelProperty(value = "Nome da fazenda")
	@NotNull
	private String name;

	public Farm() {

	}

	public Farm(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Farm [id=" + id + ", name=" + name + "]";
	}

}
