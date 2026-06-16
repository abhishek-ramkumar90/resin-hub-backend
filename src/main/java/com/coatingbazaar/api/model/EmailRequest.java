package com.coatingbazaar.api.model;

public record EmailRequest(
	String subject,
	String quantity,
	String companyname,
	String pincode,
	String contactnumber,
	String category,
	String product,
	String industry,
	String colour,
	String chemistry,
	String finish,
	String gloss,
    String email
) {
}

