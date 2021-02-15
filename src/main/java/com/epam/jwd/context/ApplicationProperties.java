package com.epam.jwd.context;



import java.util.ResourceBundle;

public class ApplicationProperties {
    public static final ApplicationProperties APPLICATION_PROPERTIES = new ApplicationProperties();

    private ApplicationProperties() {
    }

    private String dbUrl;
    private String dbLogin;
    private String dbPassword;
    private int pullInitSize;
    private int pullMaxSize;

    private String smsSid;
    private String smsToken;
    private String number;

    public static void init(ResourceBundle property) {
        APPLICATION_PROPERTIES.dbUrl = property.getString("db_url");
        APPLICATION_PROPERTIES.dbLogin = property.getString("db_login");
        APPLICATION_PROPERTIES.dbPassword = property.getString("db_password");
        APPLICATION_PROPERTIES.pullInitSize = Integer.parseInt(property.getString("pull_init_size"));
        APPLICATION_PROPERTIES.pullMaxSize = Integer.parseInt(property.getString("pull_max_size"));

        APPLICATION_PROPERTIES.smsSid = property.getString("sms.account_sid");
        APPLICATION_PROPERTIES.smsToken = property.getString("sms.auth_token");
        APPLICATION_PROPERTIES.number = property.getString("sms.twilio_number");
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbLogin() {
        return dbLogin;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getPullInitSize() {
        return pullInitSize;
    }

    public int getPullMaxSize() {
        return pullMaxSize;
    }

    public String getSmsSid() {
        return smsSid;
    }

    public String getSmsToken() {
        return smsToken;
    }

    public String getNumber() {
        return number;
    }

}
