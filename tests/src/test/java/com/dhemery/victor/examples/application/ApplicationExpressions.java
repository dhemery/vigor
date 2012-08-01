package com.dhemery.victor.examples.application;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplicationOrientation;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

public class ApplicationExpressions {
    public static Query<IosApplication, IosApplicationOrientation> orientation() {
        return new NamedQuery<IosApplication, IosApplicationOrientation>("orientation") {
            @Override
            public IosApplicationOrientation query(IosApplication application) {
                return application.orientation();
            }
        };
    }

    public static Matcher<IosApplication> running() {
        return new CustomTypeSafeMatcher<IosApplication>("running") {
            @Override
            protected boolean matchesSafely(IosApplication application) {
                return application.isRunning();
            }
        };
    }
}
