package com.crawler.parser.dto;

public class Source {

    String name;
    String raw;
    String schema;
    String www;
    String subDomain;
    String domain;
    String path;
    String attributes;

    public Source() {
    }

    public Source(String raw) {
        this.raw = raw;
    }

    public Source(String name, String raw, String schema, String www, String subDomain, String domain, String path, String attributes) {
        this.name = name;
        this.raw = raw;
        this.schema = schema;
        this.www = www;
        this.subDomain = subDomain;
        this.domain = domain;
        this.path = path;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
