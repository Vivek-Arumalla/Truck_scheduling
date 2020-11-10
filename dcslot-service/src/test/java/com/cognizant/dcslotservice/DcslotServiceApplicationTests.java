package com.cognizant.dcslotservice;

import com.cognizant.dcslotservice.model.DC;
import com.cognizant.dcslotservice.model.DcSlots;
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
class DcslotServiceApplicationTests {

	private String dcSlotsUrl="http://localhost:8083/dc-service/api/v1/dcslots";
	private String dcUrl="http://localhost:8083/dcSlots-service/api/v1/dc";

	RestTemplate template =new RestTemplate();

	@Test
	public void testGetAllDcSlots(){
		template.delete(dcSlotsUrl);
		ResponseEntity<DcSlots[]> dcSlots = template.getForEntity(dcSlotsUrl,DcSlots[].class);
		List<DcSlots> resultDcSlots = Arrays.asList(dcSlots.getBody());
		assertThat(resultDcSlots).isEmpty();
	}

	@Test
	public void testInsertDcSlots(){
		template.delete(dcSlotsUrl);
		template.delete(dcUrl);
		ResponseEntity<DC> dc = template.postForEntity(dcUrl,dcExampleData(),DC.class);
		ResponseEntity<DcSlots> dcSlots= template.postForEntity(dcSlotsUrl,dcSlotsExampleData(), DcSlots.class);
		assertNotNull(dcSlots);

	}

	@Test
	@Rollback(false)
	public void testUpdateDcSlot(){
		template.delete(dcSlotsUrl);
		template.delete(dcUrl);
		DC dc = template.postForEntity(dcUrl,dcExampleData(),DC.class).getBody();
		DcSlots dcSlots= template.postForEntity(dcSlotsUrl,dcSlotsExampleData(), DcSlots.class).getBody();
		dcSlots.setTimeSlot("9");
		template.put(dcSlotsUrl+"/"+dcSlots.getDcSlotNumber(),dcSlots,DcSlots.class);
		assertEquals("9.00 to 10.00",template.getForObject(dcSlotsUrl+ "/" +dcSlots.getDcSlotNumber(),DcSlots.class).getTimeSlot());

	}

	@Test
	@Rollback(false)
	public void testDeleteDcSlot() {
		template.delete(dcSlotsUrl);
		template.delete(dcUrl);
		DC dc = template.postForEntity(dcUrl,dcExampleData(),DC.class).getBody();
		DcSlots dcSlots= template.postForEntity(dcSlotsUrl,dcSlotsExampleData(), DcSlots.class).getBody();
		template.delete(dcSlotsUrl+"/"+dcSlots.getDcSlotNumber());
		assertThrows(HttpClientErrorException.NotFound.class, ()->{ template.getForObject(dcSlotsUrl+ "/" +dcSlots.getDcSlotNumber(),DcSlots.class);});
	}

	DC dcExampleData(){
		DC dc = new DC();
		dc.setDcNumber(101);
		dc.setDcCity("Banglore");
		dc.setDcType("Imports");
		return  dc;
	}

	DcSlots dcSlotsExampleData(){
		DcSlots dcSlots =  new DcSlots();
		dcSlots.setTimeSlot("6");
		dcSlots.setMaxTrucks(2);
		dcSlots.setDc(dcExampleData());
		return dcSlots;

	}


}
