package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.examples.runner.VictorTest;
import org.junit.Test;

import static com.dhemery.victor.examples.extensions.ViewTapAction.tap;
import static com.dhemery.victor.examples.extensions.ViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class FlashKeyboard extends VictorTest {
    public static final String LETTER_Q = "16";
    public static final String LETTER_W = "48";
    public static final String LETTER_E = "80";
    public static final String LETTER_R = "112";
    public static final String LETTER_T = "144";
    public static final String LETTER_Y = "176";
    public static final String LETTER_U = "208";
    public static final String LETTER_I = "240";
    public static final String LETTER_O = "272";
    public static final String LETTER_P = "304";
    public static final String RETURN_KEY = "272";
    @Test
    public void flashKeyboard() throws InterruptedException {
        By textField1 = By.igor("[accessibilityHint == 'field1']");
        when(application.view(textField1), is(visible()), tap());
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_P,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_R,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_E,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_T,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_T,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_Y,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_P,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_O,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_U,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_T,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",LETTER_Y,"27");
        application.sendMessage("DFX_touchKeyboardAtPointX:y:",RETURN_KEY,"189");
        By textField2 = By.igor("[accessibilityHint == 'field2']");
        when(application.view(textField2), is(visible()), tap());

//        application.sendMessage("DFX_dumpKeyboard");
    }
}


