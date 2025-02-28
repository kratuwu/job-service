package com.ata.job_service.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldFilterUtilTest {
    @Test
    void should_return_camel_case_when_call_build_camel_query_fields_given_fields_() {
        // Given
        List<String> fields = List.of("job_title", "salary");

        // When
        List<String> actual = FieldFilterUtil.buildCamelQueryFields(fields);

        // Then
        assertEquals(actual, List.of("jobTitle", "salary"));
    }
}