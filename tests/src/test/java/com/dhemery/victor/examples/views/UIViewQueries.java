package com.dhemery.victor.examples.views;

import com.dhemery.core.Feature;
import com.dhemery.core.NamedFeature;

import java.util.List;

public class UIViewQueries {
    public static Feature<UIView,Boolean> animating() {
        return new NamedFeature<UIView,Boolean>("animating") {
            @Override
            public Boolean of(UIView view) {
                List<String> results = view.sendMessage("isAnimating");
                if (results.size() != 1) {
                    return false;
                }
                return Boolean.parseBoolean(results.get(0));
            }
        };
    }

    public static Feature<UIView,String> backgroundColor() {
        return new NamedFeature<UIView, String>("background color") {
            @Override
            public String of(UIView view) {
                return view.sendMessage("backgroundColor").get(0);
            }
        };
    }

    public static Feature<UIView,Integer> count() {
        return new NamedFeature<UIView, Integer>("count") {
            @Override
            public Integer of(UIView views) {
                return views.sendMessage("tag").size();
            }
        };
    }

    public static Feature<UIView,Boolean> visible() {
        return new NamedFeature<UIView,Boolean>("visible") {
            @Override
            public Boolean of(UIView view) {
                return view.isVisible();
            }
        };
    }
}
