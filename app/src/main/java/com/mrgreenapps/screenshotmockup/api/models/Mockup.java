package com.mrgreenapps.screenshotmockup.api.models;

import java.util.List;

public class Mockup {
    public String name;
    public String description;
    public List<MockupFile> mockupFiles;
    public MockupConfig mockupConfig;

    public Mockup(String name, String description, List<MockupFile> mockupFiles, MockupConfig mockupConfig) {
        this.name = name;
        this.description = description;
        this.mockupFiles = mockupFiles;
        this.mockupConfig = mockupConfig;
    }
}
