package com.ata.job_service.util;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortBuilderTest {
    @Test
    void should_return_correct_sort_when_call_build_sort_given_sort_list_with_asc_and_desc() {
        // Given
        List<String> sortes = List.of("salary:ASC", "job_title:DESC");

        // When
        Sort actual = SortBuilder.getSort(sortes);

        Sort expected = Sort.by(List.of(
                Sort.Order.asc("salary"),
                Sort.Order.desc("jobTitle")
        ));

        // Then
        assertEquals(expected, actual);
    }
}