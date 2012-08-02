package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;

public class UILabelQueries {
    public static Query<UILabel, String> textColor() {
        return new NamedQuery<UILabel, String>("text color") {
            @Override
            public String query(UILabel label) {
                return label.textColor().get(0);
            }
        };
    }
}
