package com.dhemery.victor.examples.runner;

public class CreateIosDevice {
    static MySimulatedIosDevice withCapabilities(IosDeviceCapabilities deviceCapabilities) {
        MySimulator simulator = new MySimulator(deviceCapabilities.sdkRoot(), deviceCapabilities.simulatorBinaryPath(), deviceCapabilities.applicationBinaryPath());
        return new MySimulatedIosDevice(simulator);
    }
}
