package com.dhemery.victor.examples.runner;

import com.dhemery.polling.PollTimeoutException;
import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.properties.RequiredProperties;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.examples.tests.OrientationQuery;
import com.dhemery.victor.simulator.Simulator;
import com.dhemery.victor.view.IosViewAgent;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;

public class VictorTest extends PollableExpressions {
	private static IosApplication application;
	private static IosDevice phone;
	private static Simulator simulator;
	private static PollTimer timer;
    private static IosViewAgent agent;

	@BeforeClass
	public static void launchApp() throws IOException, PollTimeoutException {
		RequiredProperties configuration = new RequiredProperties("default.properties", "./my.properties");
		Launcher launcher = new Launcher(configuration);
		launcher.launch();
		phone = launcher.device();
		application = launcher.application();
		simulator = launcher.simulator();
		timer = launcher.timer();
        agent = launcher.agent();
	}

	@AfterClass
	public static void shutDownSimulator() throws IOException, InterruptedException {
        if(simulator != null) simulator.shutDown();
	}

	public IosApplication application() { return application; }
	public IosDevice device() { return phone; }
	
	@Override
    public PollTimer eventually() {
		return timer;
	}

    public IosViewAgent agent() {
        return agent;
    }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new OrientationQuery();
    }
}
