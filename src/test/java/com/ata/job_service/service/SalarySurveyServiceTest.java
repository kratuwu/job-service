package com.ata.job_service.service;

import com.ata.job_service.entity.SalarySurvey;
import com.ata.job_service.repository.SalarySurveyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SalarySurveyServiceTest {
    @InjectMocks
    private SalarySurveyService salarySurveyService;
    @Mock
    private SalarySurveyRepository salarySurveyRepository;

    @Test
    void should_return_correct_data_when_call_get_salary_survey_given_sort_and_query_fields_and_gender_and_job_title() {
        // Given
        Sort sort = Sort.by(Sort.Order.asc("salary"));
        List<String> fields = List.of("jobTitle", "salary", "location");
        String gender = "Female";
        String jobTitle = "Software Engineer";
        when(salarySurveyRepository.findAll(
                any(Specification.class),
                eq(sort)
        )).thenReturn(getMockSalarySurveys());

        List<Object> actual = salarySurveyService.getSalarySurvey(gender, jobTitle, BigDecimal.ONE, BigDecimal.TEN, sort, fields);

        assertEquals(2, actual.size());
        Map<String, ?> first = (Map<String, ?>) actual.getFirst();
        assertTrue(fields.containsAll(first.keySet()));
        Map<String, ?> second = (Map<String, ?>) actual.get(1);
        assertTrue(fields.containsAll(second.keySet()));
    }

    private List<SalarySurvey> getMockSalarySurveys() {
        return List.of(
                SalarySurvey.builder()
                        .salary(BigDecimal.valueOf(10000))
                        .jobTitle("Software Engineer")
                        .gender("Female")
                        .location("BKK")
                        .build(),
                SalarySurvey.builder()
                        .salary(BigDecimal.valueOf(10000))
                        .jobTitle("Software Engineer")
                        .gender("Female")
                        .location("USA")
                        .build()
        );
    }
}