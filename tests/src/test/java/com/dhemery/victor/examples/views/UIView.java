package com.dhemery.victor.examples.views;

import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;

import java.util.List;

public class UIView {
    private final IosApplication application;
    private final IosViewIdentifier id;

    public UIView(IosApplication application, IosViewIdentifier id) {
        this.application = application;
        this.id = id;
    }

    protected IosApplication application() {
        return application;
    }

    public boolean isVisible() {
        List<String> results = sendMessage("isHidden");
        if (results.size() != 1) {
            return false;
        }
        return !Boolean.parseBoolean(results.get(0));
    }

    protected IosViewIdentifier id() {
        return id;
    }

    public List<String> sendMessage(String name, Object... arguments) {
        return application.view(id).sendMessage(name, arguments);
    }

    public void tap() {
        sendMessage("touch");
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public List<String> backgroundColor() {
        return sendMessage("backgroundColor");
    }
}
