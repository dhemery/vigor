package com.dhemery.victor.examples.runner;

import com.dhemery.polling.MatcherPoll;
import com.dhemery.polling.PollTimer;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.properties.RequiredProperties;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.agent.FrankAgent;
import com.dhemery.victor.application.AgentBackedIosApplication;
import com.dhemery.victor.simulator.SimulatedIosDevice;
import com.dhemery.victor.simulator.Simulator;
import com.dhemery.victor.simulator.local.LocalSimulator;
import com.dhemery.victor.simulator.remote.RemoteSimulator;
import com.dhemery.victor.view.IosViewAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.dhemery.victor.examples.extensions.FrankReadyMatcher.ready;
import static org.hamcrest.core.Is.is;

public class Launcher {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final FrankAgent frank;
	private Simulator simulator;
	private final RequiredProperties configuration;
	private final String simulatorHost;

	public Launcher(RequiredProperties configuration) {
		this.configuration = configuration;
		simulatorHost = configuration.get("simulator.host");
		frank = frankClient();
	}

	public IosApplication application() {
		return new AgentBackedIosApplication(frank);
	}

	public FrankAgent frankClient() {
		String frankServerUrl = urlForSimulatorHostPort(configuration.get("frank.server.port"));
		return new FrankAgent(frankServerUrl);
	}

	public void launch() {
		if(simulatorHost.equals("localhost")) {
			simulator = launchLocalSimulator();
		} else {
			simulator = launchRemoteSimulator();
		}
		Boolean launchNew = Boolean.parseBoolean(configuration.get("simulator.launch.new"));
		if(launchNew) {
			String applicationPath = configuration.get("application.path");
            String sdkVersion = configuration.get("simulator.sdk.version");
			log.debug("Launching simulator");
			simulator.launch(applicationPath, sdkVersion, false);
		}
		new MatcherPoll<FrankAgent>(frank, timer(), is(ready())).run();
	}

    private Simulator launchLocalSimulator() {
		return new LocalSimulator();
	}

	private Simulator launchRemoteSimulator() {
		String simulatorUrl = urlForSimulatorHostPort(configuration.get("simulator.server.port"));
		log.debug("Launching simulator on remote server {}", simulatorUrl);
		return new RemoteSimulator(simulatorUrl);
	}

	public IosDevice device() {
		return new SimulatedIosDevice(simulator);
	}

	public PollTimer timer() {
		Integer timeout = configuration.getInteger("polling.timeout");
		Integer pollingInterval = configuration.getInteger("polling.interval");
		return new SystemClockPollTimer(timeout, pollingInterval);
	}

	public String urlForSimulatorHostPort(String simulatorServerPort) {
		return String.format("http://%s:%s", simulatorHost, simulatorServerPort);
	}

	public Simulator simulator() {
		return simulator;
	}

    public IosViewAgent agent() {
        return frank;
    }
}
