package com.dhemery.victor.examples.views;

import com.dhemery.core.Feature;
import com.dhemery.core.NamedFeature;

public class UISwitchQueries {
    public static Feature<UISwitch,Boolean> off() {
        return new NamedFeature<UISwitch,Boolean>("off") {
            @Override
            public Boolean of(UISwitch theSwitch) {
                return theSwitch.isOn();
            }
        };
    }

    public static Feature<UISwitch,Boolean> on() {
        return new NamedFeature<UISwitch,Boolean>("on") {
            @Override
            public Boolean of(UISwitch theSwitch) {
                return !theSwitch.isOn();
            }
        };
    }
}
