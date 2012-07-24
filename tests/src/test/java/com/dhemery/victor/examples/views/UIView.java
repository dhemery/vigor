package com.dhemery.victor.examples.views;

import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;

import java.util.List;

public class UIView {
    private final IosApplication application;
    private final By query;

    public UIView(IosApplication application, By query) {
        this.application = application;
        this.query = query;
    }

    protected IosApplication application() {
        return application;
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

    protected By query() {
        return query;
    }

    public List<String> sendMessage(String name, Object... arguments) {
        return application.view(query).sendMessage(name, arguments);
    }

    public void tap() {
        sendMessage("tap");
    }
}