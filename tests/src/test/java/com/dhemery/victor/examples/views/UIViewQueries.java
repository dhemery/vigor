package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;

import java.util.List;

public class UIViewQueries {
    public static Query<UIView,String> backgroundColor() {
        return new NamedQuery<UIView, String>("background color") {
            @Override
            public String query(UIView object) {
                return object.sendMessage("backgroundColor").get(0);
            }
        };
    }

    public static Query<UIView,Integer> count() {
        return new NamedQuery<UIView, Integer>("count") {
            @Override
            public Integer query(UIView views) {
                return views.sendMessage("tag").size();
            }
        };
    }

    public static Query<UIView,Boolean> isAnimating() {
        return new NamedQuery<UIView,Boolean>("is animating") {
            @Override
            public Boolean query(UIView view) {
                List<String> results = view.sendMessage("isAnimating");
                if (results.size() != 1) {
                    return false;
                }
                return Boolean.parseBoolean(results.get(0));
            }
        };
    }

    public static Query<UIView,Boolean> isVisible() {
        return new NamedQuery<UIView,Boolean>("is visible") {
            @Override
            public Boolean query(UIView view) {
                return view.isVisible();
            }
        };
    }
}
