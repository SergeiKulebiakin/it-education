package dev.iteducation.commons.error;

import lombok.Getter;

@Getter
public class ErrorModel {

    private String code;
    private String message;

    public ErrorModel(BusinessException error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
