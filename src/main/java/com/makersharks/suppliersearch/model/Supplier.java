package com.makersharks.suppliersearch.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
    private Long supplierId;
	
	@Column(name = "company_name", nullable = false)
    private String companyName;
	
	@Column(name = "location", nullable = false)
    private String location;
	
	@Column(name = "website", nullable = false)
    private String website;

	@Enumerated(EnumType.STRING)
    @Column(name = "manufacturing_process", nullable = false)
    private ManufacturingProcess manufacturingProcess;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "nature_of_business", nullable = false)
    private NatureOfBusiness natureOfBusiness;

}
