package com.dhemery.victor.examples.application;

import com.dhemery.core.NamedQuery;
import com.dhemery.core.Query;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplicationOrientation;

public class ApplicationQueries {
    public static Query<IosApplication, IosApplicationOrientation> orientation() {
        return new NamedQuery<IosApplication, IosApplicationOrientation>("orientation") {
            @Override
            public IosApplicationOrientation query(IosApplication application) {
                return application.orientation();
            }
        };
    }

    public static Query<IosApplication,Boolean> isRunning() {
        return new NamedQuery<IosApplication, Boolean>("is running") {
            @Override
            public Boolean query(IosApplication application) {
                return application.isRunning();
            }
        };
    }
}
