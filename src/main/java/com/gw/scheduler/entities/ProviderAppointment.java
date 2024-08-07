package com.gw.scheduler.entities;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)

@NoArgsConstructor
@Entity
@Table(name = "provider_appointments", schema = "scheduler", catalog = "henrymeds")
public class ProviderAppointment extends AuditableClass
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
	@ApiModelProperty(notes = "The unique ID of the appointment")
	private int recordId;

	@NotNull
	@Column(name = "provider_id")
	@ApiModelProperty(notes = "The Provider ID associated with the appointment")
	private int providerId;

	@NotNull
	@Column(name = "provider_availability_id")
	@ApiModelProperty(notes = "The Provider Availability ID for the appointment")
	private int providerAvailabilityId;

	@NotNull
	@Column(name = "client_id")
	@ApiModelProperty(notes = "The Client ID for the appointment")
	private int clientId;

	@NotNull
	@Column(name = "confirmation_id")
	@ApiModelProperty(notes = "The confirmation ID to be used as a public and unique reference in email links")
	private UUID confirmationID = UUID.randomUUID();

	@Column(name = "is_confirmed")
	@ApiModelProperty(notes = "Flag to determine if appointment has been confirmed by the user or not. Null if it has not been interacted with yet")
	private Boolean isConfirmed;

	@Transient
	@ApiModelProperty(notes = "The Provider Availability Data for this appointment")
	private ProviderAvailability providerAvailability;

}