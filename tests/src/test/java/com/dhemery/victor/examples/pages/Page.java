package com.dhemery.victor.examples.pages;

import com.dhemery.polling.Action;
import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosView;
import com.dhemery.victor.examples.extensions.TouchViewAction;
import com.dhemery.victor.examples.extensions.VisibleViewMatcher;
import org.hamcrest.Matcher;

public class Page extends PollableExpressions {
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

    public IosView view(By query) {
        return application.view(query);
    }

    public Action<? super IosView> touch() {
        return new TouchViewAction();
    }

    public Matcher<? super IosView> visible() {
        return new VisibleViewMatcher();
    }
}
