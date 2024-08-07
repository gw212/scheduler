package com.gw.scheduler.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProviderScheduleRequest
{
	int providerID;

	@NotNull
	List<TimeSlots> timeSlotsList;
	@NotNull
	String providerTimeZone;
}
