package com.gw.scheduler.entities;

import jakarta.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalTime;

@ApiModel(description = "Details about the availability of a provider")
@Data
@Entity
@Table(name = "provider_availability", schema = "scheduler", catalog = "henrymeds")
public class ProviderAvailability extends AuditableClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    @ApiModelProperty(notes = "The database generated record ID")
    private Integer recordId;

    @Column(name = "provider_id", nullable = false)
    @ApiModelProperty(notes = "The provider ID")
    private Integer providerId;

    @Column(name = "day_of_year", nullable = false)
    @ApiModelProperty(notes = "The day of the year")
    private Integer dayOfYear;

    @Column(name = "year", nullable = false)
    @ApiModelProperty(notes = "The year")
    private Integer year;

    @Column(name = "start_time", nullable = false)
    @ApiModelProperty(notes = "Start time of the availability period")
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    @ApiModelProperty(notes = "End time of the availability period")
    private LocalTime endTime;

    @Column(name = "time_zone", nullable = false, length = 3)
    @ApiModelProperty(notes = "The time zone of the provider")
    private String timeZone;

    @Column(name = "is_active", nullable = false)
    @ApiModelProperty(notes = "Flag indicating whether the availability is active or not.  Defaults to true")
    private Boolean isActive=true;

}