package com.ata.job_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SalarySurveyService {


    public List<Object> getSalarySurvey(String gender, String jobTitle, BigDecimal minSalary, BigDecimal maxSalary, Sort sort, List<String> fields) {

        return List.of();
    }
}