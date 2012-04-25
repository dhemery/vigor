package com.dhemery.victor.examples.runner;

import com.dhemery.victor.device.LocalSimulator;
import com.dhemery.victor.device.Simulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class CreateSimulator {
    public static final String DEFAULT_SDK_ROOT_FOR_DEVELOPER_ROOT_AND_SDK_VERSION = "%s/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator%s.sdk";
    public static final String DEFAULT_SDK_VERSION = "5.1";
    public static final String DEFAULT_SIMULATOR_BINARY_PATH_FOR_DEVELOPER_ROOT = "%s/Platforms/iPhoneSimulator.platform/Developer/Applications/iPhone Simulator.app/Contents/MacOS/iPhone Simulator";

    public static final String DEVELOPER_ROOT_PROPERTY = "victor.developer.root";
    public static final String SDK_ROOT_PROPERTY = "victor.sdk.root";
    public static final String SDK_VERSION_PROPERTY = "victor.sdk.version";
    public static final String SIMULATOR_BINARY_PATH_PROPERTY = "victor.simulator.binary.path";

    private final Logger log = LoggerFactory.getLogger(getClass());
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
        String sdkRoot = sdkRoot();
        log.info("sdk root {} ", sdkRoot);
        return new LocalSimulator(sdkRoot, simulatorBinaryPath());
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




    private String defaultDeveloperRoot() {
        try {
            Process process = new ProcessBuilder().command("xcode-select", "-print-path").start();
            return outputFromProcess(process);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String outputFromProcess(Process process) throws IOException {
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }

    // todo Use SDKROOT if specified.
    private String defaultSdkRoot() {
        return String.format(DEFAULT_SDK_ROOT_FOR_DEVELOPER_ROOT_AND_SDK_VERSION, developerRoot(), sdkVersion());
    }

    // todo Default to the latest installed SDK.
    private String defaultSdkVersion() {
        return DEFAULT_SDK_VERSION;
    }

    private String defaultSimulatorBinaryPath() {
        return String.format(DEFAULT_SIMULATOR_BINARY_PATH_FOR_DEVELOPER_ROOT, developerRoot());
    }
}
