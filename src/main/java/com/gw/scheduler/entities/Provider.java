package com.gw.scheduler.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "All details about the Provider")
@Data
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "provider", schema = "scheduler", catalog = "henrymeds")
public class Provider extends AuditableClass
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
	@Schema(description = "The database generated provider ID")
	private Integer recordId;

	@Column(name = "first_name", nullable = false, length = 50)
	@Schema(description = "The first name of the provider")
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	@Schema(description = "The last name of the provider")
	private String lastName;

	@Column(name = "specialization", nullable = false, length = 50)
	@Schema(description = "The specialization of the provider")
	private String specialization;

	@Column(name = "email", nullable = false, length = 100)
	@Schema(description = "The email ID of the provider")
	private String email;

	@Column(name = "phone_number", nullable = false, length = 25)
	@Schema(description = "The phone number of the provider")
	private String phoneNumber;

	@Column(name = "address", nullable = false, length = 100)
	@Schema(description = "The address of the provider")
	private String address;

	@Column(name = "city", nullable = false, length = 50)
	@Schema(description = "The city of the provider")
	private String city;

	@Column(name = "state", nullable = false, length = 30)
	@Schema(description = "The state of the provider")
	private String state;

	@Column(name = "zip", nullable = false, length = 9)
	@Schema(description = "The zip code of the provider")
	private String zip;

	@Column(name = "country", nullable = false, length = 3)
	@Schema(description = "The country of the provider")
	private String country;

}