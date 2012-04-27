package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.frank.FrankAgent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class FrankRespondsMatcher extends TypeSafeMatcher<FrankAgent> {
    @Override
    public void describeTo(Description description) {
        description.appendText("responds to requests");
    }

    @Override
    protected boolean matchesSafely(FrankAgent frank) {
        try {
            return frank.isReady();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(FrankAgent frank, Description mismatchDescription) {
        super.describeMismatch(frank, mismatchDescription);
        mismatchDescription.appendText(" does not respond to requests");
    }

    public static Matcher<FrankAgent> respondsToRequests() {
        return new FrankRespondsMatcher();
    }
}
