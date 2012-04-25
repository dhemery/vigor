package com.dhemery.victor.examples.tests;

import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.examples.runner.VictorTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.IosApplication.Orientation.LANDSCAPE;
import static com.dhemery.victor.IosApplication.Orientation.PORTRAIT;
import static org.hamcrest.Matchers.equalTo;

public class RotationTests extends VictorTest {
	@Test
	public void orientationTests() throws InterruptedException, IOException {
		assertThat(application, has(orientation(), equalTo(PORTRAIT)));

		waitUntil(application, has(orientation(), equalTo(PORTRAIT)));

		device.rotateLeft();
		assertThat(application, eventually(), has(orientation(), equalTo(LANDSCAPE)));

		device.rotateRight();
		assertThat(application, eventually(), has(orientation(), equalTo(PORTRAIT)));

		device.rotateRight();
		assertThat(application, eventually(), has(orientation(), equalTo(LANDSCAPE)));

		device.rotateRight();
		assertThat(application, eventually(), has(orientation(), equalTo(PORTRAIT)));
	}

}
