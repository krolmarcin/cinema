package pl.com.bottega.cms.infrastructure.validation;

import java.util.*;


public interface Validatable {

    void validate(ValidationErrors errors);

    default boolean isEmpty(Object o) {
        return (o == null || o.toString().trim().isEmpty());
    }

    default void ensureEnumType(Enum s, Class enumType, String name, ValidationErrors errors) {
        Object[] enumTypes = enumType.getEnumConstants();
        Boolean valueFound = false;
        for (Object o : enumTypes) {
            if (o.equals(s)) {
                valueFound = true;
                break;
            }
        }
        if (!valueFound) {
            errors.add(name, ValidationError.INVALID.getValMsg());
        }
    }

    default void ensureValidEmailAddress(String email, String name, ValidationErrors errors) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if (!m.matches()) {
            errors.add(name, ValidationError.VALID_EMAIL_ADDRESS.getValMsg());
        }
    }

    default void ensureNotEmpty(Object o, String name, ValidationErrors errors) {
        if (isEmpty(o)) {
            errors.add(name, ValidationError.REQUIRED.getValMsg());
        }
    }

    default void ensureNotEquallyEmpty(Object[] objects, String[] names, ValidationErrors errors) {
        int size = objects.length;
        for (int i=0; i<size; i++) {
            for (int j=i+1;j<size; j++) {
                if (isEmpty(objects[i]) == isEmpty(objects[j])) {
                    errors.add(names[i] + ", " + names[j], ValidationError.ONE_AND_ONLY_ONE.getValMsg());
                }
            }
        }
    }

    default void ensureGreaterThanX(Object o, String name, int bound, ValidationErrors errors) {
        if (!(o instanceof Integer && (Integer) o > bound)) {
            errors.add(name, ValidationError.GREATER_THAN_X.getValMsg() + bound);
        }
    }

    default void ensureGreaterThanX(Object o, String name, Object bound, ValidationErrors errors) {
        if (o.toString().compareTo(bound.toString()) <= 0) {
            errors.add(name, ValidationError.GREATER_THAN_X.getValMsg() + bound);
        }
    }

    default void ensureEachGreaterThanX(Collection c, String name, Object bound, ValidationErrors errors) {
        for (Object o : c) {
            if (o.toString().compareTo(bound.toString()) <= 0) {
                errors.add(name + ".$value=" + o, ValidationError.GREATER_THAN_X.getValMsg() + bound);
            }
        }

    }

    default void ensureNotEmpty(Collection c, String name, ValidationErrors errors) {
        if (isEmpty(c)) {
            errors.add(name, ValidationError.REQUIRED.getValMsg());
        }
        else if (c.size() == 0) {
            errors.add(name, ValidationError.NOT_EMPTY.getValMsg());
        }
        else {
            for (Object o : c) {
                if (isEmpty(o)) {
                    errors.add(name, ValidationError.NOT_NULL_VALUES.getValMsg());
                    return;
                }
            }
        }
    }

    default void ensureAtLeastX(Collection c, String name, int bound, ValidationErrors errors) {
        if ( !(c.size() >= bound)) {
            errors.add(name, ValidationError.AT_LEAST_X.getValMsg() + bound);
        }
    }

    default void ensureUniqueElements(Collection c, String name, ValidationErrors errors) {
        for (Object o1 : c) {
            for (Object o2 : c) {
                if (o1 != o2 && o1.equals(o2)) {
                    errors.add(name, ValidationError.UNIQUE_ELEMENTS.getValMsg());
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
