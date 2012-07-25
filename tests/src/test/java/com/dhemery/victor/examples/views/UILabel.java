package com.dhemery.victor.examples.views;

import com.dhemery.victor.By;
import com.dhemery.victor.IosViewFactory;

import java.util.List;

public class UILabel extends UIView {
    public UILabel(IosViewFactory application, By query) {
        super(application, query);
    }

    public List<String> textColor() {
        return sendMessage("textColor");
    }
}
