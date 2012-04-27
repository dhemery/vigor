package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.IosView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ViewIsAnimatingMatcher extends TypeSafeMatcher<IosView> {
    private String reason;

    @Override
    protected boolean matchesSafely(IosView view) {
        List<String> results = view.sendMessage("isAnimating");
        if (results.size() != 1) {
            reason = String.format("false because %s had size %s", view, results.size());
            return false;
        }
        if (!Boolean.parseBoolean(results.get(0))) {
            reason = String.format("false because %s isAnimating was %s", view, false);
            return false;
        }
        reason = results.get(0);
        return true;
    }

    @Override
    protected void describeMismatchSafely(IosView item, Description mismatchDescription) {
        super.describeMismatchSafely(item, mismatchDescription);
        mismatchDescription.appendText(reason);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("animating");
    }

    public static Matcher<? super IosView> animating() {
        return new ViewIsAnimatingMatcher();
    }
}
