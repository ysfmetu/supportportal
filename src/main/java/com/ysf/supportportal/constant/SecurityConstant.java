package com.ysf.supportportal.constant;

public class SecurityConstant {

    public static final Long EXPIRATION_TIME=Long.valueOf(432_000_000); //5 günlük süreyi ifade eder, milisecond türünden gösterimi
    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String JWT_TOKEN_HEADER="jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED="Token cannot be verified";
    public static final String GET_ARRAYS_LLC="TMO,Ysf";
    public static final String GET_ARRAYS_ADMINISTRATION="User Management System";
    public static final String AUTHORITIES="authorities";
    public static final String FORBIDDEN_MESSAGE="Bu sayfaya erişmek için öncelikli olarak login olmanız gerekmektedir.";
    public static final String ACCESS_DENIED_MESSAGE="You dont have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD="OPTIONS";
    public static final String[] PUBLIC_URLS={"/user/login","/user/register","/user/resetpassword/**","/user/image/**"};
}
