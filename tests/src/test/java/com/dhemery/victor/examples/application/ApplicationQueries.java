package com.dhemery.victor.examples.application;

import com.dhemery.core.Feature;
import com.dhemery.core.NamedFeature;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplicationOrientation;

public class ApplicationQueries {
    public static Feature<IosApplication, IosApplicationOrientation> orientation() {
        return new NamedFeature<IosApplication, IosApplicationOrientation>("orientation") {
            @Override
            public IosApplicationOrientation of(IosApplication application) {
                return application.orientation();
            }
        };
    }

    public static Feature<IosApplication,Boolean> isRunning() {
        return new NamedFeature<IosApplication, Boolean>("is running") {
            @Override
            public Boolean of(IosApplication application) {
                return application.isRunning();
            }
        };
    }
}
