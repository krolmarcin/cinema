package pl.com.bottega.cms.infrastructure.validation;

/**
 * Created by ogurekk on 2017-04-20.
 */
public enum ValidationError {

    REQUIRED("is required"),
    NOT_EMPTY("should have at least one element"),
    NOT_NULL_VALUES("should not have null values"),
    GREATER_THAN_X("should be greater than "),
    ONE_AND_ONLY_ONE("one and only one is required"),
    AT_LEAST_X("required at least "),
    UNIQUE_ELEMENTS("should contain unique elements"),
    VALID_EMAIL_ADDRESS("not recognized as valid email address"),
    INVALID("invalid value, available: ");

    private String valMsg;

    ValidationError(String valMsg) {
        this.valMsg = valMsg;
    }

    public String getValMsg() {
        return valMsg;
    }
}
