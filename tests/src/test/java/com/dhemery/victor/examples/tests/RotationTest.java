package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnVigorApp;
import org.junit.Test;

import static com.dhemery.expressions.Has.has;
import static com.dhemery.victor.IosApplicationOrientation.LANDSCAPE;
import static com.dhemery.victor.IosApplicationOrientation.PORTRAIT;
import static com.dhemery.victor.examples.application.ApplicationExpressions.orientation;
import static org.hamcrest.Matchers.equalTo;

public class RotationTest extends OnVigorApp {
    @Test
    public void orientationTests() {
        assertThat(application(), has(orientation(), equalTo(PORTRAIT)));

        device().rotateLeft();
        assertThat(application(), eventually(), has(orientation(), equalTo(LANDSCAPE)));

        device().rotateRight();
        assertThat(application(), eventually(), has(orientation(), equalTo(PORTRAIT)));

        device().rotateRight();
        assertThat(application(), eventually(), has(orientation(), equalTo(LANDSCAPE)));

        device().rotateRight();
        assertThat(application(), eventually(), has(orientation(), equalTo(PORTRAIT)));

        device().rotateRight();
        assertThat(application(), eventually(), has(orientation(), equalTo(LANDSCAPE)));

        device().rotateRight();
        assertThat(application(), eventually(), has(orientation(), equalTo(PORTRAIT)));
    }
}
