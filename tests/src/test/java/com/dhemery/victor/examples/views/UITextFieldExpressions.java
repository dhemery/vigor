package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

public class UITextFieldExpressions {
    public static Matcher<UITextField> editing() {
        return new CustomTypeSafeMatcher<UITextField>("editing"){
            @Override
            protected boolean matchesSafely(UITextField textField) {
                return Boolean.parseBoolean(textField.sendMessage("isEditing").get(0));
            }
        };
    }

    public static Query<UITextField,String> text() {
        return new NamedQuery<UITextField, String>("text") {
            @Override
            public String query(UITextField textField) {
                return textField.sendMessage("text").get(0);
            }
        };
    }
}
