package com.makersharks.suppliersearch.repository;

import com.makersharks.suppliersearch.model.ManufacturingProcess;
import com.makersharks.suppliersearch.model.NatureOfBusiness;
import com.makersharks.suppliersearch.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void testFindByLocationAndNatureOfBusinessAndManufacturingProcess() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> result = supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcess(
                "India", NatureOfBusiness.SMALL_SCALE, ManufacturingProcess._3D_PRINTING, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        Supplier foundSupplier = result.getContent().get(0);
        assertEquals("Alpha Tech", foundSupplier.getCompanyName());
        assertEquals("www.alphatech.com", foundSupplier.getWebsite());
        assertEquals("India", foundSupplier.getLocation());
        assertEquals(NatureOfBusiness.SMALL_SCALE, foundSupplier.getNatureOfBusiness());
        assertEquals(ManufacturingProcess._3D_PRINTING, foundSupplier.getManufacturingProcess());
    }

    @Test
    void testFindAllSuppliers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> result = supplierRepository.findAll(pageable);

        assertNotNull(result);
        assertEquals(10, result.getTotalElements());
    }
}
