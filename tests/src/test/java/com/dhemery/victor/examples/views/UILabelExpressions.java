package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;

public class UILabelExpressions {
    public static Query<UILabel, String> textColor() {
        return new NamedQuery<UILabel, String>("text color") {
            @Override
            public String currentValueFrom(UILabel label) {
                return label.textColor().get(0);
            }
        };
    }
}
