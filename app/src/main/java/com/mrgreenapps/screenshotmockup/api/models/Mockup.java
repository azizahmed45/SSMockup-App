package com.mrgreenapps.screenshotmockup.api.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Mockup implements Serializable {

    public int id;
    public String name;
    public String description;

    @SerializedName("mockup_files")
    public List<MockupFile> mockupFiles;

    @SerializedName("config")
    public MockupConfig mockupConfig;

    public Mockup(String name, String description, List<MockupFile> mockupFiles, MockupConfig mockupConfig) {
        this.name = name;
        this.description = description;
        this.mockupFiles = mockupFiles;
        this.mockupConfig = mockupConfig;
    }
}
