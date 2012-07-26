package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnVigorApp;
import org.junit.Test;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.IosApplicationOrientation.LANDSCAPE;
import static com.dhemery.victor.IosApplicationOrientation.PORTRAIT;
import static org.hamcrest.Matchers.equalTo;

public class Rotation extends OnVigorApp {
    @Test
    public void orientationTests() {
        assertThat(application, has(orientation(), equalTo(PORTRAIT)));

        rotateLeft();
        assertThat(application, eventually(), has(orientation(), equalTo(LANDSCAPE)));

        rotateRight();
        assertThat(application, eventually(), has(orientation(), equalTo(PORTRAIT)));

        rotateRight();
        assertThat(application, eventually(), has(orientation(), equalTo(LANDSCAPE)));

        rotateRight();
        assertThat(application, eventually(), has(orientation(), equalTo(PORTRAIT)));

        rotateRight();
        assertThat(application, eventually(), has(orientation(), equalTo(LANDSCAPE)));

        rotateRight();
        assertThat(application, eventually(), has(orientation(), equalTo(PORTRAIT)));
    }
}
