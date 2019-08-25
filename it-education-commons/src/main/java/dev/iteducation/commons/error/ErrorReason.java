package dev.iteducation.commons.error;

public interface ErrorReason {

    String getCode();

    String getMessageTemplate();

    default BusinessException getException(Object... args) {
        return new BusinessException(this.getCode(), String.format(this.getMessageTemplate(), args));
    }

    default BusinessException getException(Throwable throwable, Object... args) {
        return new BusinessException(this.getCode(), String.format(this.getMessageTemplate(), args));
    }

}
