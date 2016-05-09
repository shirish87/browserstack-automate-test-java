package com.browserstack.automate.test;

import com.browserstack.client.model.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class TestHelper {

    public static DesiredCapabilities getCapabilities(String browserName) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browserName);
        caps.setCapability("project", getProjectName());
        caps.setCapability("build", getBuildName());
        caps.setCapability("name", getSessionName(browserName));
        return caps;
    }

    public static String getSessionName(String browser) {
        return getProjectName() + ": " + browser;
    }

    public static String getSessionName(Browser browser) {
        String browserId = (browser.getDevice() != null) ? browser.getDevice() : browser.getBrowserVersion();
        return getSessionName(browser.getBrowser() + ":" + browserId);
    }

    public static String getProjectName() {
        return "Automate Java";
    }

    public static String getBuildName() {
        String envBuildId = System.getenv("BUILD_NUMBER");
        return "JUnit|TestNG" + ((envBuildId != null) ? ": " + envBuildId : "");
    }

    public static String getBrowsersJson() throws IOException, URISyntaxException {
        URL browsersUrl = AutomateParameterizedTest.class.getResource("/browsers.json");
        return new Scanner(new File(browsersUrl.toURI()), "UTF8").useDelimiter("\\Z").next();
    }
}
