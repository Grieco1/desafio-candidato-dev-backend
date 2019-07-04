package com.srmasset.thcepdetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srmasset.thcepdetails.domain.AddressModel;
import com.srmasset.thcepdetails.service.ZipCodeService;

/**
 * Controller class for the zipcode endpoint
 * @author Ricardo Grieco
 *
 */

@RestController
@RequestMapping("/zipcode")
public class ZipCodeController {

	@Autowired
	private ZipCodeService zipCodeService;

	/**
	 * Method that  expect a String variable
	 * 
	 * @param zipCode
	 * @return All adress information about the zipcode
	 */
	@GetMapping("/{zipcode}")
	public ResponseEntity<AddressModel> getAddressData(@PathVariable String zipcode) {
		return new ResponseEntity<>(zipCodeService.getAddressData(zipcode), HttpStatus.OK);
	}
	
	/**
	 * Method that sends a requisition and expect a body with a list of string 
	 * 
	 * @param zipCode
	 * @return a List of adresses information about 
	 */
	@PostMapping
	public ResponseEntity<List<AddressModel>> getAddressListData(@RequestBody List<String> zipcodes) {
		return new ResponseEntity<>(zipCodeService.getAddressListData(zipcodes), HttpStatus.OK);
	}
}
