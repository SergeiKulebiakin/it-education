package dev.iteducation.commons.error;

public class BusinessException extends RuntimeException {

    private String code;
    private String message;

    public BusinessException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + code + "]: " + message ;
    }
}
