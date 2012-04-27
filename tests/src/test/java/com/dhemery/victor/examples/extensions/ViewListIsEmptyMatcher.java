package com.dhemery.victor.examples.extensions;

import com.dhemery.victor.IosView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class ViewListIsEmptyMatcher extends TypeSafeMatcher<IosView> {
    private int lastSize;

    @Override
    protected boolean matchesSafely(IosView item) {
        List<String> items = item.sendMessage("tag");
        lastSize = items.size();  // Save to report after mismatch.
        return items.isEmpty();
    }

    @Override
    protected void describeMismatchSafely(IosView item, Description mismatchDescription) {
        super.describeMismatchSafely(item, mismatchDescription);
        mismatchDescription.appendText(" list with size ").appendValue(lastSize);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("empty");
    }

    public static Matcher<? super IosView> empty() {
        return new ViewListIsEmptyMatcher();
    }
}
