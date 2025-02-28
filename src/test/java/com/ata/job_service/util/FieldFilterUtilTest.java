package com.ata.job_service.util;

import com.ata.job_service.entity.SalarySurvey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FieldFilterUtilTest {
    @Nested
    @DisplayName("Test function buildCamelQueryFields")
    class BuildCamelQuery {
        @Test
        void should_return_camel_case_when_call_build_camel_query_fields_given_fields() {
            // Given
            List<String> fields = List.of("job_title", "salary");

            // When
            List<String> actual = FieldFilterUtil.buildCamelQueryFields(fields);

            // Then
            assertEquals(actual, List.of("jobTitle", "salary"));
        }
    }

    @Nested
    @DisplayName("Test function filterByFields")
    class FilterByFields {
        @Test
        void should_return_new_object_with_query_field_given_salary_survey_list_and_query_fields() {
            // Given
            List<SalarySurvey> salarySurveys = List.of(
                    SalarySurvey.builder()
                            .salary(BigDecimal.valueOf(100000))
                            .gender("Female")
                            .jobTitle("Test")
                            .annualBonus("100000")
                            .location("BKK")
                            .build()
            );
            List<String> queryFields = List.of("jobTitle", "salary");

            // When
            List<Object> actual = FieldFilterUtil.filterByFields(salarySurveys, queryFields);

            // Then
            Map<String, ?> first = (Map<String, ?>) actual.getFirst();

            assertEquals(queryFields.size(), first.size());
            assertEquals(salarySurveys.getFirst().getJobTitle(), first.get("jobTitle"));
            assertEquals(salarySurveys.getFirst().getSalary(), first.get("salary"));

        }

        @ParameterizedTest
        @EmptySource
        void should_return_same_object_with_query_field_given_salary_survey_list_without_query_fields(List<String> fields) {
            // Given
            List<SalarySurvey> salarySurveys = List.of(
                    SalarySurvey.builder()
                            .salary(BigDecimal.valueOf(100000))
                            .gender("Female")
                            .jobTitle("Test")
                            .annualBonus("100000")
                            .location("BKK")
                            .build()
            );

            // When
            List<Object> actual = FieldFilterUtil.filterByFields(salarySurveys, fields);

            // Then
            assertEquals(salarySurveys, actual);

        }
    }
}