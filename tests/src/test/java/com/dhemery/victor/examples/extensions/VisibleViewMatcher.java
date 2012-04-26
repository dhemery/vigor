package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.IosView;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class VisibleViewMatcher extends TypeSafeMatcher<IosView> {

    @Override
    protected boolean matchesSafely(IosView view) {
        List<String> results = view.sendMessage("isHidden");
        if (results.size() != 1) return false;
        return !Boolean.parseBoolean(results.get(0));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("visible");
    }
}
