package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedMatcher;
import org.hamcrest.Matcher;

public class UISwitchExpressions {
    public static Matcher<UISwitch> off() {
        return new NamedMatcher<UISwitch>("off") {
            @Override
            protected boolean matchesSafely(UISwitch theSwitch) {
                return theSwitch.isOn();
            }
        };
    }
    public static Matcher<UISwitch> on() {
        return new NamedMatcher<UISwitch>("on") {
            @Override
            protected boolean matchesSafely(UISwitch theSwitch) {
                return !theSwitch.isOn();
            }
        };
    }
}
