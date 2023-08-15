package com.chanseok.rdscore.domain.type;

public enum GraduationStatus {
    ENROLLED(0, "재학중"), BREAK(1, "휴학"), GRADUATED(2, "졸업");

    private final int code;
    private final String description;

    GraduationStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
