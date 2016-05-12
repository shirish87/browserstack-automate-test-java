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
public class AutomateParameterizedTest {

    private RemoteWebDriver webDriver;
    private Browser browser;

    public AutomateParameterizedTest(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "BrowserStack:{index}")
    public static LinkedList<Browser[]> getEnvironments() throws Exception {
        LinkedList<Browser[]> env = new LinkedList<Browser[]>();

        try {
            for (Browser browser : AutomateTestHelper.parseBrowsers(TestHelper.getBrowsersJson())) {
                env.add(new Browser[]{browser});
            }
        } catch (Exception e) {
            Browser browser = new Browser();
            browser.setBrowser("chrome");
            browser.setBrowserVersion("49.0");
            env.add(new Browser[]{browser});
        }

        return env;
    }

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("name", TestHelper.getSessionName(browser));
        caps.setCapability("project", TestHelper.getProjectName());
        caps.setCapability("build", TestHelper.getBuildName());
        AutomateTestHelper.applyBrowser(browser, caps);
        webDriver = new RemoteWebDriver(new URL(TestHelper.getHubUrl()), caps);
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

}
