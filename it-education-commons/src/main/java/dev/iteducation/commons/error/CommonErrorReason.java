package dev.iteducation.commons.error;

public enum CommonErrorReason implements ErrorReason {

    // Validation errors
    MODEL_VALIDATION_ERROR("V001", "Validation error: %s"),
    PATH_VARIABLE_ERROR("V002", "Error reading path variable %s"),
    USER_TOKEN_ERROR("V003", "Error reading user token"),

    // Unexpected errors
    TEST_ERROR("T001", "test error"),
    UNEXPECTED("E000", "Unexpected error: %s");

    private String code;
    private String message;

    CommonErrorReason(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessageTemplate() {
        return message;
    }
}
