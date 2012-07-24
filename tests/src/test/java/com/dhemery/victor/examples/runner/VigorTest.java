package com.dhemery.victor.examples.runner;

import com.dhemery.configuration.Configuration;
import com.dhemery.configuration.LoadProperties;
import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.victor.*;
import com.dhemery.victor.examples.application.ApplicationOrientationQuery;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.dhemery.victor.examples.application.ApplicationRunningMatcher.running;
import static org.hamcrest.Matchers.is;

public class VigorTest extends PollableExpressions {
    public static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    private static final Configuration configuration = new Configuration();
    public static IosApplication application;
    public static IosDevice device;
    public static IosViewFactory viewFactory;
    public static PollTimer timer;

    @BeforeClass
    public static void startApplicationInDevice() {
        LoadProperties.fromFiles(VIGOR_PROPERTIES_FILES).into(configuration);
        Victor victor = new Victor(configuration);
        victor.events().subscribe(new VigorCommandLogger());
        victor.events().subscribe(new VigorFrankLogger());
        victor.events().subscribe(new VigorHttpLogger());
        viewFactory = victor.viewFactory();
        device = victor.device();
        application = victor.application();
        timer = createTimer(configuration);
        device.start();
    }

    @Before
    public void waitForApplication() {
        pollPublisher().subscribe(new VigorPollLogger());
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
