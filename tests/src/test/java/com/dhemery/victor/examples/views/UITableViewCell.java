package com.dhemery.victor.examples.views;

import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;

import static com.dhemery.victor.Igor.igor;

public class UITableViewCell extends UIView {
    public UITableViewCell(IosApplication application, IosViewIdentifier id) {
        super(application, id);
    }

    private static final String CONFIRM_DELETION_BUTTON_FOR_CELL = "(%s) UITableViewCellDeleteConfirmationButton";
    private static final String DELETE_BUTTON_FOR_CELL = "(%s) UITableViewCellEditControl";

    public UIView confirmationButton() {
        String selector = String.format(CONFIRM_DELETION_BUTTON_FOR_CELL, id().pattern());
        return new UIView(application(), igor(selector));
    }

    public UIView deleteButton() {
        String selector = String.format(DELETE_BUTTON_FOR_CELL, id().pattern());
        return new UIView(application(),  igor(selector));
    }
}
