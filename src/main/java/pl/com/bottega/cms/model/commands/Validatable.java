package pl.com.bottega.cms.model.commands;

import java.util.*;

import static pl.com.bottega.cms.model.commands.ValidationError.*;


public interface Validatable {

    void validate(ValidationErrors errors);

    default boolean isEmpty(Object o) {
        return (o == null || o.toString().isEmpty());
    }

    default void ensureNotEmpty(Object o, String name, ValidationErrors errors) {
        if (isEmpty(o)) {
            errors.add(name, REQUIRED.getValMsg());
        }
    }

    default void ensureNotEquallyEmpty(Object[] objects, String[] names, ValidationErrors errors) {
        int size = objects.length;
        for (int i=0; i<size; i++) {
            for (int j=i+1;j<size; j++) {
                if (isEmpty(objects[i]) == isEmpty(objects[j])) {
                    errors.add(names[i] + ", " + names[j], ONE_AND_ONLY_ONE.getValMsg());
                }
            }
        }
    }

    default void ensureGreaterThanZero(Object o, String name, ValidationErrors errors) {
        if (!(o instanceof Integer && (Integer) o > 0)) {
            errors.add(name, GREATER_THAN_ZERO.getValMsg());
        }
    }

    default void ensureNotEmpty(Collection c, String name, ValidationErrors errors) {
        if (isEmpty(c)) {
            errors.add(name, REQUIRED.getValMsg());
        }
        else if (c.size() == 0) {
            errors.add(name, NOT_EMPTY.getValMsg());
        }
        else {
            for (Object o : c) {
                if (isEmpty(o)) {
                    errors.add(name, NOT_NULL_VALUES.getValMsg());
                    return;
                }
            }
        }
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
