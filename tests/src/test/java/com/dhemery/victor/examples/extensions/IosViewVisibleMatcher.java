package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.IosView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class IosViewVisibleMatcher extends TypeSafeMatcher<IosView> {
    private String reason;

    @Override
    protected boolean matchesSafely(IosView view) {
        List<String> results = view.sendMessage("isHidden");
        if (results.size() != 1) {
            reason = String.format("was false because %s had size %s", view, results.size());
            return false;
        }
        if (Boolean.parseBoolean(results.get(0))) {
            reason = String.format("was false because %s was hidden", view);
            return false;
        }
        reason = "";
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("visible");
    }

    @Override
    protected void describeMismatchSafely(IosView item, Description mismatchDescription) {
        mismatchDescription.appendText(reason);
    }

    public static Matcher<IosView> visible() {
        return new IosViewVisibleMatcher();
    }
}
