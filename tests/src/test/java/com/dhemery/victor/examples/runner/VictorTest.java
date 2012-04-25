package com.dhemery.victor.examples.runner;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.properties.RequiredProperties;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.device.CreateIosDevice;
import com.dhemery.victor.device.IosDeviceCapabilities;
import com.dhemery.victor.examples.extensions.ApplicationOrientationQuery;
import com.dhemery.victor.frank.CreateFrankAgent;
import com.dhemery.victor.frank.FrankAgent;
import com.dhemery.victor.frank.FrankIosApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Properties;

import static com.dhemery.victor.examples.extensions.FrankAgentReadyMatcher.ready;
import static org.hamcrest.core.Is.is;

public class VictorTest extends PollableExpressions {
    public static IosApplication application;
    public static IosDevice device;
    public static PollTimer timer;

    @BeforeClass
    public static void startApplicationInDevice() {
        Properties properties = new RequiredProperties("default.properties", "my.properties").properties();
        IosDeviceCapabilities capabilities = new IosDeviceCapabilities(properties);
        device = CreateIosDevice.withCapabilities(capabilities);
        device.start();
        timer = createTimer(properties);
        FrankAgent frank = CreateFrankAgent.fromProperties(properties);
        waitUntil(frank, timer, is(ready()));
        application = new FrankIosApplication(frank);
    }

    @AfterClass
    public static void stopDevice() {
        device.stop();
    }

    @Override
    public PollTimer eventually() { return timer; }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new ApplicationOrientationQuery();
    }

    private static PollTimer createTimer(Properties properties) {
        Integer timeout = Integer.parseInt(properties.getProperty("polling.timeout"));
        Integer pollingInterval = Integer.parseInt(properties.getProperty("polling.interval"));
        return new SystemClockPollTimer(timeout, pollingInterval);
    }
}
