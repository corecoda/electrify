package com.corecoda.ikollect.models;

public enum FaultMode {
    NONE,
    CLIENT_INVALID_ARGUMENT,
    INVALID_OBJECT_STATE,
    GATEWAY_ERROR,
    REQUESTED_ENTITY_NOT_FOUND,
    LIMIT_EXCEEDED,
    UNAUTHORIZED
}
