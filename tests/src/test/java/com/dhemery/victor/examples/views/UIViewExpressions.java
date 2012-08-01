package com.dhemery.victor.examples.views;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

import java.util.List;

public class UIViewExpressions {
    public static Matcher<UIView> animating() {
        return new CustomTypeSafeMatcher<UIView>("animating") {
            @Override
            protected boolean matchesSafely(UIView view) {
                List<String> results = view.sendMessage("isAnimating");
                if (results.size() != 1) {
                    return false;
                }
                return Boolean.parseBoolean(results.get(0));
            }
        };
    }

    public static Matcher<UIView> visible() {
        return new CustomTypeSafeMatcher<UIView>("visible") {
            @Override
            protected boolean matchesSafely(UIView view) {
                return view.isVisible();
            }
        };
    }

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
}
