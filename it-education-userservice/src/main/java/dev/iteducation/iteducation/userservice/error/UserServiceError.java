package dev.iteducation.iteducation.userservice.error;

import dev.iteducation.commons.error.ErrorReason;

public enum UserServiceError implements ErrorReason {
    ACCOUNT_NOT_FOUND("US.001", "Account with id %s not found");

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
