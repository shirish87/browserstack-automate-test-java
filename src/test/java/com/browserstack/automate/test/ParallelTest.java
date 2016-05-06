package com.browserstack.automate.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

@RunWith(Parallelized.class)
public class ParallelTest {

    private static String username = System.getenv("BROWSERSTACK_USER");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESSKEY");
    private static String hubUrl = String.format("https://%s:%s@hub.browserstack.com/wd/hub", username, accessKey);

    private RemoteWebDriver webDriver;
    private String browserName;

    @Before
    public void setUp() throws MalformedURLException {
        webDriver = new RemoteWebDriver(new URL(hubUrl), TestHelper.getCapabilities(browserName));
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void test1() {
        webDriver.get("https://www.google.com");
    }

    @Test
    public void test2() {
        webDriver.get("https://www.google.com/ncr");
    }

    public ParallelTest(String browserName) {
        this.browserName = browserName;
    }


    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"chrome"});
        env.add(new String[]{"firefox"});
        env.add(new String[]{"ie"});
        env.add(new String[]{"opera"});
        return env;
    }

}
