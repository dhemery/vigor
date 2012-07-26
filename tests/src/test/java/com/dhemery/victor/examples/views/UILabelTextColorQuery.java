package com.dhemery.victor.examples.views;

import com.dhemery.polling.Query;

public class UILabelTextColorQuery extends Query<UILabel, String> {
    @Override
    public String name() {
        return "text color";
    }

    @Override
    public String query(UILabel label) {
        return label.textColor().get(0);
    }

    public static Query<UILabel, String> textColor() {
        return new UILabelTextColorQuery();
    }
}
