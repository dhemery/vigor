package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.examples.views.UITextField;
import com.dhemery.victor.examples.runner.VigorTest;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.victor.examples.extensions.UIViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class TextFieldTests extends VigorTest {
    private UITextField prefixField;

    @Before
    public void beginEditing() {
        By by = By.igor("#prefix");
        prefixField = textField(by);
        when(prefixField, is(visible())).tap();
    }

    public UITextField textField(By by) {
        return new UITextField(application.view(by));
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
}
