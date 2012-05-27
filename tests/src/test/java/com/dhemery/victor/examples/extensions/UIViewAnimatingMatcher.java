package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.examples.views.UIView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class UIViewAnimatingMatcher extends TypeSafeMatcher<UIView> {
    private String reason;

    @Override
    protected boolean matchesSafely(UIView view) {
        List<String> results = view.sendMessage("isAnimating");
        if (results.size() != 1) {
            return false;
        }
        if (!Boolean.parseBoolean(results.get(0))) {
            return false;
        }
        return true;
    }

    @Override
    protected void describeMismatchSafely(UIView item, Description mismatchDescription) {
        super.describeMismatchSafely(item, mismatchDescription);
        mismatchDescription.appendText(reason);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("animating");
    }

    public static Matcher<UIView> animating() {
        return new UIViewAnimatingMatcher();
    }
}
