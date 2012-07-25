package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.examples.runner.VigorTest;
import com.dhemery.victor.examples.views.UITextField;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.victor.Igor.igor;
import static com.dhemery.victor.examples.views.UIViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class TextFieldTests extends VigorTest {
    private UITextField prefixField;

    @Before
    public void beginEditing() {
        prefixField = textField(igor("#prefix"));
    }

    @Test
    public void setText() {
        demo(12);
        when(prefixField, is(visible())).tap();
        demo(10);
        prefixField.setText("Hello Dolly");
        demo(10);
        prefixField.appendText(". Well hello Dolly");
        demo(4);
        prefixField.insertText(",", 5);
        demo(2);
        prefixField.insertText(",", 24);
        demo(2);
        prefixField.appendText(".");
        demo(2);
        prefixField.done();
        demo(10);
    }

    public UITextField textField(By query) {
        return new UITextField(viewFactory, query);
    }
}
