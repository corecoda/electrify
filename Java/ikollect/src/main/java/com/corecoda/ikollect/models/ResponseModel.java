package com.corecoda.ikollect.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseModel {

    private boolean status;
    private String message;
    private Object data;
    private FaultMode faultMode;

    // Constructors
    public ResponseModel() {
        this.message = "";
    }

    public ResponseModel(boolean status, String message,Object data, FaultMode faultMode) {
        this.status = status;
        this.message = message;
        this.faultMode = faultMode;
        this.data = data;
    }

    // Static methods to create ResponseModel instances
    public static ResponseModel success(String message, Object data, FaultMode fault) {
        return new ResponseModel(true, message != null ? message : "Request was Successful",data, fault  );
    }

    public static ResponseModel success(String message) {
        return success(message, null, FaultMode.NONE);
    }

    public static ResponseModel failure(String message, Object data, FaultMode fault) {
        return new ResponseModel(false, message != null ? message : "Request was not completed", data, fault);
    }

    public static ResponseModel failure(String message) {
        return failure(message, null, FaultMode.CLIENT_INVALID_ARGUMENT);
    }
}



