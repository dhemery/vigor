package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.frank.FrankAgent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class FrankAgentReadyMatcher extends TypeSafeMatcher<FrankAgent> {
    @Override
    public void describeTo(Description description) {
        description.appendText("ready to respond to requests");
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
        mismatchDescription.appendValue(frank)
                .appendText(" is not ready to respond to requests");
    }

    public static Matcher<FrankAgent> ready() {
        return new FrankAgentReadyMatcher();
    }
}
