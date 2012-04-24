package com.dhemery.victor.examples.runner;

import com.dhemery.victor.simulator.local.LocalSimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// todo create process-kit with my own ProcessBuilder class
public class MyLocalSimulator extends LocalSimulator {
    private static LocalSimulator simulator;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final List<String> baseCommand;
    private Process process;

    public MyLocalSimulator(String sdkRoot, String simulatorBinaryPath) {
        log.debug("New simulator at SDK Root {} at path {}", sdkRoot, simulatorBinaryPath);
        baseCommand = new ArrayList<String>();
        baseCommand.add(simulatorBinaryPath);
        baseCommand.add("-currentSdkRoot");
        baseCommand.add(sdkRoot);
    }

    public void startWithApplication(String applicationBinaryPath) {
        log.debug("simulator start");
        List<String> command = new ArrayList<String>(baseCommand);
        command.add("-SimulateApplication");
        command.add(applicationBinaryPath);
        ProcessBuilder processBuilder = new ProcessBuilder().command(command);
        log.debug("Launching {}", command);
        try {
            process = processBuilder.start();
        } catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }

    public void stop() {
        log.debug("simulator stop");
        touchMenuItem("iOS Simulator", "Quit iOS Simulator");
        try {
            process.waitFor();
        } catch (InterruptedException cause) {
            throw new RuntimeException("Interrupted while waiting for simulator process to stop.", cause);
        }
    }
}
