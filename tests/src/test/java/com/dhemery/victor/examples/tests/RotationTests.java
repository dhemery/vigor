package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.VigorTest;
import org.junit.Test;

import java.io.IOException;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.IosApplicationOrientation.*;
import static org.hamcrest.Matchers.equalTo;

public class RotationTests extends VigorTest {
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

        device.rotateRight();
        assertThat(application, eventually(), has(orientation(), equalTo(LANDSCAPE)));

        device.rotateRight();
        assertThat(application, eventually(), has(orientation(), equalTo(PORTRAIT)));
    }
}
