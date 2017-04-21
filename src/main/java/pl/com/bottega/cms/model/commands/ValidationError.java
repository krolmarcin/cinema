package pl.com.bottega.cms.model.commands;

/**
 * Created by ogurekk on 2017-04-20.
 */
public enum ValidationError {

    REQUIRED("is required"),
    NOT_EMPTY("should have at least one element"),
    NOT_NULL_VALUES("should not have null values"),
    GREATER_THAN_ZERO("should be greater than zero"),
    ONE_AND_ONLY_ONE("one and only one is required");

    private String valMsg;

    ValidationError(String valMsg) {
        this.valMsg = valMsg;
    }

    public String getValMsg() {
        return valMsg;
    }
}
