package com.ata.job_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SalarySurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;

    private String employer;

    private String location;

    private String jobTitle;

    private String yearsAtEmployer;

    private String yearsOfExperience;

    private BigDecimal salary;

    private String signingBonus;

    private String annualBonus;

    private String annualStockValueBonus;

    private String gender;

    private String additionalComments;

}