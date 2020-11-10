package com.cognizant.vendorservice;

import com.cognizant.vendorservice.model.VendorDetails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VendorServiceApplicationTests {

	private String vendorUrl="http://localhost:8083/vendor-service/api/v1/vendor";

	RestTemplate template =new RestTemplate();

	@Test
	public void testGetAllTrucks(){
		template.delete(vendorUrl);
		template.postForEntity(vendorUrl,vendorExampleData(), VendorDetails.class);
		ResponseEntity<VendorDetails[]> vendorDetails = template.getForEntity(vendorUrl,VendorDetails[].class);
		List<VendorDetails> resultVendor = Arrays.asList(vendorDetails.getBody());
		assertThat(resultVendor).size().isEqualTo(1);
	}

	@Test
	@Rollback(false)
	public void testRegisterVendor(){
		template.delete(vendorUrl);
		ResponseEntity<VendorDetails> vendorDetails= template.postForEntity(vendorUrl,vendorExampleData(), VendorDetails.class);
		assertEquals(201,vendorDetails.getStatusCodeValue());
		assertNotNull(vendorDetails.getBody());

	}

	@Test
	public void testGetVendorByName() {
		String vendorName = "Vivek";
		VendorDetails vendorDetails = template.getForObject(vendorUrl+"/" +vendorName,VendorDetails.class);
		assertEquals(vendorDetails.getVendorName(),vendorName);
	}

	@Test
	public void testUpdateVendor(){
		template.delete(vendorUrl);
		ResponseEntity<VendorDetails> vendorDetails= template.postForEntity(vendorUrl,vendorExampleData(), VendorDetails.class);
		assertEquals(201,vendorDetails.getStatusCodeValue());
		vendorDetails.getBody().setVendorPhoneNumber("7654321890");
		template.put(vendorUrl+"/"+vendorDetails.getBody().getVendorId(),vendorDetails,VendorDetails.class);
		assertEquals("7654321890", template.getForObject(vendorUrl+"/id/" +vendorDetails.getBody().getVendorId(),VendorDetails.class).getVendorPhoneNumber());
	}

	@Test
	@Rollback(false)
	public void testDeleteTruck() {
		template.delete(vendorUrl);
		ResponseEntity<VendorDetails> vendorDetails= template.postForEntity(vendorUrl,vendorExampleData(), VendorDetails.class);
		assertEquals(201,vendorDetails.getStatusCodeValue());
		template.delete(vendorUrl+"/"+vendorDetails.getBody().getVendorId());
		assertThrows(HttpClientErrorException.NotFound.class, ()->{ template.getForObject(vendorUrl+"/id/" +vendorDetails.getBody().getVendorId(),VendorDetails.class);});
	}

	VendorDetails vendorExampleData(){
		VendorDetails vendorDetails = new VendorDetails();
		vendorDetails.setVendorPhoneNumber("7896541230");
		vendorDetails.setVendorName("Vivek");
		vendorDetails.setVendorEmail("vivek@gmail.com");
		vendorDetails.setVendorAddress("Guntur,AndhraPradesh,522001");
		return vendorDetails;
	}

}
