package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.model.ShowingsArranger;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public interface Validatable {

    void validate(ValidationErrors errors);

    default boolean isEmpty(Object o) {
        return (o == null || o.toString().isEmpty());
    }

    default boolean areEquallyEmpty(Object o1, Object o2) {
        return isEmpty(o1) == isEmpty(o2);
    }

    default boolean isNotGreaterThanZero(Object o) {
        return !(o instanceof Integer && (Integer) o > 0);
    }

    default boolean isEmpty(Collection c) {
        if (c == null || c.size() == 0) {
            return true;
        }
        for (Object o : c) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    class ValidationErrors {

        private Map<String, Set<String>> errors = new HashMap<>();

        public void add(String fieldName, String errorMessage) {
            Set<String> fieldErrors = errors.getOrDefault(fieldName, new HashSet<>());
            fieldErrors.add(errorMessage);
            errors.putIfAbsent(fieldName, fieldErrors);
        }

        public boolean isValid() {
            return errors.isEmpty();
        }

        public Map<String, Set<String>> getErrors() {
            return errors;
        }

    }

}
