package com.interfaceproject.listener;

import org.testng.TestNG;

public class TestNgOverride extends TestNG {
    private static final String OUTPUT_FOLDER = "testoutput";
    public TestNgOverride() {
        super.setOutputDirectory(OUTPUT_FOLDER);
    }
}
