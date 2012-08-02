package com.dhemery.victor.examples.views;

import com.dhemery.core.Feature;
import com.dhemery.core.NamedFeature;

public class UILabelQueries {
    public static Feature<UILabel, String> textColor() {
        return new NamedFeature<UILabel, String>("text color") {
            @Override
            public String of(UILabel label) {
                return label.textColor().get(0);
            }
        };
    }
}
