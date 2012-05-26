package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.examples.views.UIView;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ViewVisibleMatcher extends TypeSafeMatcher<UIView> {
    @Override
    protected boolean matchesSafely(UIView view) {
        return view.isVisible();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("visible");
    }

    public static ViewVisibleMatcher visible() {
        return new ViewVisibleMatcher();
    }
}
