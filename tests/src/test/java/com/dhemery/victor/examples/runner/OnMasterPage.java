package com.dhemery.victor.examples.runner;

import com.dhemery.victor.examples.views.UILabel;
import com.dhemery.victor.examples.views.UISwitch;
import com.dhemery.victor.examples.views.UITextField;
import com.dhemery.victor.examples.views.UIView;
import org.junit.Before;

import static com.dhemery.victor.Igor.igor;

public class OnMasterPage extends OnVigorApp {
    protected UITextField prefixField;
    protected UILabel preview;
    protected UISwitch prefixSwitch;
    protected UIView increment;

    @Before
    public void setup() {
        increment = new UIView(application(), igor("[accessibilityLabel=='Next Item Number'] > [accessibilityLabel == 'increment']"));
        prefixField = new UITextField(application(), igor("[accessibilityLabel == 'Next Item Prefix']"));
        preview = new UILabel(application(), igor("UILabel[accessibilityLabel == 'Next Item Preview']"));
        prefixSwitch = new UISwitch(application(), igor("[accessibilityLabel == 'Enable Next Item Prefix']"));
    }
}
