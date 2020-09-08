package com.femiliapm.genbe.model.dto;

public class StatusMessageDto2 {
	private String status;
	private String message;
	private DetailBiodataDto data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DetailBiodataDto getData() {
		return data;
	}

	public void setData(DetailBiodataDto data) {
		this.data = data;
	}

}
