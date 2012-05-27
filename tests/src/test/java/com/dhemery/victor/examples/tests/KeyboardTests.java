package com.dhemery.victor.examples.tests;

import com.dhemery.victor.By;
import com.dhemery.victor.examples.runner.VigorTest;
import com.dhemery.victor.examples.views.UITextField;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UIViewVisibleMatcher.visible;
import static org.hamcrest.Matchers.is;

public class KeyboardTests extends VigorTest {
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

    @Before
    public void beginEditing() {
        UITextField prefixField = new UITextField(application, By.igor("#prefix"));
        when(prefixField, is(visible())).tap();
    }

    @Test
    public void touchKeyboard() {
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
        application.sendMessage("DFX_touchKeyboardAtx:y:", RETURN_KEY, 189);
    }

    @Test
    public void printKeyboard() {
        application.sendMessage("DFX_printKeyboard");
    }
}
