package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.hamcrest.core.Is;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UITextFieldQueries.isEditing;
import static com.dhemery.victor.examples.views.UITextFieldQueries.text;
import static org.hamcrest.core.Is.is;

public class PrefixFieldTest extends OnMasterPage {
    @Test
    public void allowsEditingWhenPrefixFieldIsOn() {
        prefixSwitch.turnOn();
        prefixField.tap();
        assertThat(prefixField, isEditing());
    }

    @Test
    public void disallowsEditingWhenPrefixFieldIsOff() {
        prefixSwitch.turnOff();
        prefixField.tap();
        assertThat(prefixField, isEditing(), eventually(), is(false));
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
