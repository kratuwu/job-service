package com.ata.job_service.util;

import com.ata.job_service.entity.SalarySurvey;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static org.springframework.util.ObjectUtils.isEmpty;

@UtilityClass
public class FieldFilterUtil {

    public static List<String> buildCamelQueryFields(List<String> fields) {
        return fields.stream().map(field -> LOWER_UNDERSCORE.to(LOWER_CAMEL, field)).toList();
    }

    public static List<Object> filterByFields(List<SalarySurvey> salarySurveys, List<String> fields) {
        if (isEmpty(fields)) return new ArrayList<>(salarySurveys);
        return salarySurveys.stream().map(salarySurvey -> {
            Map<String, Object> map = new HashMap<>();
            for (String field : fields) {
                try {
                    Field declaredField = SalarySurvey.class.getDeclaredField(field);
                    declaredField.setAccessible(true);
                    map.put(field, declaredField.get(salarySurvey));
                } catch (Exception ignored) {
                }
            }
            return (Object) map;
        }).toList();
    }
}