package com.browserstack.automate.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AutomateClassDefTest {

    private static String username = System.getenv("BROWSERSTACK_USER");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESSKEY");
    private static String hubUrl = String.format("https://%s:%s@hub.browserstack.com/wd/hub", username, accessKey);

    private static RemoteWebDriver webDriver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        webDriver = new RemoteWebDriver(new URL(hubUrl), TestHelper.getCapabilities("chrome"));
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
