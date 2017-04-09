package pl.com.bottega.cms.model.commands;

import java.util.*;

public interface Validatable {

    void validate(ValidationErrors errors);

    default boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    default boolean isEmpty(Collection<String> collections) {
        if (collections == null || collections.isEmpty())
            return false;
        else
            for (String collection : collections) {
                if (isEmpty(collection) || collection == null) {
                    return false;
                }
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
