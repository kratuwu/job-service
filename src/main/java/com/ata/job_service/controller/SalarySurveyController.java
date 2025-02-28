package com.ata.job_service.controller;

import com.ata.job_service.service.SalarySurveyService;
import com.ata.job_service.util.FieldFilterUtil;
import com.ata.job_service.util.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/job_data")
public class SalarySurveyController {

    @Autowired
    private SalarySurveyService salarySurveyService;

    @GetMapping
    public ResponseEntity<List<Object>> getJobDataList(
            @RequestParam(required = false, defaultValue = "") List<String> fields,
            @RequestParam(defaultValue = "id:ASC", required = false) List<String> sorts,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(name = "salary[gte]", required = false) BigDecimal minSalary,
            @RequestParam(required = false) BigDecimal maxSalary) {

        return new ResponseEntity<>(
                salarySurveyService.getSalarySurvey(
                        gender,
                        jobTitle,
                        minSalary,
                        maxSalary,
                        SortBuilder.getSort(sorts),
                        FieldFilterUtil.buildCamelQueryFields(fields)
                ),
                HttpStatus.OK
        );
    }
}