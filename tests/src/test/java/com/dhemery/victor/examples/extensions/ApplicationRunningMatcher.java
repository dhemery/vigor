package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.IosApplication;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ApplicationRunningMatcher extends TypeSafeMatcher<IosApplication> {
    @Override
    public void describeTo(Description description) {
        description.appendText("running");
    }

    @Override
    protected boolean matchesSafely(IosApplication application) {
        try {
            return application.isRunning();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(IosApplication application, Description mismatchDescription) {
        super.describeMismatch(application, mismatchDescription);
        mismatchDescription.appendText(" not running");
    }

    public static Matcher<IosApplication> running() {
        return new ApplicationRunningMatcher();
    }
}
