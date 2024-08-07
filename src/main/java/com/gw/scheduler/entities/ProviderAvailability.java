package com.gw.scheduler.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Schema(description = "Details about the availability of a provider")
@Data
@Entity
@Table(name = "provider_availability", schema = "scheduler", catalog = "henrymeds")
public class ProviderAvailability extends AuditableClass
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
	@Schema(description = "The database generated record ID")
	private Integer recordId;

	@Column(name = "provider_id", nullable = false)
	@Schema(description = "The provider ID")
	private Integer providerId;

	@Column(name = "day_of_year", nullable = false)
	@Schema(description = "The day of the year")
	private Integer dayOfYear;

	@Column(name = "year", nullable = false)
	@Schema(description = "The year")
	private Integer year;

	@Column(name = "start_time", nullable = false)
	@Schema(description = "Start time of the availability period")
	private LocalTime startTime;

	@Column(name = "end_time", nullable = false)
	@Schema(description = "End time of the availability period")
	private LocalTime endTime;

	@Column(name = "time_zone", nullable = false, length = 3)
	@Schema(description = "The time zone of the provider")
	private String timeZone;

	@Column(name = "is_active", nullable = false)
	@Schema(description = "Flag indicating whether the availability is active or not.  Defaults to true")
	private Boolean isActive = true;

}