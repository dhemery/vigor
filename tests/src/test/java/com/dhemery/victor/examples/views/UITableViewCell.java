package com.dhemery.victor.examples.views;

import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;

public class UITableViewCell extends UIView {
    public UITableViewCell(IosApplication application, By query) {
        super(application, query);
    }

    private static final String CONFIRM_DELETION_BUTTON_FOR_CELL = "(%s) UITableViewCellDeleteConfirmationControl";
    private static final String DELETE_BUTTON_FOR_CELL = "(%s) UITableViewCellEditControl";

    private UIView confirmationButton() {
        String selector = String.format(CONFIRM_DELETION_BUTTON_FOR_CELL, query().pattern());
        return new UIView(application(), By.igor(selector));
    }

    public void delete() {
        deleteButton().tap();
        confirmationButton().tap();
    }

    private UIView deleteButton() {
        String selector = String.format(DELETE_BUTTON_FOR_CELL, query().pattern());
        return new UIView(application(),  By.igor(selector));
    }
}
