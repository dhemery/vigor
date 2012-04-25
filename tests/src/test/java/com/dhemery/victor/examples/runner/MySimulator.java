package com.dhemery.victor.examples.runner;

import com.dhemery.victor.device.LocalSimulator;

public class MySimulator extends LocalSimulator {
    private final String applicationBinaryPath;

    /**
     * @param sdkRoot             the path to the SDK to use for the simulation.
     * @param simulatorBinaryPath the path to the Simulator executable on this computer.
     */
    public MySimulator(String sdkRoot, String simulatorBinaryPath, String applicationBinaryPath) {
        super(sdkRoot, simulatorBinaryPath);
        this.applicationBinaryPath = applicationBinaryPath;
    }

    public void start() {
        startWithApplication(applicationBinaryPath);
    }
}
