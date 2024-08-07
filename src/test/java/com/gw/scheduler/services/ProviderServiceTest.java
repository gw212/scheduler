package com.gw.scheduler.services;

import com.gw.scheduler.entities.Provider;
import com.gw.scheduler.entities.ProviderAvailability;
import com.gw.scheduler.pojo.ProviderScheduleRequest;
import com.gw.scheduler.pojo.TimeSlots;
import com.gw.scheduler.repositories.ProviderAvailabilityRepository;
import com.gw.scheduler.repositories.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class ProviderServiceTest
{

	@Mock
	private ProviderRepository providerRepository;

	@InjectMocks
	private ProviderService providerService;
	@Mock
	private ProviderAvailabilityRepository providerAvailabilityRepository;

	@Autowired
	PostgreSQLContainer<?> postgresContainer;

	@Test
	public void testSaveProvider()
	{
		// Create a Provider
		Provider provider = new Provider();
		provider.setFirstName("FirstName");
		provider.setLastName("LastName");
		provider.setSpecialization("Specialization");
		provider.setEmail("test@example.com");
		provider.setPhoneNumber("1234567890");
		provider.setAddress("Address");
		provider.setCity("City");
		provider.setState("State");
		provider.setZip("123456");
		provider.setCountry("Country");

		doReturn(provider).when(providerRepository)
						  .save(provider);

		Provider returnedProvider = providerService.saveProvider(provider);

		// Assert the response
		assertNotNull(returnedProvider, "The saved provider should not be null");
	}

	@Test
	public void testSaveProviderSchedule()
	{
		ProviderScheduleRequest providerScheduleRequest = new ProviderScheduleRequest();
		providerScheduleRequest.setProviderID(123);

		TimeSlots timeSlot1 = new TimeSlots(LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(17, 0));

		TimeSlots timeSlot2 = new TimeSlots(LocalDate.now()
													 .plusDays(30),
		LocalTime.of(9, 0),
		LocalTime.of(17, 0));

		List<TimeSlots> timeSlotsList = Arrays.asList(timeSlot1, timeSlot2);

		providerScheduleRequest.setTimeSlotsList(timeSlotsList);
		providerScheduleRequest.setProviderTimeZone("CST");

		ProviderAvailability availability1 = new ProviderAvailability();
		availability1.setDayOfYear(timeSlot1.getDate()
											.getDayOfYear());
		availability1.setStartTime(timeSlot1.getStartTime());
		availability1.setEndTime(timeSlot1.getEndTime());
		ProviderAvailability availability2 = new ProviderAvailability();
		availability2.setDayOfYear(timeSlot2.getDate()
											.getDayOfYear());
		availability2.setStartTime(timeSlot2.getStartTime());
		availability2.setEndTime(timeSlot2.getEndTime());
		List<ProviderAvailability> availabilityList = Arrays.asList(availability1, availability2);

		doReturn(availabilityList).when(providerAvailabilityRepository)
								  .saveAll(any(List.class));

		List<ProviderAvailability> returnedList = providerService.saveProviderSchedule(providerScheduleRequest);

		// Assert the response
		assertNotNull(returnedList, "The returned list should not be null");
		assertEquals(2, returnedList.size(), "The returned list size is not as expected");
	}
}
