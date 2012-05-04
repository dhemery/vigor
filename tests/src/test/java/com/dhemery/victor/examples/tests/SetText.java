package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.IosView;
import com.dhemery.victor.examples.runner.VictorTest;
import org.junit.Test;

import static com.dhemery.victor.examples.extensions.ViewTapAction.tap;
import static com.dhemery.victor.examples.extensions.ViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class SetText extends VictorTest {

    @Test
    public void setText() {
        IosView field1 = application.view(By.igor("#prefix"));
        when(field1, is(visible()), tap());
        field1.sendMessage("DFX_setText:", "Hello Dolly");
        field1.sendMessage("DFX_appendText:", ". Well hello Dolly");
        field1.sendMessage("DFX_insertText:atLocation:", ",", 5);
        field1.sendMessage("DFX_insertText:atLocation:", ",", 24);
        field1.sendMessage("DFX_appendText:", ".");
    }
}
