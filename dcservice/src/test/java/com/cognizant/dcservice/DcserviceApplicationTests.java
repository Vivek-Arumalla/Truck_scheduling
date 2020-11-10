package com.cognizant.dcservice;


import com.cognizant.dcservice.model.DC;
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
class DcserviceApplicationTests {

	private String dcUrl="http://localhost:8083/dc-service/api/v1/dc";

	RestTemplate template =new RestTemplate();

	@Test
	public void testGetAllDCDetails(){
		template.delete(dcUrl);
		ResponseEntity<DC[]> dc = template.getForEntity(dcUrl,DC[].class);
		List<DC> resultDc = Arrays.asList(dc.getBody());
		assertThat(resultDc).isEmpty();
	}

	@Test
	@Rollback(false)
	public void testInsertDcDetails(){
		template.delete(dcUrl);
		ResponseEntity<DC> dc= template.postForEntity(dcUrl,dcExampleData(), DC.class);
		assertNotNull(dc);

	}

	@Test
	public void testGetDCById() {
		int id = 101;
		DC dc = template.getForObject(dcUrl+ "/id/" +id,DC.class);
		assertEquals(dc.getDcNumber(),id);
	}


	@Test
	@Rollback(false)
	public void testUpdateDcDetails(){
		template.delete(dcUrl);
		ResponseEntity<DC> dc= template.postForEntity(dcUrl,dcExampleData(), DC.class);
		DC dc1 = dc.getBody();
		dc1.setDcCity("Chennai");
		template.put(dcUrl+"/"+dc1.getDcNumber(),dc1,DC.class);
		DC dc2 = template.getForObject(dcUrl+ "/id/" +dc1.getDcNumber(),DC.class);
		assertNotEquals(dc,dc2);

	}

	@Test
	@Rollback(false)
	public void testDeleteDC() {
		template.delete(dcUrl);
		DC dc= template.postForEntity(dcUrl,dcExampleData(), DC.class).getBody();
		template.delete(dcUrl+"/"+dc.getDcNumber());
		assertThrows(HttpClientErrorException.NotFound.class, ()->{ template.getForObject(dcUrl+ "/id/" +dc.getDcNumber(),DC.class);});
	}

	@Test
	public void testGetDcByCity(){
		template.delete(dcUrl);
		DC dc= template.postForEntity(dcUrl,dcExampleData(), DC.class).getBody();
		assertEquals("Banglore",template.getForObject(dcUrl+ "/name/" +dc.getDcCity(),DC.class).getDcCity());

	}

	DC dcExampleData(){
		DC dc = new DC();
		dc.setDcNumber(101);
		dc.setDcCity("Banglore");
		dc.setDcType("Imports");
		return  dc;
	}

}
