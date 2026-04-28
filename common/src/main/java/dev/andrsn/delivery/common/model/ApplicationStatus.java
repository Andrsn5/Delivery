package dev.andrsn.delivery.common.model;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    CREATED,
    RESERVED,
    WAITING_FOR_TAKE,
    OPERATOR_PROCESSING,
    READY_TO_RECEIVE,
    COMPLETED,
    REJECTED;

    public static boolean isFinal(ApplicationStatus status) {
        return status == COMPLETED || status == REJECTED;
    }
}
