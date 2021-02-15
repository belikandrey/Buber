package com.epam.jwd.command;

import java.util.HashMap;
import java.util.Map;

public class CommandResult {
    public enum ResponseType {
        FORWARD,
        REDIRECT
    }

    private String page;

    private ResponseType responseType;

    private Map<String, Object> attributes;


    public CommandResult(String page, ResponseType responseType, Map<String, Object> attributes) {
        this(page, responseType);
        this.attributes = attributes;
    }


    public CommandResult(String page, ResponseType responseType) {
        this.page = page;
        this.responseType = responseType;
        attributes = new HashMap<>();
    }

    public String getPage() {
        return page;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void addAttribute(String name, Object attribute) {
        attributes.put(name, attribute);
    }
}
