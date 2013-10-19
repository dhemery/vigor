package com.dhemery.victor.examples.views;

import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosView;
import com.dhemery.victor.IosViewIdentifier;

import static com.dhemery.victor.Igor.igor;

public class UISwitch extends UIView {
    public UISwitch(IosApplication application, IosViewIdentifier id) {
        super(application, id);
    }

    public boolean isOn() {
        return Boolean.parseBoolean(this.sendMessage("isOn").get(0));
    }

    public void turnOff() {
        if(isOn()) tap();
    }

    public void turnOn() {
        if(!isOn()) tap();
    }

    @Override
    public void tap() {
        sendMessage("touchAndHold:", 1);
    }
}
