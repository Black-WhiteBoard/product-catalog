package com.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    @JsonProperty
    private String code;

    @JsonProperty
    private String message;
    public ErrorResponse(String code,String message){

        this.code=code;
        this.message=message;
    }
}
