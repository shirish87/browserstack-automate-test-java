package com.browserstack.automate.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AutomateClassDefTest {

    private static RemoteWebDriver webDriver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        webDriver = new RemoteWebDriver(new URL(TestHelper.getHubUrl()), TestHelper.getCapabilities("chrome"));
    }

    @AfterClass
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testCommon1() {
        webDriver.get("https://www.google.com");
    }

    @Test
    public void testCommon2() {
        webDriver.get("https://www.google.com/ncr");
    }
}
