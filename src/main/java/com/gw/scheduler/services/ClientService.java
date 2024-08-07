package com.gw.scheduler.services;

import com.gw.scheduler.entities.Client;
import com.gw.scheduler.entities.ProviderAppointment;
import com.gw.scheduler.entities.ProviderAvailability;
import com.gw.scheduler.repositories.ClientRepository;
import com.gw.scheduler.repositories.ProviderAppointmentRepository;
import com.gw.scheduler.repositories.ProviderAvailabilityRepository;
import com.gw.scheduler.repositories.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ClientService
{
	private final EmailService emailService;
	private final ClientRepository clientRepository;
	private final ProviderRepository providerRepository;
	private final ProviderAvailabilityRepository providerAvailabilityRepository;
	private final ProviderAppointmentRepository providerAppointmentRepository;

	public ClientService(EmailService emailService, ClientRepository clientRepository,
			ProviderAvailabilityRepository providerAvailabilityRepository, ProviderRepository providerRepository,
			ProviderAppointmentRepository providerAppointmentRepository)
	{
		this.emailService = emailService;
		this.clientRepository = clientRepository;
		this.providerAvailabilityRepository = providerAvailabilityRepository;
		this.providerRepository = providerRepository;
		this.providerAppointmentRepository = providerAppointmentRepository;
	}

	public boolean confirmReservation(String confirmationID)
	{
		ProviderAppointment
				appointment =
				providerAppointmentRepository.findByConfirmationID(UUID.fromString(confirmationID));
		if (appointment != null)
		{
			appointment.setIsConfirmed(true);
			providerAppointmentRepository.save(appointment);
			return true;
		}
		return false;
	}

	public List<ProviderAvailability> getAllProviderAvailabilities()
	{
		return providerAvailabilityRepository.findAllByAvailability();
	}

	public ProviderAppointment reserveAvailabilityForClient(int availabilityID, int clientID)
	{
		Optional<ProviderAvailability>
				availabilityOptional =
				providerAvailabilityRepository.findById(availabilityID);
		if (availabilityOptional.isPresent())
		{
			ProviderAvailability availability = availabilityOptional.get();
			ProviderAppointment appointment = new ProviderAppointment();
			appointment.setProviderId(availability.getProviderId());
			appointment.setProviderAvailabilityId(availabilityID);
			appointment.setClientId(clientID);
			ProviderAppointment savedAppointment = providerAppointmentRepository.save(appointment);
			savedAppointment.setProviderAvailability(availability);
			return savedAppointment;
		} else
		{
			throw new NoSuchElementException("Availability not found");
		}
	}

	public Client saveClient(Client client)
	{
		return clientRepository.save(client);
	}

	public void sendBookingNotification(int clientID, ProviderAppointment appointment)
	{
		Optional<Client> clientOptional = clientRepository.findById(clientID);
		if (clientOptional.isPresent())
		{
			Client client = clientOptional.get();
			String clientEmail = client.getEmail();
			String
					emailMessage =
					"Congratulations " + client.getFirstName() + "! Your appointment has been reserved for: "
					+ appointment.getProviderAvailability()
								 .getStartTime()
					+ ".  Please click the link below to confirm your appointment within the next 30 minutes."
					+ " http://localhost/clients/bookings/confirm?confirmationId="
					+ appointment.getConfirmationID();
			emailService.sendEmailFake(clientEmail, "Confirm appointment", "no-reply@email.com", emailMessage);
		} else
		{
			log.error("Client with id {} not found", clientID);
		}
	}
}