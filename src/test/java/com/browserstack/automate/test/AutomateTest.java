package com.browserstack.automate.test;

import com.browserstack.automate.helper.AutomateTestHelper;
import com.browserstack.client.model.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

@RunWith(Parallelized.class)
public class AutomateTest {

    private static String username = System.getenv("BROWSERSTACK_USER");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESSKEY");
    private static String hubUrl = String.format("https://%s:%s@hub.browserstack.com/wd/hub", username, accessKey);

    private RemoteWebDriver webDriver;
    private Browser browser;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("build", this.getClass().getSimpleName());
        caps.setCapability("name", "Automate Test: " + browser.getBrowser());
        AutomateTestHelper.applyBrowser(browser, caps);
        webDriver = new RemoteWebDriver(new URL(hubUrl), caps);
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void test1() {
        webDriver.get("https://www.google.co.in/?q=test1");
    }

    @Test
    public void test2() throws IOException {
        webDriver.get("https://www.google.com/search?hl=en&source=hp&q=test2");
    }

    public AutomateTest(Browser browser) {
        this.browser = browser;
    }


    @Parameterized.Parameters(name = "BrowserStack:{index}")
    public static LinkedList<Browser[]> getEnvironments() throws Exception {
        LinkedList<Browser[]> env = new LinkedList<Browser[]>();

        try {
            for (Browser browser : AutomateTestHelper.parseBrowsers()) {
                env.add(new Browser[]{browser});
            }
        } catch (IOException e) {
            Browser browser = new Browser();
            browser.setBrowser("chrome");
            browser.setBrowserVersion("49.0");
            env.add(new Browser[]{browser});
        }

        return env;
    }

}