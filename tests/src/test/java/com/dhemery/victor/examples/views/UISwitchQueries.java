package com.dhemery.victor.examples.views;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

public class UISwitchQueries {
    public static Matcher<UISwitch> isOff() {
        return new CustomTypeSafeMatcher<UISwitch>("is off") {
            @Override
            protected boolean matchesSafely(UISwitch theSwitch) {
                return theSwitch.isOn();
            }
        };
    }
    public static Matcher<UISwitch> isOn() {
        return new CustomTypeSafeMatcher<UISwitch>("is on") {
            @Override
            protected boolean matchesSafely(UISwitch theSwitch) {
                return !theSwitch.isOn();
            }
        };
    }
}
