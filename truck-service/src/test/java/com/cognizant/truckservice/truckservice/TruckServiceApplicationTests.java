package com.cognizant.truckservice.truckservice;

import com.cognizant.truckservice.model.Truck;
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
class TruckServiceApplicationTests {

	private String truckUrl="http://localhost:8083/truck-service/api/v1/truck";

	RestTemplate template =new RestTemplate();

	@Test
	public void testGetAllTrucks(){
		template.delete(truckUrl);
		template.postForEntity(truckUrl,truckExampleData(), Truck.class);
		ResponseEntity<Truck[]> truck = template.getForEntity(truckUrl,Truck[].class);
		List<Truck> resultTruck = Arrays.asList(truck.getBody());
		assertThat(resultTruck).size().isEqualTo(1);
	}

	@Test
	@Rollback(false)
	public void testRegisterTruck(){
		template.delete(truckUrl);
		Truck truck= template.postForEntity(truckUrl,truckExampleData(), Truck.class).getBody();
		assertNotNull(truck);

	}

	@Test
	public void testGetTruckByNumber() {
		String truckNumber = "AP 07 RT 7864";
		Truck truck = template.getForObject(truckUrl+"/" +truckNumber,Truck.class);
		assertEquals(truck.getTruckNumber(),truckNumber);
	}


	@Test
	public void testUpdateTruck(){
		template.delete(truckUrl);
		Truck truck = template.postForEntity(truckUrl,truckExampleData(), Truck.class).getBody();
		truck.setTruckName("Ashok Leyland");
		template.put(truckUrl+"/"+truck.getTruckId(),truck,Truck.class);
		assertEquals("Ashok Leyland", template.getForObject(truckUrl+"/" +truck.getTruckNumber(),Truck.class).getTruckName());

	}

	@Test
	@Rollback(false)
	public void testDeleteTruck() {
		template.delete(truckUrl);
		Truck truck= template.postForEntity(truckUrl,truckExampleData(), Truck.class).getBody();
		template.delete(truckUrl+"/"+truck.getTruckId());
		assertThrows(HttpClientErrorException.NotFound.class, ()->{ template.getForObject(truckUrl+"/" +truck.getTruckNumber(),Truck.class);});
	}

	Truck truckExampleData(){
		Truck truck = new Truck();
		truck.setTruckType("Refrigerated (Reefer) Trailers");
		truck.setTruckName("TATA");
		truck.setTruckNumber("AP 07 RT 7864");
		return truck;
	}

}
