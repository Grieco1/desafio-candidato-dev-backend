package com.srmasset.thcepdetails;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.srmasset.thcepdetails.controller.ZipCodeController;
import com.srmasset.thcepdetails.domain.AddressModel;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ZipCodeControllerTest {

	@Autowired
	private ZipCodeController zipCodeController;

	/**
	 * This test will send a true/correct zipcode
	 */
	@Test
	public void validGetAddressData() {

		AddressModel result = zipCodeController.getAddressData("03821010").getBody();
		assertNotNull(result);
	}

	/**
	 * This test will send a incorrect zipcode
	 */
	@Test
	public void failGetAddressListData() {

		ResponseEntity<AddressModel> addressData = zipCodeController.getAddressData("13215asdasw");
		assertNull(addressData.getBody());
	}
	
	/**
	 * This test will send a list of zipcodes
	 */
	@Test
	public void getAddressListData() {

		List<String> zipcodes = new ArrayList<>();
		zipcodes.add("03821010");
		zipcodes.add("03821000");
		ResponseEntity<List<AddressModel>> addressListData = zipCodeController.getAddressListData(zipcodes);
		List<AddressModel> adresses = addressListData.getBody();
		assertNotNull(adresses);
		assertTrue(adresses.size() == zipcodes.size());

	}

}
