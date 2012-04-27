package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.IosView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class ViewIsTappableMatcher extends TypeSafeMatcher<IosView> {

    @Override
    protected boolean matchesSafely(IosView view) {
        List<String> results = view.sendMessage("isTappable");
        if (results.size() != 1) return false;
        return Boolean.parseBoolean(results.get(0));
    }

    @Override
    protected void describeMismatchSafely(IosView item, Description mismatchDescription) {
        super.describeMismatchSafely(item, mismatchDescription);
        mismatchDescription.appendText(" not tappable");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("tappable");
    }

    public static Matcher<? super IosView> tappable() {
        return new ViewIsTappableMatcher();
    }
}
