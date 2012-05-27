package com.dhemery.victor.examples.pages;

import com.dhemery.polling.Action;
import com.dhemery.polling.PollTimer;
import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.examples.views.UIView;

import java.util.List;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.extensions.UIViewAnimatingMatcher.animating;
import static com.dhemery.victor.examples.extensions.UIViewCountQuery.count;
import static com.dhemery.victor.examples.extensions.UIViewVisibleMatcher.visible;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class MasterPage extends Page {
    private static final By ADD_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Add']");
    private static final By DONE_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Done']");
    private static final By EDIT_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Edit']");

    private static final String CONFIRM_DELETION_BUTTON_FOR_CELL = "(%s) UITableViewCellDeleteConfirmationControl";

    private static final By CELL = By.igor("UITableViewCell*");
    private static final String DELETE_BUTTON_FOR_CELL = "(%s) UITableViewCellEditControl";
    private static final By CELL_LABEL = By.igor(CELL.pattern() + " UILabel");
    private static final String CELL_WITH_LABEL = "(" + CELL_LABEL.pattern() + "[accessibilityLabel=='%s'])";

    public MasterPage(IosApplication application, PollTimer timer) {
        super(application, timer);
    }

    private UIView addButton() {
        return view(ADD_BUTTON);
    }

    public void addCell() {
        addButton().sendMessage("tap");
    }

    public void deleteAllCells() {
        while (the(items(), has(count(), greaterThan(0)))) {
            deleteItem(0);
        }
    }

    public Action<UIView> delete() {
        return new Action<UIView>() {
            @Override
            public void executeOn(UIView item) {
                waitUntil(item, is(not(animating())));
                deleteButton(item).tap();
                confirmDeletionButton(item).tap();
                waitUntil(item, is(not(visible())));
            }
        };
    }

    public void delete(UIView item) {
        delete().executeOn(item);
    }

    public void deleteItem(Integer i) {
        editButton().tap();
        delete(item(i));
        doneButton().tap();
    }

    private UIView confirmDeletionButton(UIView cell) {
        String selector = String.format(CONFIRM_DELETION_BUTTON_FOR_CELL, cell.query().pattern());
        return view(By.igor(selector));
    }

    private UIView deleteButton(UIView cell) {
        String selector = String.format(DELETE_BUTTON_FOR_CELL, cell.query().pattern());
        return view(By.igor(selector));
    }

    public UIView doneButton() {
        return view(DONE_BUTTON);
    }

    public UIView editButton() {
        return view(EDIT_BUTTON);
    }
    public UIView item(Integer i) {
        return itemWithLabel(itemLabel(i));
    }

    private String itemLabel(Integer i) {
        waitUntil(items(), has(count(), greaterThan(i)));
        return itemLabels().get(i);
    }

    private List<String> itemLabels() {
        return view(CELL_LABEL).sendMessage("accessibilityLabel");
    }

    private UIView itemWithLabel(String label) {
        return view(By.igor(String.format(CELL_WITH_LABEL, label)));
    }

    public UIView items() {
        return view(CELL);
    }

    public void visitCell(Integer i) {
        item(i).sendMessage("tap");
    }
}
