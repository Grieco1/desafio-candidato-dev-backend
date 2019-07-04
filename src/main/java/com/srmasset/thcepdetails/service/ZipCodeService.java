package com.srmasset.thcepdetails.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.srmasset.thcepdetails.domain.AddressModel;

/**
 * Using the
 * API:https://zuul.trusthub.com.br/orchestrator/v1/obter-endereco-por-cep/ , to
 * return the ZipCode data.
 * 
 * @author Ricardo Grieco
 */

@Cacheable
@Service
public class ZipCodeService {

	private static final RestTemplate restTemplate = new RestTemplate();

	@Value("${zipcode.endpoint}")

	private String endpoint;

	/**
	 * makes a request to the api using the zipcode
	 * 
	 * @param zipCode
	 * @return All adress information about the zipcode
	 */

	public AddressModel getAddressData(String zipCode) {

		if (!zipCodeValidate(zipCode)) {

			return null;
		}
		String urlZipCode = endpoint + zipCode;
		ResponseEntity<AddressModel> response = restTemplate.getForEntity(urlZipCode, AddressModel.class);
		AddressModel body = response.getBody();
		
		if (body.getState() == null ) {			
			return null;
		}		
		
		body.setZipcode(zipCode);

		return body;
	}
	
	/**
	 * makes a request to the api using a list of zipcodes
	 * 
	 * @param zipCode
	 * @return a ZipCode List info. (Post)
	 */
	public List<AddressModel> getAddressListData(List<String> zipCodes) {

		ArrayList<AddressModel> returnList = new ArrayList<AddressModel>();

		AddressModel result;

		for (String zipCode : zipCodes) {

			result = getAddressData(zipCode);

			if (result != null) {
				returnList.add(result);
			}
		}
		return returnList;
	}

	public boolean zipCodeValidate(String zipcode) {

		if (zipcode != null && !zipcode.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
