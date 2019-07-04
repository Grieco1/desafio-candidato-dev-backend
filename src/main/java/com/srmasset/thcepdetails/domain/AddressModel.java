package com.srmasset.thcepdetails.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Represents the Address information
 * 
 * @author Ricardo Grieco
 *
 */
@Data
public class AddressModel {

	@JsonProperty("estado")
	private String state;

	@JsonProperty("bairro")
	private String district;

	@JsonProperty("cidade")
	private String city;

	@JsonProperty("loghradouro")
	private String publicArea;

	@JsonProperty("cep")
	private String zipcode;
}