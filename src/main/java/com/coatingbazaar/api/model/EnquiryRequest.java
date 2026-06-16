package com.coatingbazaar.api.model;

public record EnquiryRequest(
	String subject,
	String companyname,
	String sector,
	String contactnumber,
	String surface,
	String environment,
	String requirement,
	String quantity,
	String timeline,
	String email
) {
}

