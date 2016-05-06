package com.browserstack.automate.test;

public class TestHelper {

    public static org.openqa.selenium.remote.DesiredCapabilities getCapabilities(String browserName) {
        org.openqa.selenium.remote.DesiredCapabilities caps = new org.openqa.selenium.remote.DesiredCapabilities();
        caps.setBrowserName(browserName);
        caps.setCapability("project", "Automate Java");

        String envBuildId = System.getenv("BUILD_NUMBER");
        caps.setCapability("build", "JUnit|TestNG" + ((envBuildId != null) ? ": " + envBuildId : ""));
        return caps;
    }
}
