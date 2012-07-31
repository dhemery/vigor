package com.dhemery.victor.examples.runner;

import com.dhemery.victor.examples.views.UILabel;
import com.dhemery.victor.examples.views.UISwitch;
import com.dhemery.victor.examples.views.UITextField;
import com.dhemery.victor.examples.views.UIView;

import static com.dhemery.victor.Igor.igor;

public class OnMasterPage extends OnVigorApp {
    protected UITextField prefixField = new UITextField(application(), igor("#prefix"));
    protected UILabel preview = new UILabel(application(), igor("#nextItemPreview"));
    protected UISwitch prefixSwitch = new UISwitch(application(), igor("#prefixEnabled"));
    protected UIView increment = new UIView(application(), igor("[accessibilityLabel=='Increment']"));
}
