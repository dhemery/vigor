package com.dhemery.victor.examples.views;

import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;

import java.util.List;

public class UILabel extends UIView {
    public UILabel(IosApplication application, IosViewIdentifier id) {
        super(application, id);
    }

    public List<String> textColor() {
        return sendMessage("textColor");
    }
}
