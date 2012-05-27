package com.dhemery.victor.examples.extensions;

import com.dhemery.polling.Query;
import com.dhemery.victor.examples.views.UIView;

public class UIViewCountQuery extends Query<UIView, Integer> {
    @Override
    public String name() {
        return "count";
    }

    @Override
    public Integer query(UIView view) {
        return view.sendMessage("tag").size();
    }

    public static Query<UIView, Integer> count() {
        return new UIViewCountQuery();
    }
}
