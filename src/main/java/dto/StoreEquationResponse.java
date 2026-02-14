package com.example.algebraic.dto;

public class StoreEquationResponse {

    private String message;
    private int equationId;

    public StoreEquationResponse(String message, int equationId) {
        this.message = message;
        this.equationId = equationId;
    }

    public String getMessage() {
        return message;
    }

    public int getEquationId() {
        return equationId;
    }
}
