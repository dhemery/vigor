package com.dhemery.victor.examples.pages;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.victor.By;
import com.dhemery.victor.IosViewFactory;
import com.dhemery.victor.examples.views.UIView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page extends PollableExpressions {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final IosViewFactory application;
    private final PollTimer timer;

    public Page(IosViewFactory application, PollTimer timer) {
        this.application = application;
        this.timer = timer;
    }

    public IosViewFactory application() {
        return application;
    }

    @Override
    public PollTimer eventually() {
        return timer;
    }

    public UIView view(By query) {
        return new UIView(application, query);
    }
}
