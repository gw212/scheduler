package com.gw.scheduler.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class TimeSlots
{
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;


}
