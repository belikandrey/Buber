package com.epam.jwd.util;

import com.epam.jwd.context.ApplicationProperties;

import java.util.ResourceBundle;

public class PropertyReader {

    private PropertyReader() {
    }

    public static void init() {
        ResourceBundle properties = ResourceBundle.getBundle("application");
        ApplicationProperties.init(properties);
    }
}
