package com.ata.job_service.service;

import com.ata.job_service.entity.SalarySurvey;
import com.ata.job_service.repository.SalarySurveyRepository;
import com.ata.job_service.specification.SalarySurveySpecification;
import com.ata.job_service.util.FieldFilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.ata.job_service.specification.SalarySurveySpecification.getSalarySurveyCriteria;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class SalarySurveyService {

    private final SalarySurveyRepository salarySurveyRepository;

    public List<Object> getSalarySurvey(String gender, String jobTitle, BigDecimal salaryGte, BigDecimal salaryLte, Sort sort, List<String> fields) {
        return FieldFilterUtil.filterByFields(
                salarySurveyRepository.findAll(getSalarySurveyCriteria(gender, jobTitle, salaryGte, salaryLte), sort),
                fields
        );
    }

}