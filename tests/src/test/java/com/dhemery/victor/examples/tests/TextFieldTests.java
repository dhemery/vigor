package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.examples.views.UITextField;
import com.dhemery.victor.examples.runner.VigorTest;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UIViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class TextFieldTests extends VigorTest {
    private UITextField prefixField;

    @Before
    public void beginEditing() {
        prefixField = textField(By.igor("#prefix"));
        when(prefixField, is(visible())).tap();
    }

    @Test
    public void setText() {
        prefixField.setText("Hello Dolly");
        prefixField.appendText(". Well hello Dolly");
        prefixField.insertText(",", 5);
        prefixField.insertText(",", 24);
        prefixField.appendText(".");
        prefixField.done();
    }

    public UITextField textField(By query) {
        return new UITextField(application, query);
    }
}
