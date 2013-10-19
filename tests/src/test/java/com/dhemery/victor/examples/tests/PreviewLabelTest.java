package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UILabelQueries.textColor;
import static com.dhemery.victor.examples.views.UIViewQueries.backgroundColor;
import static org.hamcrest.Matchers.is;

public class PreviewLabelTest extends OnMasterPage {
    private static final String GREEN = "{\"green\":0,\"red\":0,\"alpha\":1,\"blue\":1}";
    private static final String YELLOW = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":1}";
    private static final String BLUE = "{\"green\":1,\"red\":0,\"alpha\":1,\"blue\":0}";
    private static final String RED = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":0}";

    @Test
    public void backgroundColorFollowsPrefixFieldEditMode() {
        prefixField.beginEditing();
        assertThat(preview, backgroundColor(), eventually(), is(YELLOW));

        prefixField.endEditing();
        assertThat(preview, backgroundColor(), eventually(), is(GREEN));

        prefixField.beginEditing();
        assertThat(preview, backgroundColor(), eventually(), is(YELLOW));

        prefixField.endEditing();
        assertThat(preview, backgroundColor(), eventually(), is(GREEN));

        prefixField.beginEditing();
        assertThat(preview, backgroundColor(), eventually(), is(YELLOW));

        prefixField.endEditing();
        assertThat(preview, backgroundColor(), eventually(), is(GREEN));

        prefixField.beginEditing();
        assertThat(preview, backgroundColor(), eventually(), is(YELLOW));

        prefixField.endEditing();
        assertThat(preview, backgroundColor(), eventually(), is(GREEN));
    }

    @Test
    public void textColorFollowsPrefixEnabledSwitch() {
        preview.sendMessage("description");
        prefixSwitch.turnOff();
        assertThat(preview, textColor(), eventually(), is(RED));

        prefixSwitch.turnOn();
        assertThat(preview, textColor(), eventually(), is(BLUE));

        prefixSwitch.turnOff();
        assertThat(preview, textColor(), eventually(), is(RED));

        prefixSwitch.turnOn();
        assertThat(preview, textColor(), eventually(), is(BLUE));

        prefixSwitch.turnOff();
        assertThat(preview, textColor(), eventually(), is(RED));

        prefixSwitch.turnOn();
        assertThat(preview, textColor(), eventually(), is(BLUE));
    }
}
