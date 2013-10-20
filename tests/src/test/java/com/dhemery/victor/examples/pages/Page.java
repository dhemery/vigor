package com.dhemery.victor.examples.pages;

import com.dhemery.expressing.AssistedExpressive;
import com.dhemery.expressing.PollingAssistant;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;
import com.dhemery.victor.examples.views.UIView;

public class Page extends AssistedExpressive {
    private final IosApplication application;

    public Page(IosApplication application, PollingAssistant assistant) {
        super(assistant);
        this.application = application;
    }

    public IosApplication application() {
        return application;
    }

    public UIView view(IosViewIdentifier id) {
        return new UIView(application, id);
    }
}
