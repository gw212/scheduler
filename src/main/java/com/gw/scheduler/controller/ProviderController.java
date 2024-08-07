package com.gw.scheduler.controller;

import com.gw.scheduler.entities.Provider;
import com.gw.scheduler.entities.ProviderAvailability;
import com.gw.scheduler.pojo.ApiResponse;
import com.gw.scheduler.pojo.ProviderScheduleRequest;
import com.gw.scheduler.services.ProviderService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/providers")
public class ProviderController extends StandardController
{

	private final ProviderService providerService;

	public ProviderController(ProviderService providerService)
	{
		this.providerService = providerService;
	}

	@PostMapping(value = "/provider/create")
	public ResponseEntity<ApiResponse<Provider>> createProvider(@Valid @RequestBody Provider provider)
	{
		Provider savedProvider = providerService.saveProvider(provider);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(),savedProvider));
	}

	@PostMapping(value = "/provider/{providerID}/available")
	public ResponseEntity<ApiResponse<List<ProviderAvailability>>> postAvailableTimesForProvider(@PathVariable(value = "providerID") int providerID,
			@RequestBody @Valid ProviderScheduleRequest providerScheduleRequest)
	{
		if (!isValidProvider(providerID))
		{
			return ResponseEntity.ok(UNAUTHORIZED_RESPONSE);
		}
		try
		{
			providerScheduleRequest.setProviderID(providerID);
			List<ProviderAvailability>
					savedSchedule =
					providerService.saveProviderSchedule(providerScheduleRequest);
			return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), savedSchedule));
		}
		catch (Exception e)
		{
			log.error("Error saving availability", e);
			return ResponseEntity.ok(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.toString()));
		}

	}
}