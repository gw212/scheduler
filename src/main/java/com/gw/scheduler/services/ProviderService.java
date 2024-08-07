package com.gw.scheduler.services;

import com.gw.scheduler.entities.Provider;
import com.gw.scheduler.entities.ProviderAppointment;
import com.gw.scheduler.entities.ProviderAvailability;
import com.gw.scheduler.pojo.ProviderScheduleRequest;
import com.gw.scheduler.repositories.ProviderAppointmentRepository;
import com.gw.scheduler.repositories.ProviderAvailabilityRepository;
import com.gw.scheduler.repositories.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProviderService
{

	private final ProviderRepository providerRepository;
	private final ProviderAvailabilityRepository providerAvailabilityRepository;
	private final ProviderAppointmentRepository providerAppointmentRepository;


	public static void main(String[] args)
	{
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("fhj89024jAJKLHF*H24jknfasd");
		String myEncryptedPassword = encryptor.encrypt("asdfhj8shadf&H@#$89");
		System.out.println(myEncryptedPassword);
	}


	public ProviderService(ProviderAvailabilityRepository providerAvailabilityRepository,
			ProviderRepository providerRepository, ProviderAppointmentRepository providerAppointmentRepository)
	{
		this.providerAvailabilityRepository = providerAvailabilityRepository;
		this.providerRepository = providerRepository;
		this.providerAppointmentRepository = providerAppointmentRepository;
	}

	public void clearNonConfirmedAppointments()
	{
		LocalDateTime
				thirtyMinuteDateTime =
				LocalDateTime.now()
							 .minusMinutes(30);
		List<ProviderAppointment>
				appointmentsToClear =
				providerAppointmentRepository.findByIsConfirmedIsNullOrIsConfirmedEqualsAndCreatedDateGreaterThanEqual(
						false, thirtyMinuteDateTime);
		if (appointmentsToClear != null && !appointmentsToClear.isEmpty())
		{
			appointmentsToClear.forEach(
					appointment -> providerAppointmentRepository.deleteById(appointment.getRecordId()));
		}

	}

	public Provider saveProvider(Provider provider)
	{
		return providerRepository.save(provider);
	}

	public List<ProviderAvailability> saveProviderSchedule(ProviderScheduleRequest providerScheduleRequest)
	{

		List<ProviderAvailability> providerAvailability = convertRequestToSchedule(providerScheduleRequest);
		return providerAvailabilityRepository.saveAll(providerAvailability);
	}

	private List<ProviderAvailability> convertRequestToSchedule(ProviderScheduleRequest providerScheduleRequest)
	{
		List<ProviderAvailability> providerAvailabilityList = new ArrayList<>();

		//		providerScheduleRequest.getTimeSlotsList()
		//							   .forEach(timeSlots -> {
		//								   ProviderAvailability availability = new ProviderAvailability();
		//								   availability.setProviderId(providerScheduleRequest.getProviderID());
		//								   availability.setTimeZone(providerScheduleRequest.getProviderTimeZone
		//								   ());
		//								   availability.setDayOfYear(timeSlots.date()
		//																	  .getDayOfYear());
		//								   availability.setStartTime(timeSlots.startTime());
		//								   availability.setEndTime(timeSlots.endTime());
		//								   providerAvailabilityList.add(availability);
		//							   });
		//		return providerAvailabilityList;
		//	}
		providerScheduleRequest.getTimeSlotsList()
							   .forEach(timeSlots -> {
								   LocalTime startTime = timeSlots.getStartTime();
								   LocalTime endTime = timeSlots.getEndTime();
								   while (!startTime.isAfter(endTime))
								   {
									   ProviderAvailability availability = new ProviderAvailability();
									   availability.setProviderId(providerScheduleRequest.getProviderID());
									   availability.setTimeZone(providerScheduleRequest.getProviderTimeZone());
									   availability.setDayOfYear(timeSlots.getDate()
																		  .getDayOfYear());
									   availability.setYear(timeSlots.getDate()
																	 .getYear());
									   availability.setStartTime(startTime);
									   availability.setEndTime(startTime.plusMinutes(15)
																		.isBefore(endTime) ?
											   startTime.plusMinutes(15) :
											   endTime);
									   providerAvailabilityList.add(availability);
									   startTime = startTime.plusMinutes(15);
								   }
							   });
		return providerAvailabilityList;
	}

}