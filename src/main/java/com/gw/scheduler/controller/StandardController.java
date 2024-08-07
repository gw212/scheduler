package com.gw.scheduler.controller;

import com.gw.scheduler.pojo.ApiResponse;
import org.springframework.http.HttpStatus;

public abstract class StandardController
{

	static final ApiResponse UNAUTHORIZED_RESPONSE = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "User does not have access to this element");

	// method stub to check on if provider is valid (exists, active in system, etc.)
	public boolean isValidProvider(int providerID){
		return true;
	}

	// method stub to check on if client is valid  (exists, active in system, etc.)
	public boolean isValidClient(int providerID){
		return true;
	}
}
