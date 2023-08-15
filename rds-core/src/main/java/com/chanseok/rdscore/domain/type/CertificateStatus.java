package com.chanseok.rdscore.domain.type;

public enum CertificateStatus {
    PENDING(0, "보류"), IN_PROGRESS(1, "진행중"), DENIED(2, "거절"), COMPLETED(3, "완료");

    private final int code;
    private final String description;

    CertificateStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
