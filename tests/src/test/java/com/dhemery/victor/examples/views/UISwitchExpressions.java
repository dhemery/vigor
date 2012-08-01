package com.dhemery.victor.examples.views;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

public class UISwitchExpressions {
    public static Matcher<UISwitch> off() {
        return new CustomTypeSafeMatcher<UISwitch>("off") {
            @Override
            protected boolean matchesSafely(UISwitch theSwitch) {
                return theSwitch.isOn();
            }
        };
    }
    public static Matcher<UISwitch> on() {
        return new CustomTypeSafeMatcher<UISwitch>("on") {
            @Override
            protected boolean matchesSafely(UISwitch theSwitch) {
                return !theSwitch.isOn();
            }
        };
    }
}
