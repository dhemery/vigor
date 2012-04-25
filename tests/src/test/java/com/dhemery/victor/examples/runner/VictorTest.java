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
import com.dhemery.victor.frank.IosViewAgent;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.dhemery.victor.examples.extensions.AgentReadyMatcher.ready;
import static org.hamcrest.core.Is.is;

public class VictorTest extends PollableExpressions {
    private static IosApplication application;
    private static FrankAgent frank;
    private static IosDevice device;
    private static PollTimer timer;

    @BeforeClass
    public static void startApplicationInDevice() {
        RequiredProperties configuration = new RequiredProperties("default.properties", "my.properties");
        IosDeviceCapabilities capabilities = new IosDeviceCapabilities(configuration.properties());
        device = CreateIosDevice.withCapabilities(capabilities);
        device.start();
        frank = CreateFrankAgent.fromProperties(configuration.properties());
        timer = timer(configuration);
        waitUntil(frank, timer, is(ready()));
        application = new FrankIosApplication(frank);
    }

    @AfterClass
    public static void stopDevice() {
        device.stop();
    }

    public IosViewAgent viewAgent() { return frank; }
    public IosApplication application() { return application; }
    public IosDevice device() { return device; }

    @Override
    public PollTimer eventually() {
        return timer;
    }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new ApplicationOrientationQuery();
    }

    private static PollTimer timer(RequiredProperties configuration) {
        Integer timeout = configuration.getInteger("polling.timeout");
        Integer pollingInterval = configuration.getInteger("polling.interval");
        return new SystemClockPollTimer(timeout, pollingInterval);
    }
}
