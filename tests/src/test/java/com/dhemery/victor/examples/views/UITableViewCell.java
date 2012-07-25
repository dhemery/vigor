package com.dhemery.victor.examples.views;

import com.dhemery.victor.By;
import com.dhemery.victor.IosViewFactory;

import static com.dhemery.victor.Igor.igor;

public class UITableViewCell extends UIView {
    public UITableViewCell(IosViewFactory application, By query) {
        super(application, query);
    }

    private static final String CONFIRM_DELETION_BUTTON_FOR_CELL = "(%s) UITableViewCellDeleteConfirmationControl";
    private static final String DELETE_BUTTON_FOR_CELL = "(%s) UITableViewCellEditControl";

    public UIView confirmationButton() {
        String selector = String.format(CONFIRM_DELETION_BUTTON_FOR_CELL, query().pattern());
        return new UIView(application(), igor(selector));
    }

    public UIView deleteButton() {
        String selector = String.format(DELETE_BUTTON_FOR_CELL, query().pattern());
        return new UIView(application(),  igor(selector));
    }
}
