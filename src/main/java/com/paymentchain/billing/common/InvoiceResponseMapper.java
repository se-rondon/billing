package com.paymentchain.billing.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.entities.dto.InvoiceResponse;

@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper {

//	Los que se llaman igual se peuden omitir
	@Mappings({ @Mapping(source = "customerId", target = "customer"),
				@Mapping(source = "id", target= "invoiceId"),
				@Mapping(source = "number", target = "number"), 
				@Mapping(source = "detail", target = "detail"),
				@Mapping(source = "amount", target = "amount")})
	InvoiceResponse InvoiceToInvoiceResponse(Invoice source);
	
	List<InvoiceResponse> InvoiceListToInvoiceResponse(List<Invoice> source);
	
	@InheritInverseConfiguration
	Invoice InvoiceResponseToInvoice(InvoiceResponse source);
	
	@InheritInverseConfiguration
	List<Invoice> InvoiceResponseListToInvoice(List<InvoiceResponse> source);
}
