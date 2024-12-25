package com.cap.identity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1000, "User existed."),
    INVALID_USERNAME(1001, "Invalid username."),
    INVALID_PASSWORD(1002, "Invalid password.")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
