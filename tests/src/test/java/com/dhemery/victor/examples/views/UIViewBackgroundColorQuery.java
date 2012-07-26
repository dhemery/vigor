package com.dhemery.victor.examples.views;

import com.dhemery.polling.Query;

public class UIViewBackgroundColorQuery extends Query<UIView, String>{
    @Override
    public String name() {
        return "background color";
    }

    @Override
    public String query(UIView view) {
        return view.backgroundColor().get(0);
    }

    public static Query<UIView, String> backgroundColor() {
        return new UIViewBackgroundColorQuery();
    }
}
