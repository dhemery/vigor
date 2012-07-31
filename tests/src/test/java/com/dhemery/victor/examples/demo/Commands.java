package com.dhemery.victor.examples.demo;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.BeforeClass;
import org.junit.Test;

public class Commands extends OnMasterPage {
    @BeforeClass
    public static void divertLaunch() {
        configuration().define("victor.simulator.process.owner", "me");
    }

    @Test public void beginEditingRobustly() { prefixField.tap(); }
    @Test public void setTextRobustly() { prefixField.sendMessage("DFX_setText:", "Robustly"); }
    @Test public void endEditingRobustly() { prefixField.done(); }
    @Test public void switchOnRobustly() { prefixSwitch.turnOn(); }
    @Test public void switchOffRobustly() { prefixSwitch.turnOff(); }

    @Test public void beginEditingNaively() { prefixField.sendMessage("becomeFirstResponder"); }
    @Test public void setTextNaively() { prefixField.sendMessage("setText:", "naively"); }
    @Test public void endEditingNaively() { prefixField.sendMessage("resignFirstResponder"); }
    @Test public void switchOnNaively() { prefixSwitch.sendMessage("setOn:animated:", true, true); }
    @Test public void switchOffNaively() { prefixSwitch.sendMessage("setOn:animated:", false, true); }
}
