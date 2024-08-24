package com.makersharks.suppliersearch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.makersharks.suppliersearch.model.ManufacturingProcess;
import com.makersharks.suppliersearch.model.NatureOfBusiness;
import com.makersharks.suppliersearch.model.Supplier;

public interface SupplierService {

	Page<Supplier> searchSuppliers(String location, NatureOfBusiness natureOfBusiness, ManufacturingProcess manufacturingProcess, Pageable pageable);

}
