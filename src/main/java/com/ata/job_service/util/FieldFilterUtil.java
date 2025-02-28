package com.ata.job_service.util;

import lombok.experimental.UtilityClass;

import java.util.List;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

@UtilityClass
public class FieldFilterUtil {

    public static List<String> buildCamelQueryFields(List<String> fields) {
        return fields.stream().map(field -> LOWER_UNDERSCORE.to(LOWER_CAMEL, field)).toList();
    }
}