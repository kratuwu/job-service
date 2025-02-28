package com.ata.job_service.specification;

import com.ata.job_service.entity.SalarySurvey;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@UtilityClass
public class SalarySurveySpecification {

    public static Specification<SalarySurvey> getSalarySurveyCriteria(String gender, String jobTitle, BigDecimal salaryGte, BigDecimal salaryLte) {
        Specification<SalarySurvey> spec = Specification.where(SalarySurveySpecification.hasJobTitle(jobTitle));
        spec = spec.and(SalarySurveySpecification.hasGender(gender));
        if (nonNull(salaryGte)) {
            spec = spec.and(SalarySurveySpecification.hasSalaryGreaterThanEqual(salaryGte));
        } else if(nonNull(salaryLte)) {
            spec = spec.and(SalarySurveySpecification.hasSalaryLessThanEqual(salaryLte));
        }
        return spec;
    };

    public static Specification<SalarySurvey> hasJobTitle(String jobTitle) {
        return (root, query, criteriaBuilder) ->
                isNull(jobTitle)
                        ? null
                        : criteriaBuilder.equal(root.get("jobTitle"), jobTitle);
    }

    public static Specification<SalarySurvey> hasGender(String gender) {
        return (root, query, criteriaBuilder) ->
                isNull(gender)
                        ? null
                        : criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<SalarySurvey> hasSalaryGreaterThanEqual(BigDecimal salary) {
        return (root, query, criteriaBuilder) ->
                isNull(salary)
                        ? null
                        : criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), salary);
    }

    public static Specification<SalarySurvey> hasSalaryLessThanEqual(BigDecimal salary) {
        return (root, query, criteriaBuilder) ->
                isNull(salary)
                        ? null
                        : criteriaBuilder.lessThanOrEqualTo(root.get("salary"), salary);
    }

}
