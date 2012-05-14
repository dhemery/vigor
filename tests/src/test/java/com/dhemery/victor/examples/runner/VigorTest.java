package com.dhemery.victor.examples.runner;

import com.dhemery.configuration.Configuration;
import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosApplicationOrientation;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.configuration.CreateIosApplication;
import com.dhemery.victor.configuration.CreateIosDevice;
import com.dhemery.victor.examples.extensions.ApplicationOrientationQuery;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.dhemery.victor.examples.extensions.ApplicationRunningMatcher.running;
import static org.hamcrest.Matchers.is;

public class VigorTest extends PollableExpressions {
    public static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    public static IosApplication application;
    public static IosDevice device;
    public static PollTimer timer;

    @BeforeClass
    public static void startApplicationInDevice() {
        Configuration configuration = new Configuration(VIGOR_PROPERTIES_FILES);
        device = CreateIosDevice.withConfiguration(configuration);
        application = CreateIosApplication.withConfiguration(configuration);
        timer = createTimer(configuration);
        device.start();
        waitUntil(application, timer, is(running()));
    }

    @AfterClass
    public static void stopDevice() {
        if (device != null) device.stop();
    }

    @Override
    public PollTimer eventually() {
        return timer;
    }

    protected Query<IosApplication, IosApplicationOrientation> orientation() {
        return new ApplicationOrientationQuery();
    }

    private static PollTimer createTimer(Configuration configuration) {
        Integer timeout = Integer.parseInt(configuration.option("polling.timeout"));
        Integer pollingInterval = Integer.parseInt(configuration.option("polling.interval"));
        return new SystemClockPollTimer(timeout, pollingInterval);
    }
}
