package com.gw.scheduler.repositories;

import com.gw.scheduler.entities.ProviderAppointment;
import com.gw.scheduler.entities.ProviderAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProviderAppointmentRepository extends JpaRepository<ProviderAppointment, Integer>
{
	ProviderAppointment findByConfirmationID(UUID confirmationID);

	List<ProviderAppointment> findByIsConfirmedIsNullOrIsConfirmedEqualsAndCreatedDateGreaterThanEqual(boolean isConfirmed, LocalDateTime createdDate);
}
