package com.browserstack.automate.test;

import com.browserstack.client.model.Browser;

public class TestHelper {

    public static org.openqa.selenium.remote.DesiredCapabilities getCapabilities(String browserName) {
        org.openqa.selenium.remote.DesiredCapabilities caps = new org.openqa.selenium.remote.DesiredCapabilities();
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
}
