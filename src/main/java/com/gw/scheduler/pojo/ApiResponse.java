package com.gw.scheduler.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>
{
	@Schema(description =  "The timestamp when the response is created")
	long timestamp;

	@Schema(description =  "The current date and time")
	LocalDateTime currentDateTime;

	@Schema(description =  "The result of the operation")
	boolean result;

	@Schema(description =  "The data returned in the response")
	T data;

	@Schema(description =  "Total count of the data")
	int total;

	@Schema(description =  "Errors encountered during the operation, if any")
	ApiError errorResponse;

	public ApiResponse(int statusCode, String errorMessage)
	{
		this.timestamp = System.currentTimeMillis();
		this.currentDateTime = LocalDateTime.now();
		result = false;
		data = null;
		errorResponse = new ApiError(statusCode, errorMessage);
	}

	public ApiResponse(int statusCode, T data)
	{
		this.timestamp = System.currentTimeMillis();
		this.currentDateTime = LocalDateTime.now();
		result = true;
		total = 1;
		this.data = data;

	}
}
