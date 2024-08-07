package com.gw.scheduler.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(description = "All details about the Provider")
@Data
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "provider", schema = "scheduler", catalog = "henrymeds")
public class Provider extends AuditableClass
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
	@ApiModelProperty(notes = "The database generated provider ID")
	private Integer recordId;

	@Column(name = "first_name", nullable = false, length = 50)
	@ApiModelProperty(notes = "The first name of the provider")
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	@ApiModelProperty(notes = "The last name of the provider")
	private String lastName;

	@Column(name = "specialization", nullable = false, length = 50)
	@ApiModelProperty(notes = "The specialization of the provider")
	private String specialization;

	@Column(name = "email", nullable = false, length = 100)
	@ApiModelProperty(notes = "The email ID of the provider")
	private String email;

	@Column(name = "phone_number", nullable = false, length = 25)
	@ApiModelProperty(notes = "The phone number of the provider")
	private String phoneNumber;

	@Column(name = "address", nullable = false, length = 100)
	@ApiModelProperty(notes = "The address of the provider")
	private String address;

	@Column(name = "city", nullable = false, length = 50)
	@ApiModelProperty(notes = "The city of the provider")
	private String city;

	@Column(name = "state", nullable = false, length = 30)
	@ApiModelProperty(notes = "The state of the provider")
	private String state;

	@Column(name = "zip", nullable = false, length = 9)
	@ApiModelProperty(notes = "The zip code of the provider")
	private String zip;

	@Column(name = "country", nullable = false, length = 3)
	@ApiModelProperty(notes = "The country of the provider")
	private String country;



}