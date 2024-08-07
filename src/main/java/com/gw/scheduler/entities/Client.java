package com.gw.scheduler.entities;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "client", schema = "scheduler", catalog = "henrymeds")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Client extends AuditableClass
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The auto-generated ID of the client")
	private Long recordId;

	@NotBlank
	@Size(max = 50)
	@ApiModelProperty(notes = "The first name of the client")
	private String firstName;

	@NotBlank
	@Size(max = 50)
	@ApiModelProperty(notes = "The last name of the client")
	private String lastName;

	@NotBlank
	@Size(max = 100)
	@ApiModelProperty(notes = "The email of the client")
	private String email;

	@NotBlank
	@Size(max = 25)
	@ApiModelProperty(notes = "The phone number of the client")
	private String phoneNumber;

	@NotBlank
	@Size(max = 100)
	@ApiModelProperty(notes = "The address of the client")
	private String address;

	@NotBlank
	@Size(max = 50)
	@ApiModelProperty(notes = "The city of the client")
	private String city;

	@NotBlank
	@Size(max = 30)
	@ApiModelProperty(notes = "The state of the client")
	private String state;

	@NotBlank
	@Size(max = 9)
	@ApiModelProperty(notes = "The zip of the client")
	private String zip;

	@NotBlank
	@Size(max = 3)
	@ApiModelProperty(notes = "The country of the client")
	private String country;

}