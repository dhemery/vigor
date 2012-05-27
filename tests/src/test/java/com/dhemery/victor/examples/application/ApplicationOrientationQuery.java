package com.dhemery.victor.examples.application;

import com.dhemery.polling.Query;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplicationOrientation;

public class ApplicationOrientationQuery extends Query<IosApplication, IosApplicationOrientation> {
    @Override
    public String name() {
        return "orientation";
    }

    @Override
    public IosApplicationOrientation query(IosApplication application) {
        return application.orientation();
    }

    public static Query<IosApplication, IosApplicationOrientation> orienation() {
        return new ApplicationOrientationQuery();
    }
}
