package com.dhemery.victor.examples.runner;

import com.dhemery.victor.device.LocalSimulator;
import com.dhemery.victor.device.Simulator;

import java.util.Properties;

public class CreateSimulator {
    public static final String DEFAULT_DEVELOPER_ROOT = "/Applications/Xcode.app/Contents/Developer";
    public static final String DEFAULT_SDK_ROOT_TEMPLATE = "%s/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator%s.sdk";
    public static final String DEFAULT_SDK_VERSION = "5.1";
    public static final String DEFAULT_SIMULATOR_BINARY_PATH_TEMPLATE = "%s/Platforms/iPhoneSimulator.platform/Developer/Applications/iPhone Simulator.app/Contents/MacOS/iPhone Simulator";

    public static final String DEVELOPER_ROOT_PROPERTY = "victor.developer.root";
    public static final String SDK_ROOT_PROPERTY = "victor.sdk.root";
    public static final String SDK_VERSION_PROPERTY = "victor.sdk.version";
    public static final String SIMULATOR_BINARY_PATH_PROPERTY = "victor.simulator.binary.path";

    private final Properties properties;

    public static Simulator fromProperties(Properties properties) {
        return new CreateSimulator(properties).create();
    }

    public static Simulator with(String simulatorBinaryPath, String developerRoot, String sdkVersion) {
        Properties properties = new Properties();
        properties.setProperty(SIMULATOR_BINARY_PATH_PROPERTY, simulatorBinaryPath);
        properties.setProperty(DEVELOPER_ROOT_PROPERTY, developerRoot);
        properties.setProperty(SDK_VERSION_PROPERTY, sdkVersion);
        return fromProperties(properties);
    }

    public static Simulator with(String simulatorBinaryPath, String sdkRoot) {
        Properties properties = new Properties();
        properties.setProperty(SIMULATOR_BINARY_PATH_PROPERTY, simulatorBinaryPath);
        properties.setProperty(SDK_ROOT_PROPERTY, sdkRoot);
        return fromProperties(properties);
    }

    private CreateSimulator(Properties properties) {
        this.properties = new Properties(properties);
    }

    private Simulator create() {
        return new LocalSimulator(sdkRoot(), simulatorBinaryPath());
    }

    private String developerRoot() {
        return properties.getProperty(DEVELOPER_ROOT_PROPERTY, defaultDeveloperRoot());
    }

    private String sdkRoot() {
        return properties.getProperty(SDK_ROOT_PROPERTY, defaultSdkRoot());
    }

    private String sdkVersion() {
        return properties.getProperty(SDK_VERSION_PROPERTY, defaultSdkVersion());
    }

    private String simulatorBinaryPath() {
        return properties.getProperty(SIMULATOR_BINARY_PATH_PROPERTY, defaultSimulatorBinaryPath());
    }





    // todo use xcode-switch -print-path
    private String defaultDeveloperRoot() {
        return DEFAULT_DEVELOPER_ROOT;
    }

    private String defaultSdkRoot() {
        return String.format(DEFAULT_SDK_ROOT_TEMPLATE, developerRoot(), sdkVersion());
    }

    // todo Default to the latest installed SDK.
    private String defaultSdkVersion() {
        return DEFAULT_SDK_VERSION;
    }

    private String defaultSimulatorBinaryPath() {
        return String.format(DEFAULT_SIMULATOR_BINARY_PATH_TEMPLATE, developerRoot());
    }
}
