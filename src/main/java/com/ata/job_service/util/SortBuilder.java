package com.ata.job_service.util;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@UtilityClass
public class SortBuilder {
    public Sort getSort(List<String> sorts) {
        return Sort.by(
                sorts.stream().map(sort -> {
                    String[] parts = sort.split(":");
                    Sort.Direction direction = (parts.length > 1 && parts[1].equalsIgnoreCase(DESC.name())) ? DESC : ASC;
                    return new Sort.Order(direction, LOWER_UNDERSCORE.to(LOWER_CAMEL, parts[0]));
                }).collect(Collectors.toList())
        );
    }
}
