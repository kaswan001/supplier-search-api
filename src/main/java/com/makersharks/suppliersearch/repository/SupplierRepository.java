package com.makersharks.suppliersearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.makersharks.suppliersearch.model.ManufacturingProcess;
import com.makersharks.suppliersearch.model.NatureOfBusiness;
import com.makersharks.suppliersearch.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcess(
	        String location, NatureOfBusiness natureOfBusiness, ManufacturingProcess manufacturingProcess, Pageable pageable
	    );
}
