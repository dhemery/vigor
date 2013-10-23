package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UILabelQueries.textColor;
import static com.dhemery.victor.examples.views.UITextFieldQueries.editing;
import static com.dhemery.victor.examples.views.UITextFieldQueries.text;

public class PrefixFieldTest extends OnMasterPage {
    private static final String RED = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":0}";

    @Test
    public void allowsEditingWhenPrefixFieldIsOn() {
        prefixSwitch.turnOn();
        prefixField.tap();
        assertThat(prefixField, is(editing()));
    }

    @Test
    public void disallowsEditingWhenPrefixFieldIsOff() throws InterruptedException {
        prefixSwitch.turnOff();
        waitUntil(preview, textColor(), is(RED));
        prefixField.tap();
        assertThat(prefixField, is(not(editing())));
    }

    @Test
    public void displaysValidlyEnteredText() {
        assertThat(prefixField, text(), is(""));
        prefixField.tap();

        prefixField.setText("Hello Dolly");
        assertThat(prefixField, text(), is("Hello Dolly"));

        prefixField.appendText(". Well hello Dolly");
        assertThat(prefixField, text(), is("Hello Dolly. Well hello Dolly"));

        prefixField.insertText(",", 5);
        assertThat(prefixField, text(), is("Hello, Dolly. Well hello Dolly"));

        prefixField.insertText(",", 24);
        assertThat(prefixField, text(), is("Hello, Dolly. Well hello, Dolly"));

        prefixField.appendText(".");
        assertThat(prefixField, text(), is("Hello, Dolly. Well hello, Dolly."));

        prefixField.done();
    }
}
