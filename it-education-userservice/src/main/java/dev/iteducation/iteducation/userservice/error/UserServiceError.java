package dev.iteducation.iteducation.userservice.error;

import dev.iteducation.commons.error.ErrorReason;

public enum UserServiceError implements ErrorReason {
    ACCOUNT_NOT_FOUND("US.001", "Account with id %s not found"),
    REGISTRATION_FAIL_PASS("US.002", "Passwords don't match"),
    REGISTRATION_FAIL_NAME("US.003", "User already exists"),
    REGISTRATION_FAIL_EXPIRED("US.004", "Verification link invalid or expired"),

    UNEXPECTED_MAIL_SERVICE_ERROR("US.900", "Unexpected error during sending verification email");

    private String code;

    private String messageTemplate;

    UserServiceError(String code, String messageTemplate) {
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessageTemplate() {
        return messageTemplate;
    }
}
