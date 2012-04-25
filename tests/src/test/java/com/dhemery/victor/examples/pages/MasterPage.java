package com.dhemery.victor.examples.pages;

import com.dhemery.polling.PollTimer;
import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosView;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class MasterPage extends Page {
    private static final By ADD_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Add']");
    private static final By CONFIRM_DELETION_BUTTON = By.igor("UITableViewCellDeleteConfirmationControl[accessibilityLabel == 'Confirm Deletion']");
    private static final String DELETE_BUTTON_TEMPLATE = "UITableView[accessibilityLabel=='Empty list'] > UITableViewCell > UITableViewCellEditControl[accessibilityLabel=='%s']";
    private static final By DELETE_BUTTONS = By.igor("UITableView[accessibilityLabel=='Empty list'] > UITableViewCell > UITableViewCellEditControl[accessibilityLabel BEGINSWITH 'Delete']");
    private static final By DONE_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Done']");
    private static final By EDIT_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Edit']");
    private static final String ITEM_TEMPLATE = "UITableView[accessibilityLabel=='Empty list'] > UITableViewCell[accessibilityLabel=='%s']";
    private static final By ITEMS = By.igor("UITableView[accessibilityLabel=='Empty list'] > UITableViewCell");

    public MasterPage(IosApplication application, PollTimer timer) {
        super(application, timer);
    }

    private IosView addButton() {
        return view(ADD_BUTTON);
    }

    public void addItem() {
        addButton().sendMessage("touch");
    }

    private IosView confirmDeletionButton() {
        return view(CONFIRM_DELETION_BUTTON);
    }

    private IosView deleteButtonAtRow(Integer i) {
        List<String> labels = deleteButtons().sendMessage("accessibilityLabel");
        return deleteButtonNamed(labels.get(i));
    }

    private IosView deleteButtonNamed(String name) {
        By selector = By.igor(String.format(DELETE_BUTTON_TEMPLATE, name));
        return view(selector);
    }

    private IosView deleteButtons() {
        return view(DELETE_BUTTONS);
    }

    public void deleteItemAtRow(Integer i) {
        editButton().sendMessage("touch");
        when(deleteButtonAtRow(i), is(visible()), touch());
        when(confirmDeletionButton(), is(visible()), touch());
        doneButton().sendMessage("touch");
    }

    private IosView doneButton() {
        return view(DONE_BUTTON);
    }

    private IosView editButton() {
        return view(EDIT_BUTTON);
    }

    private IosView itemAtRow(Integer i) {
        List<String> itemLabels = items().sendMessage("accessibilityLabel");
        return itemNamed(itemLabels.get(i));
    }

    private IosView itemNamed(String name) {
        By by = By.igor(String.format(ITEM_TEMPLATE, name));
        return view(by);
    }

    private IosView items() {
        return view(ITEMS);
    }

    public Integer numberOfItems() {
        return items().sendMessage("tag").size();
    }

    public void visitItemAtRow(Integer i) {
        itemAtRow(i).sendMessage("touch");
    }
}
