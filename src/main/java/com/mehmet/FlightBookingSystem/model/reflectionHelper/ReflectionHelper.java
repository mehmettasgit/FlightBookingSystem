package com.mehmet.FlightBookingSystem.model.reflectionHelper;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ReflectionHelper {
    public static Set<String> getInvalidFieldNames(Object object, Set<String> validFields) {
        Set<String> invalidFieldNames = new HashSet<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = field.getName();
            if (!validFields.contains(fieldName)) {
                invalidFieldNames.add(fieldName);
            }
        }
        return invalidFieldNames;
    }
}
