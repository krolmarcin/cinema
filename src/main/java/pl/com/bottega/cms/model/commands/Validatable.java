package pl.com.bottega.cms.model.commands;

import pl.com.bottega.cms.model.ShowingsArranger;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public interface Validatable {

    void validate(ValidationErrors errors);

    default boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    default boolean isEmpty(Long l) {
        return l == null;
    }

    default boolean isEmpty(LocalDateTime ldt) {
        return ldt == null;
    }

    default boolean isEmpty(LocalTime lt) {
        return lt == null;
    }

    default boolean isEmpty(ShowingsArranger sa) {
        return sa == null;
    }

    default boolean isEmpty(Collection c) {
        if (c != null && c.size() != 0) {
            for (Object o : c) {
                if (o == null || isEmpty(o.toString())) {
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
