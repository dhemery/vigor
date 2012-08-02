package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;

public class UITextFieldQueries {
    public static Query<UITextField,Boolean> isEditing() {
        return new NamedQuery<UITextField,Boolean>("is editing"){
            @Override
            public Boolean query(UITextField textField) {
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
