package com.dhemery.victor.examples.views;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class UITextFieldEditingMatcher extends TypeSafeDiagnosingMatcher<UITextField> {
    public static Matcher<UITextField> editing() {
        return new UITextFieldEditingMatcher();
    }

    @Override
    protected boolean matchesSafely(UITextField field, Description mismatchDescription) {
        mismatchDescription.appendText("not editing");
        return Boolean.parseBoolean(field.sendMessage("isEditing").get(0));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("editing");
    }
}
