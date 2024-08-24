package com.makersharks.suppliersearch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.makersharks.suppliersearch.model.ManufacturingProcess;
import com.makersharks.suppliersearch.model.NatureOfBusiness;
import com.makersharks.suppliersearch.model.Supplier;
import com.makersharks.suppliersearch.repository.SupplierRepository;

public class SupplierServiceImplTest {

	@Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchSuppliers() {
        Pageable pageable = PageRequest.of(0, 10);
        Supplier supplier = new Supplier(1L, "Supplier A", "India", "www.supplierA.com", ManufacturingProcess._3D_PRINTING, NatureOfBusiness.SMALL_SCALE);
        Page<Supplier> page = new PageImpl<>(List.of(supplier), pageable, 1);

        when(supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcess(
                "India", NatureOfBusiness.SMALL_SCALE, ManufacturingProcess._3D_PRINTING, pageable))
                .thenReturn(page);

        Page<Supplier> result = supplierService.searchSuppliers("India", NatureOfBusiness.SMALL_SCALE, ManufacturingProcess._3D_PRINTING, pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Supplier A", result.getContent().get(0).getCompanyName());
    }
}
