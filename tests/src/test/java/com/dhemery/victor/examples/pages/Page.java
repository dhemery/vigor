package com.dhemery.victor.examples.pages;

import com.dhemery.expressions.Expressive;
import com.dhemery.polling.Poller;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;
import com.dhemery.victor.examples.views.UIView;

public class Page extends Expressive {
    private final IosApplication application;
    private final Poller poller;

    public Page(IosApplication application, Poller poller) {
        this.application = application;
        this.poller = poller;
    }

    public IosApplication application() {
        return application;
    }

    public UIView view(IosViewIdentifier id) {
        return new UIView(application, id);
    }

    @Override
    protected Poller defaultPoller() {
        return poller;
    }
}
