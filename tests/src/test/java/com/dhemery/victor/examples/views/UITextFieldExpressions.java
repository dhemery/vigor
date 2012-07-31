package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedMatcher;
import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;
import org.hamcrest.Matcher;

public class UITextFieldExpressions {
    public static Matcher<UITextField> editing() {
        return new NamedMatcher<UITextField>("editing"){
            @Override
            protected boolean matchesSafely(UITextField field) {
                return Boolean.parseBoolean(field.sendMessage("isEditing").get(0));
            }
        };
    }

    public static Query<UITextField,String> text() {
        return new NamedQuery<UITextField, String>("text") {
            @Override
            public String currentValueFrom(UITextField object) {
                return object.sendMessage("text").get(0);
            }
        };
    }
}
