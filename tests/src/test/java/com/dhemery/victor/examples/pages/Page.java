package com.dhemery.victor.examples.pages;

import com.dhemery.polling.Expressive;
import com.dhemery.polling.Poller;
import com.dhemery.polling.Ticker;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;
import com.dhemery.victor.examples.views.UIView;

public class Page extends Expressive {
    private final IosApplication application;

    public Page(IosApplication application, Poller poller, Ticker ticker) {
        super(poller, ticker);
        this.application = application;
    }

    public IosApplication application() {
        return application;
    }

    public UIView view(IosViewIdentifier id) {
        return new UIView(application, id);
    }
}
