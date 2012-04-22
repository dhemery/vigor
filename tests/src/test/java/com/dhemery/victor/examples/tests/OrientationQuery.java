package com.dhemery.victor.examples.tests;

import com.dhemery.polling.Query;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplication.Orientation;

public class OrientationQuery extends Query<IosApplication, Orientation> {
    @Override
    public String name() {
        return "orientation";
    }

    @Override
    public IosApplication.Orientation query(IosApplication application) {
        return application.orientation();
    }
}
