package dev.iteducation.commons.error;

public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException(String code, String message) {
        this(code, message, null);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super("[" + code + "]: " + message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
