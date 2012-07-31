package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.expressions.Has.has;
import static com.dhemery.victor.examples.views.UILabelExpressions.textColor;
import static com.dhemery.victor.examples.views.UIViewExpressions.backgroundColor;
import static org.hamcrest.Matchers.equalTo;

public class PreviewLabelTest extends OnMasterPage {
    private static final String GREEN = "{\"green\":0,\"red\":0,\"alpha\":1,\"blue\":1}";
    private static final String YELLOW = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":1}";
    private static final String BLUE = "{\"green\":1,\"red\":0,\"alpha\":1,\"blue\":0}";
    private static final String RED = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":0}";

    @Test
    public void backgroundColorFollowsPrefixFieldEditMode() {
        prefixField.beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        prefixField.endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));

        prefixField.beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        prefixField.endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));

        prefixField.beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        prefixField.endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));

        prefixField.beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        prefixField.endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));
    }

    @Test
    public void textColorFollowsPrefixEnabledSwitch() {
        prefixSwitch.turnOff();
        assertThat(preview, eventually(), has(textColor(), equalTo(RED)));

        prefixSwitch.turnOn();
        assertThat(preview, eventually(), has(textColor(), equalTo(BLUE)));

        prefixSwitch.turnOff();
        assertThat(preview, eventually(), has(textColor(), equalTo(RED)));

        prefixSwitch.turnOn();
        assertThat(preview, eventually(), has(textColor(), equalTo(BLUE)));

        prefixSwitch.turnOff();
        assertThat(preview, eventually(), has(textColor(), equalTo(RED)));

        prefixSwitch.turnOn();
        assertThat(preview, eventually(), has(textColor(), equalTo(BLUE)));
    }
}
