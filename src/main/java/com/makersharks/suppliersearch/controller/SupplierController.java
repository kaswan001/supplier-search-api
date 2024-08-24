package com.makersharks.suppliersearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makersharks.suppliersearch.model.ManufacturingProcess;
import com.makersharks.suppliersearch.model.NatureOfBusiness;
import com.makersharks.suppliersearch.model.Supplier;
import com.makersharks.suppliersearch.service.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

	@Autowired
    private SupplierService supplierService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/query")
    public ResponseEntity<Page<Supplier>> searchSuppliers(
        @RequestParam String location,
        @RequestParam NatureOfBusiness natureOfBusiness,
        @RequestParam ManufacturingProcess manufacturingProcess,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
		
        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> result = supplierService.searchSuppliers(location, natureOfBusiness, manufacturingProcess, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hello User");
    }
}
