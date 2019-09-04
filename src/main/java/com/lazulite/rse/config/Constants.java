package com.lazulite.rse.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "zh-tw";
    public static final String ANONYMOUS_USER = "anonymoususer";


    public static final String REPEAT_LOGIN = "repeatLogin";

    public static final String DEFAULT_ALIPAY_USER_PASSWORD = "123456";
    public static final int REPEAT_LOGIN_CACHE_TIME = 3;

    public static final String NOTIFY_URL = "https://oapiuat.fosun.com/rs/api/alipay/notify";

    private Constants() {
    }
}
