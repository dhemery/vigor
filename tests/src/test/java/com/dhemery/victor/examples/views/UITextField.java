package com.dhemery.victor.examples.views;

import com.dhemery.victor.IosView;

public class UITextField extends UIView {
    public UITextField(IosView view) {
        super(view);
    }

    public void appendText(String text) {
        sendMessage("DFX_appendText:", text);
    }

    public void done() {
        sendMessage("DFX_return");
    }

    public void insertText(String text, int location) {
        sendMessage("DFX_insertText:atLocation:", text, location);
    }

    public void setText(String text) {
        sendMessage("DFX_setText:", text);
    }
}
