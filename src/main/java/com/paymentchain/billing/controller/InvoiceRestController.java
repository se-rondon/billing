/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.billing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.billing.common.InvoiceRequestMapper;
import com.paymentchain.billing.common.InvoiceResponseMapper;
import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.entities.dto.InvoiceRequest;
import com.paymentchain.billing.entities.dto.InvoiceResponse;
import com.paymentchain.billing.respository.InvoiceRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author sotobotero
 */
@Tag(name = "Billing API", description = "This API serve all functionally for management Invoices")
@RestController
@RequestMapping("/billing")
public class InvoiceRestController {
    
    @Autowired
    InvoiceRepository invoiceRepository;
    
    @Autowired
    InvoiceRequestMapper irm;
    
    @Autowired
    InvoiceResponseMapper irsm;
    
    @Operation(description = "Return all invoices bundled into Response", summary = "Return 204 if no data found")
    @ApiResponses(value = { @ApiResponse( responseCode = "200", description = "OK"),
    						@ApiResponse( responseCode = "500", description = "Internal error")})
    @GetMapping()
    public List<InvoiceResponse> list() {
        List<Invoice> findAll = invoiceRepository.findAll();
        return irsm.InvoiceListToInvoiceResponse(findAll);
    }
    
    @GetMapping("/{id}")
    public InvoiceResponse get(@PathVariable String id) {
    	  Optional<Invoice> findById = invoiceRepository.findById(Long.valueOf(id));
          InvoiceResponse InvoiceToInvoiceRespose = irsm.InvoiceToInvoiceResponse(findById.get());
          return InvoiceToInvoiceRespose;
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody InvoiceRequest input) {
    	Invoice save = null; 
        Optional<Invoice> findById = invoiceRepository.findById(Long.valueOf(id));
        if(findById == null) {
        	return ResponseEntity.notFound().build();
        }
        Invoice get = findById.get();
        if(get != null){   
               get.setAmount(input.getAmount());
               get.setDetail(input.getDetail());
               get.setCustomerId(input.getCustomer());
               get.setNumber(input.getNumber());
                    save = invoiceRepository.save(get);                      
        }
         InvoiceResponse InvoiceToInvoiceRespose = irsm.InvoiceToInvoiceResponse(save);
        return ResponseEntity.ok(InvoiceToInvoiceRespose);
    }
    
    @Operation(description = "Create a new invoice in the database", summary="Return 200 OK")
    @ApiResponse(responseCode = "200", description = "OK")	
    @PostMapping
    public ResponseEntity<?> post(@RequestBody InvoiceRequest input) {
    	Invoice invoice = irm.InvoiceRequestToInvoice(input);
        Invoice save = invoiceRepository.save(invoice);
        InvoiceResponse invoiceResponse =  irsm.InvoiceToInvoiceResponse(save);
        return ResponseEntity.ok(invoiceResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Invoice> findById = invoiceRepository.findById(Long.valueOf(id));
        Invoice get = findById.get();
        if(get != null) {
        	invoiceRepository.delete(get);
        }
        else {	
        	return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().build();
    }
    
}
