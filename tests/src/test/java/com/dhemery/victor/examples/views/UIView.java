package com.dhemery.victor.examples.views;

import com.dhemery.victor.IosView;

import java.util.List;

public class UIView {
    private final IosView view;

    public UIView(IosView view) {
        this.view = view;
    }

    public boolean isVisible() {
        List<String> results = sendMessage("isHidden");
        if (results.size() != 1) {
            return false;
        }
        if (Boolean.parseBoolean(results.get(0))) {
            return false;
        }
        return true;
    }

    public List<String> sendMessage(String name, Object... arguments) {
        return view.sendMessage(name, arguments);
    }

    public void tap() {
        sendMessage("tap");
    }
}
