package com.dhemery.victor.examples.pages;

import com.dhemery.expressions.Expressive;
import com.dhemery.polling.Poll;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;
import com.dhemery.victor.examples.views.UIView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page extends Expressive {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final IosApplication application;
    private final Poll poll;

    public Page(IosApplication application, Poll poll) {
        this.application = application;
        this.poll = poll;
    }

    public IosApplication application() {
        return application;
    }

    public UIView view(IosViewIdentifier id) {
        return new UIView(application, id);
    }

    @Override
    public Poll eventually() {
        return poll;
    }
}
