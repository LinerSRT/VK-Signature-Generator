package ru.liner.vksign.request;

import java.util.TreeMap;

public class ApiRequestParams extends TreeMap<String, String> {
    private int requestLength = 0;

    public ApiRequestParams() {
    }

    public ApiRequestParams(String value) {
        for (String part : value.split("&")) {
            String[] keyValue = part.split("=");
            put(keyValue[0], keyValue[1]);
        }
    }

    public int getRequestLength() {
        return this.requestLength;
    }

    @Override
    public String put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Wrong request params");
        }
        requestLength += key.length() + value.length() + 2;
        return super.put(key, value);
    }
}