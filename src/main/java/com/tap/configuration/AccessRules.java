package com.tap.configuration;

public final class AccessRules {

    private AccessRules() {}

    public static final String PLATFORM_ADMIN = "hasRole('ADMIN')";
    public static final String STORE_ADMIN    = "hasRole('STORE_ADMIN')";
    public static final String STORE_LEVEL    = "hasAnyRole('STORE_ADMIN','STORE_MANAGER')";
    public static final String BRANCH_LEVEL   = "hasAnyRole('STORE_ADMIN','STORE_MANAGER','BRANCH_MANAGER')";
    public static final String POS_OPERATOR   = "hasAnyRole('BRANCH_CASHIER','BRANCH_MANAGER')";
    public static final String CASHIER        = "hasRole('BRANCH_CASHIER')";
    public static final String ALL_STAFF      = "hasAnyRole('STORE_ADMIN','STORE_MANAGER','BRANCH_MANAGER','BRANCH_CASHIER')";
    public static final String ANY_USER       = "isAuthenticated()";
}