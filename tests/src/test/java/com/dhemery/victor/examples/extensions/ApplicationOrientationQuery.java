package com.dhemery.victor.examples.extensions;

import com.dhemery.polling.Query;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplication.Orientation;

public class ApplicationOrientationQuery extends Query<IosApplication, Orientation> {
    @Override
    public String name() {
        return "orientation";
    }

    @Override
    public Orientation query(IosApplication application) {
        return application.orientation();
    }
}
