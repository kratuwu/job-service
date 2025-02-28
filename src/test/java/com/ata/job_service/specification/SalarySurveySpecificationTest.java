package com.ata.job_service.specification;

import com.ata.job_service.entity.SalarySurvey;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SalarySurveySpecificationTest {
    @Mock
    private Root<SalarySurvey> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private Predicate predicate;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_return_null_when_call_has_job_title_given_null_job_title() {
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasJobTitle(null);
        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void should_return_null_when_call_has_job_title_given_valid_job_title() {
        // Given
        String jobTitle = "Software Engineer";
        when(criteriaBuilder.equal(root.get("jobTitle"), jobTitle)).thenReturn(predicate);

        // When
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasJobTitle(jobTitle);
        Predicate actual = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(actual);
        assertEquals(predicate, actual);
        verify(criteriaBuilder).equal(root.get("jobTitle"), jobTitle);
    }

    @Test
    void should_return_null_when_call_has_gender_given_null_gender() {
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasGender(null);
        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void should_return_null_when_call_has_gender_given_valid_gender() {
        // Given
        String gender = "Male";
        when(criteriaBuilder.equal(root.get("gender"), gender)).thenReturn(predicate);

        // When
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasGender(gender);
        Predicate actual = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(actual);
        assertEquals(predicate, actual);
        verify(criteriaBuilder).equal(root.get("gender"), gender);
    }

    @Test
    void should_return_null_when_call_has_salary_greater_than_equal_given_null_salary() {
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasSalaryGreaterThanEqual(null);
        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void should_return_null_when_call_has_salary_greater_than_equal_given_valid_salary() {
        // Given
        BigDecimal salary = BigDecimal.valueOf(10000);
        when(criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), salary)).thenReturn(predicate);

        // When
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasSalaryGreaterThanEqual(salary);
        Predicate actual = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(actual);
        assertEquals(predicate, actual);
        verify(criteriaBuilder).greaterThanOrEqualTo(root.get("salary"), salary);
    }

    @Test
    void should_return_null_when_call_has_salary_less_than_equal_given_null_salary() {
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasSalaryLessThanEqual(null);
        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void should_return_null_when_call_has_salary_less_than_equal_given_valid_salary() {
        // Given
        BigDecimal salary = BigDecimal.valueOf(10000);
        when(criteriaBuilder.lessThanOrEqualTo(root.get("salary"), salary)).thenReturn(predicate);

        // When
        Specification<SalarySurvey> spec = SalarySurveySpecification.hasSalaryLessThanEqual(salary);
        Predicate actual = spec.toPredicate(root, query, criteriaBuilder);

        assertNotNull(actual);
        assertEquals(predicate, actual);
        verify(criteriaBuilder).lessThanOrEqualTo(root.get("salary"), salary);
    }

    @Test
    void should_return_correct_specification_when_call_with_salary_gte() {
        String jobTitle = "Software Engineer";
        when(criteriaBuilder.equal(root.get("jobTitle"), jobTitle)).thenReturn(predicate);
        BigDecimal salaryGte = BigDecimal.valueOf(10000);
        when(criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), salaryGte)).thenReturn(predicate);
        String gender = "Male";
        when(criteriaBuilder.equal(root.get("gender"), gender)).thenReturn(predicate);

        // When

        Specification<SalarySurvey> spec = SalarySurveySpecification.getSalarySurveyCriteria(gender, jobTitle, salaryGte, BigDecimal.TEN);
        assertNotNull(spec);
        Predicate actualPredicate = spec.toPredicate(root, query, criteriaBuilder);
        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(criteriaBuilder).greaterThanOrEqualTo(root.get("salary"), salaryGte);
        verify(criteriaBuilder).equal(root.get("gender"), gender);
        verify(criteriaBuilder).equal(root.get("jobTitle"), jobTitle);
    }

    @Test
    void should_return_correct_specification_when_call_with_salary_lte() {
        String jobTitle = "Software Engineer";
        when(criteriaBuilder.equal(root.get("jobTitle"), jobTitle)).thenReturn(predicate);
        BigDecimal salaryLte = BigDecimal.valueOf(10000);
        when(criteriaBuilder.lessThanOrEqualTo(root.get("salary"), salaryLte)).thenReturn(predicate);
        String gender = "Male";
        when(criteriaBuilder.equal(root.get("gender"), gender)).thenReturn(predicate);

        // When

        Specification<SalarySurvey> spec = SalarySurveySpecification.getSalarySurveyCriteria(gender, jobTitle, null, salaryLte);
        assertNotNull(spec);
        Predicate actualPredicate = spec.toPredicate(root, query, criteriaBuilder);
        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(criteriaBuilder).lessThanOrEqualTo(root.get("salary"), salaryLte);
        verify(criteriaBuilder).equal(root.get("gender"), gender);
        verify(criteriaBuilder).equal(root.get("jobTitle"), jobTitle);
    }

    @Test
    void should_return_correct_specification_when_call_with_out_salary() {
        String jobTitle = "Software Engineer";
        doReturn(predicate).when(criteriaBuilder).equal(any(), any());
        String gender = "Male";
        when(criteriaBuilder.equal(root.get("gender"), gender)).thenReturn(predicate);

        // When

        Specification<SalarySurvey> spec = SalarySurveySpecification.getSalarySurveyCriteria(gender, jobTitle, null, null);
        assertNotNull(spec);
        Predicate actualPredicate = spec.toPredicate(root, query, criteriaBuilder);
        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(criteriaBuilder).equal(root.get("gender"), gender);
        verify(criteriaBuilder).equal(root.get("jobTitle"), jobTitle);
    }
}