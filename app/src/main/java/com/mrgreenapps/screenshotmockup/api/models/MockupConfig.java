package com.mrgreenapps.screenshotmockup.api.models;

import java.io.Serializable;

public class MockupConfig implements Serializable {

    public int left, top, right, bottom;

    public MockupConfig(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
}
