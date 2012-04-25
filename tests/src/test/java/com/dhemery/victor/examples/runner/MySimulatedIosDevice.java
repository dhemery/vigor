package com.dhemery.victor.examples.runner;

import com.dhemery.victor.device.SimulatedIosDevice;

public class MySimulatedIosDevice extends SimulatedIosDevice{
    private final MySimulator simulator;

    public MySimulatedIosDevice(MySimulator simulator) {
        super(simulator);
        this.simulator = simulator;
    }

    public void start() {
        simulator.start();
    }

    public void stop() {
        simulator.stop();
    }
}
