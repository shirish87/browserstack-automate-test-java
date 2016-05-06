package com.browserstack.automate.test;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class TestNGTest {

    private static String username = System.getenv("BROWSERSTACK_USER");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESSKEY");
    private static String hubUrl = String.format("https://%s:%s@hub.browserstack.com/wd/hub", username, accessKey);

    private RemoteWebDriver webDriver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        webDriver = new RemoteWebDriver(new URL(hubUrl), TestHelper.getCapabilities("chrome"));
    }

    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testNG1() {
        webDriver.get("https://www.google.com");
    }

    @Test
    public void testNG2() {
        webDriver.get("https://www.google.com/ncr");
    }
}
