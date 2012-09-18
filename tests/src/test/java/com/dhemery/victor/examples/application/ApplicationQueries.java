package com.dhemery.victor.examples.application;

import com.dhemery.core.Feature;
import com.dhemery.core.NamedFeature;
import com.dhemery.victor.Igor;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplicationOrientation;
import com.dhemery.victor.IosView;

public class ApplicationQueries {
    public static Feature<IosApplication,Boolean> animating() {
        return new NamedFeature<IosApplication, Boolean>("animating") {
            @Override
            public Boolean of(IosApplication application) {
                IosView animating = application.view(Igor.igor("[animating == TRUE]"));
                return !animating.sendMessage("description").isEmpty();
            }
        };
    }
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
