package com.dhemery.victor.examples.runner;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.properties.RequiredProperties;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.device.SimulatedIosDevice;
import com.dhemery.victor.device.Simulator;
import com.dhemery.victor.examples.tests.OrientationQuery;
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
    private static Simulator simulator;

    @BeforeClass
	public static void startApplication() {
        RequiredProperties configuration = new RequiredProperties("default.properties", "my.properties");
        simulator = CreateSimulator.fromProperties(configuration.properties());
        simulator.startWithApplication(configuration.get("victor.application.binary.path"));
        device = new SimulatedIosDevice(simulator);
        frank = CreateFrankAgent.fromProperties(configuration.properties());
        timer = timer(configuration);
        waitUntil(frank, timer, is(ready()));
		application = new FrankIosApplication(frank);
	}

    @AfterClass
	public static void stopApplication() {
        simulator.stop();
	}

    public IosViewAgent viewAgent() { return frank; }
	public IosApplication application() { return application; }
	public IosDevice device() { return device; }

	@Override
    public PollTimer eventually() {
        return timer;
    }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new OrientationQuery();
    }

    private static PollTimer timer(RequiredProperties configuration) {
        Integer timeout = configuration.getInteger("polling.timeout");
        Integer pollingInterval = configuration.getInteger("polling.interval");
        return new SystemClockPollTimer(timeout, pollingInterval);
    }
}
