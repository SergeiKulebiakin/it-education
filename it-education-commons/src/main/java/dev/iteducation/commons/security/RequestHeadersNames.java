package dev.iteducation.commons.security;

public enum RequestHeadersNames {

    USER_TOKEN("bg-user-token");

    private String value;

    RequestHeadersNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
