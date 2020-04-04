package com.pattern.adapter;

public class CommonResponse {

    private String value;

    public CommonResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "value='" + value + '\'' +
                '}';
    }
}
