package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.frank.IosApplicationAgent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class AgentReadyMatcher extends TypeSafeMatcher<IosApplicationAgent> {
    @Override
    public void describeTo(Description description) {
        description.appendText("ready to respond to requests");
    }

    @Override
    protected boolean matchesSafely(IosApplicationAgent agent) {
        try {
            return agent.isReady();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(IosApplicationAgent agent, Description mismatchDescription) {
        mismatchDescription.appendValue(agent)
                .appendText(" is not ready to respond to requests");
    }

    public static Matcher<IosApplicationAgent> ready() {
        return new AgentReadyMatcher();
    }
}
