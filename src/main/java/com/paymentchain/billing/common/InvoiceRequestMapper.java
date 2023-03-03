package com.paymentchain.billing.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.entities.dto.InvoiceRequest;

@Mapper(componentModel = "spring")
public interface InvoiceRequestMapper {

//	Los que se llaman igual se peuden omitir
	//Si no añado los get y set a la clase, spurce dará un error :"No property named "customer" exists in source parameter(s). Type "InvoiceRequest" has no properties."
	@Mappings({ @Mapping(source = "customer", target = "customerId"),
				@Mapping(source = "invoiceId", target= "id")})
	Invoice InvoiceRequestToInvoice(InvoiceRequest source);

	
	List<Invoice> InvoiceRequestListToInvoice(List<InvoiceRequest> source);
	
	@InheritInverseConfiguration
	InvoiceRequest InvoicetToInvoiceRequest(Invoice source);
	@InheritInverseConfiguration
	List<InvoiceRequest> InvoiceListToInvoiceRequest(List<Invoice> source);
}