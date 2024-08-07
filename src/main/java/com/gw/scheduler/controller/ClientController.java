package com.gw.scheduler.controller;

import com.gw.scheduler.entities.Client;
import com.gw.scheduler.entities.ProviderAppointment;
import com.gw.scheduler.entities.ProviderAvailability;
import com.gw.scheduler.pojo.ApiResponse;
import com.gw.scheduler.services.ClientService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/clients")
public class ClientController extends StandardController
{

	private final ClientService clientService;

	public ClientController(ClientService clientService)
	{
		this.clientService = clientService;
	}

	@PutMapping(value = "/client/{clientID}/bookings/{availabilityID}", consumes = "application/json")
	public ResponseEntity<ApiResponse<ProviderAppointment>> bookAvailabilityForClient(
			@PathVariable("clientID") int clientID, @PathVariable("availabilityID") int availabilityID,
			@RequestParam(defaultValue = "false", required = false) boolean sendNotification
			)
	{
		if(!isValidClient(clientID)){
			return new ResponseEntity<>(UNAUTHORIZED_RESPONSE, HttpStatus.UNAUTHORIZED);
		}
		ProviderAppointment
				appointment = clientService.reserveAvailabilityForClient(availabilityID, clientID);
		if(sendNotification){
			clientService.sendBookingNotification(clientID, appointment);
		}
		return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), appointment));
	}

	@GetMapping(value = "/provider/openings")
	public ResponseEntity<ApiResponse<List<ProviderAvailability>>> getProviderAvailabilities()
	{
		try
		{
			List<ProviderAvailability> providerAvailabilities = clientService.getAllProviderAvailabilities();
			ApiResponse<List<ProviderAvailability>>
					response =
					new ApiResponse<>(HttpStatus.OK.value(), providerAvailabilities);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e)
		{
			log.error("Unable to fetch provider availabilities", e);
			ApiResponse<List<ProviderAvailability>>
					response =
					new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/bookings/confirm")
	public void confirmAppointment(@RequestParam(value = "confirmationID") String confirmationID, HttpServletResponse response)
			throws IOException
	{
		try{
			boolean isConfirmed = clientService.confirmReservation(confirmationID);
			if(isConfirmed){
				response.sendRedirect("http://localhost/success-page");
			}else{
				response.sendRedirect("http://localhost/error-page?msg=not-confirmed");
			}
		}catch (Exception e){
			response.sendRedirect("http://localhost/error-page?msg=error");
		}
	}

	@PostMapping(value="/client/create")
	public ResponseEntity<ApiResponse<Client>> createClient(@RequestBody @Valid Client client)
	{

			Client savedClient = clientService.saveClient(client);
			return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), savedClient));
	}
}
