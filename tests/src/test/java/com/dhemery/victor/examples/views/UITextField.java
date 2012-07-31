package com.dhemery.victor.examples.views;

import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;

public class UITextField extends UIView {
    public UITextField(IosApplication application, IosViewIdentifier id) {
        super(application, id);
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

    public void beginEditing() {
        tap();
    }

    public void endEditing() {
        done();
    }
}
