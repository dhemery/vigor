package com.dhemery.victor.examples.views;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class UIViewVisibleMatcher extends TypeSafeMatcher<UIView> {
    @Override
    protected boolean matchesSafely(UIView view) {
        return view.isVisible();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("visible");
    }

    public static UIViewVisibleMatcher visible() {
        return new UIViewVisibleMatcher();
    }
}
