package pl.com.bottega.cms.model.commands;

import java.time.LocalTime;
import java.util.*;

public interface Validatable {

    void validate(ValidationErrors errors);

    default boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    default boolean isEmpty(Collection c) {
        if (c != null && c.size() != 0) {
            for (Object o : c) {
                if (o == null || isEmpty((String) o)) {
                    return true;
                }
            }
            return false;
        }
        return true;
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
