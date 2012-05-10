package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.IosView;
import com.dhemery.victor.examples.runner.VigorTest;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.victor.examples.extensions.ViewTapAction.tap;
import static com.dhemery.victor.examples.extensions.ViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class TextFieldTests extends VigorTest {
    private IosView prefixField;

    @Before
    public void beginEditing() {
        prefixField = application.view(By.igor("#prefix"));
        when(prefixField, is(visible()), tap());
    }

    @Test
    public void setText() {
        prefixField.sendMessage("DFX_setText:", "Hello Dolly");
        prefixField.sendMessage("DFX_appendText:", ". Well hello Dolly");
        prefixField.sendMessage("DFX_insertText:atLocation:", ",", 5);
        prefixField.sendMessage("DFX_insertText:atLocation:", ",", 24);
        prefixField.sendMessage("DFX_appendText:", ".");
        prefixField.sendMessage("DFX_return");
    }
}
