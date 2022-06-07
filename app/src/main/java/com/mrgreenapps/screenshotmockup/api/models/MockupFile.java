package com.mrgreenapps.screenshotmockup.api.models;

import java.io.Serializable;

public class MockupFile implements Serializable {
    public int id;
    public String name;
    public String path;
    public String url;

    public MockupFile(String name, String path, String url) {
        this.name = name;
        this.path = path;
        this.url = url;
    }
}
