package com.paymentchain.billing.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "InvoiceResponse", description = "Model that represents an invoice")
@Data
public class InvoiceResponse {
	@Schema(name = "invoiceId", required = true, example = "2", defaultValue = "1", description = "Unique Id of invoice")
	private long invoiceId;
	@Schema(name = "customer", required = true, example = "2", defaultValue = "1", description = "Unique Id of customer that represents the owner of invoice")
	private long customer;
	@Schema(name = "number", required = true, example = "1", defaultValue = "8", description = "Number given on fisical invoice")
	private String number;
	@Schema(name = "detail", required = true, example = "Inictial invoice", defaultValue = "Initial invoice", description = "Description of the invoice")
	private String detail;
	@Schema(name = "amount", required = true, example = "500", defaultValue = "0", description = "Amount of money	")
	private double amount;
}
