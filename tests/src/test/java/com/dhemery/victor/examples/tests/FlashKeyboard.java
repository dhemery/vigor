package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.examples.runner.VictorTest;
import org.junit.Test;

import static com.dhemery.victor.examples.extensions.ViewTapAction.tap;
import static com.dhemery.victor.examples.extensions.ViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class FlashKeyboard extends VictorTest {
    public static final int LETTER_Q = 16;
    public static final int LETTER_W = 48;
    public static final int LETTER_E = 80;
    public static final int LETTER_R = 112;
    public static final int LETTER_T = 144;
    public static final int LETTER_Y = 176;
    public static final int LETTER_U = 208;
    public static final int LETTER_I = 240;
    public static final int LETTER_O = 272;
    public static final int LETTER_P = 304;
    public static final int RETURN_KEY = 272;
    @Test
    public void flashKeyboard() throws InterruptedException {
        By textField1 = By.igor("#prefix");
        when(application.view(textField1), is(visible()), tap());
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_P,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_R,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_E,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_T,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_T,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_Y,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_P,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_O,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_U,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_T,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",LETTER_Y,27);
        application.sendMessage("DFX_touchKeyboardAtx:y:",RETURN_KEY,189);
    }
}


