package com.gw.scheduler;

import com.gw.scheduler.services.ProviderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ScheduledTasks
{

	private final ProviderService providerService;

	public ScheduledTasks(ProviderService providerService)
	{
		this.providerService = providerService;
	}

	@Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
	public void checkNonConfirmed(){
		providerService.clearNonConfirmedAppointments();
	}
}
