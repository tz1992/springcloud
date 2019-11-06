package com.tz.fastJson.entity;

import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "App")
public class AppEnv implements Serializable {
    private static final long serialVersionUID = -6955087189769303995L;
    private boolean dev = false;
    private String name;
    private String dbName;
    private String tablePrefix;
    private String scope;
    private String tenantId;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTablePrefix() {
        return this.tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean isDev() {
        return this.dev;
    }

    public void setDev(boolean dev) {
        this.dev = dev;
    }
}