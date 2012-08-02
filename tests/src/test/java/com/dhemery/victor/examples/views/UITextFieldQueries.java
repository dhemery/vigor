package com.dhemery.victor.examples.views;

import com.dhemery.core.Feature;
import com.dhemery.core.NamedFeature;

public class UITextFieldQueries {
    public static Feature<UITextField,Boolean> editing() {
        return new NamedFeature<UITextField,Boolean>("editing"){
            @Override
            public Boolean of(UITextField textField) {
                return Boolean.parseBoolean(textField.sendMessage("isEditing").get(0));
            }
        };
    }

    public static Feature<UITextField,String> text() {
        return new NamedFeature<UITextField, String>("text") {
            @Override
            public String of(UITextField textField) {
                return textField.sendMessage("text").get(0);
            }
        };
    }
}
