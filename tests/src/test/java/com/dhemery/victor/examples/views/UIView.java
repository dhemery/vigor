package com.dhemery.victor.examples.views;

import com.dhemery.victor.By;
import com.dhemery.victor.IosViewFactory;

import java.util.List;

public class UIView {
    private final IosViewFactory application;
    private final By query;

    public UIView(IosViewFactory application, By query) {
        this.application = application;
        this.query = query;
    }

    protected IosViewFactory application() {
        return application;
    }

    public boolean isVisible() {
        List<String> results = sendMessage("isHidden");
        if (results.size() != 1) {
            return false;
        }
        return !Boolean.parseBoolean(results.get(0));
    }

    protected By query() {
        return query;
    }

    public List<String> sendMessage(String name, Object... arguments) {
        return application.view(query).sendMessage(name, arguments);
    }

    public void tap() {
        sendMessage("touch");
    }

    @Override
    public String toString() {
        return query.toString();
    }

    public List<String> backgroundColor() {
        return sendMessage("backgroundColor");
    }
}
