package com.devsuperior.uri2602.dto;

import com.devsuperior.uri2602.projections.CustomerNameProjection;

public class CustomerNameDTO {
	
	private String name;
	
	public CustomerNameDTO () {}

	public CustomerNameDTO(String name) {
		this.name = name;
	} 
	
	// construtor que recebe a interface da projection
	public CustomerNameDTO(CustomerNameProjection projection) {
		this.name = projection.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CustomerNameDTO [name=" + name + "]";
	} 
	
	

}
