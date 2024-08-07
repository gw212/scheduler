package com.gw.scheduler.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>
{
	@ApiModelProperty(notes = "The timestamp when the response is created")
	long timestamp;

	@ApiModelProperty(notes = "The current date and time")
	LocalDateTime currentDateTime;

	@ApiModelProperty(notes = "The result of the operation")
	boolean result;

	@ApiModelProperty(notes = "The data returned in the response")
	T data;

	@ApiModelProperty(notes = "Total count of the data")
	int total;

	@ApiModelProperty(notes = "Errors encountered during the operation, if any")
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
