package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.expressions.Has.has;
import static com.dhemery.victor.examples.views.UITextFieldExpressions.editing;
import static com.dhemery.victor.examples.views.UITextFieldExpressions.text;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class PrefixFieldTest extends OnMasterPage {
    @Test
    public void allowsEditingWhenPrefixFieldIsOn() {
        prefixSwitch.turnOn();
        prefixField.tap();
        assertThat(prefixField, is(editing()));
    }

    @Test
    public void disallowsEditingWhenPrefixFieldIsOff() {
        prefixSwitch.turnOff();
        prefixField.tap();
        assertThat(prefixField, eventually(), is(not(editing())));
    }

    @Test
    public void displaysValidlyEnteredText() {
        assertThat(prefixField, has(text(), equalTo("")));

        prefixField.tap();

        prefixField.setText("Hello Dolly");
        assertThat(prefixField, has(text(), equalTo("Hello Dolly")));

        prefixField.appendText(". Well hello Dolly");
        assertThat(prefixField, has(text(), equalTo("Hello Dolly. Well hello Dolly")));

        prefixField.insertText(",", 5);
        assertThat(prefixField, has(text(), equalTo("Hello, Dolly. Well hello Dolly")));

        prefixField.insertText(",", 24);
        assertThat(prefixField, has(text(), equalTo("Hello, Dolly. Well hello, Dolly")));

        prefixField.appendText(".");
        assertThat(prefixField, has(text(), equalTo("Hello, Dolly. Well hello, Dolly.")));

        prefixField.done();
    }
}
