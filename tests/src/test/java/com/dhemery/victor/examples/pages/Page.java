package com.dhemery.victor.examples.pages;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosView;
import com.dhemery.victor.examples.views.UIView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page extends PollableExpressions {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final IosApplication application;
    private final PollTimer timer;

    public Page(IosApplication application, PollTimer timer) {
        this.application = application;
        this.timer = timer;
    }

    @Override
    public PollTimer eventually() {
        return timer;
    }

    private IosView iosView(By query) {
        return application.view(query);
    }

    public UIView view(By query) {
        return new UIView(iosView(query));
    }
}
