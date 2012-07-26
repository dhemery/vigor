package com.dhemery.victor.examples.views;

import com.dhemery.polling.Query;
import com.dhemery.victor.examples.tests.PrefixFieldSetText;

public class UITextFieldTextQuery extends Query<UITextField, String> {
    @Override
    public String name() {
        return "text";
    }

    @Override
    public String query(UITextField field) {
        return field.sendMessage("text").get(0);
    }

    public static Query<UITextField, String> text() {
        return new UITextFieldTextQuery();
    }
}
