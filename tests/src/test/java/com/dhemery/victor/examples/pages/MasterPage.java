package com.dhemery.victor.examples.pages;

import com.dhemery.polling.Action;
import com.dhemery.polling.PollTimer;
import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosView;

import java.util.List;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.extensions.TableCellConfirmDeletionAction.confirmDeletionOf;
import static com.dhemery.victor.examples.extensions.ViewAnimatingMatcher.animating;
import static com.dhemery.victor.examples.extensions.ViewListEmptyMatcher.empty;
import static com.dhemery.victor.examples.extensions.ViewListSizeQuery.size;
import static com.dhemery.victor.examples.extensions.ViewTapAction.tap;
import static com.dhemery.victor.examples.extensions.ViewVisibleMatcher.visible;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class MasterPage extends Page {
    private static final By ADD_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Add']");
    private static final By DONE_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Done']");
    private static final By EDIT_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Edit']");

    private static final By CELL = By.igor("UITableViewCell*");
    private static final String DELETE_BUTTON_FOR_CELL = "(%s) UITableViewCellEditControl";
    private static final By CELL_LABEL = By.igor(CELL.selector + "˚ UILabel");
    private static final String CELL_WITH_LABEL = "(" + CELL_LABEL.selector + "[accessibilityLabel=='%s'])";

    public MasterPage(IosApplication application, PollTimer timer) {
        super(application, timer);
    }

    private IosView addButton() {
        return view(ADD_BUTTON);
    }

    public void addCell() {
        addButton().sendMessage("tap");
    }

    public void deleteAllCells() {
        while (the(items(), is(not(empty())))) {
            deleteItem(0);
        }
    }

    public Action<IosView> delete() {
        return new Action<IosView>() {
            @Override
            public void executeOn(IosView item) {
                waitUntil(item, is(not(animating())));
                tap(deleteButton(item));
                confirmDeletionOf(item);
                waitUntil(item, is(not(visible())));
            }
        };
    }

    public void delete(IosView item) {
        delete().executeOn(item);
    }

    public void deleteItem(Integer i) {
        tap(editButton());
        delete(item(i));
        tap(doneButton());
    }

    private IosView deleteButton(IosView cell) {
        String selector = String.format(DELETE_BUTTON_FOR_CELL, cell.query().selector);
        return view(By.igor(selector));
    }

    public IosView doneButton() {
        return view(DONE_BUTTON);
    }

    public IosView editButton() {
        return view(EDIT_BUTTON);
    }
    public IosView item(Integer i) {
        return itemWithLabel(itemLabel(i));
    }

    private String itemLabel(Integer i) {
        waitUntil(items(), has(size(), greaterThan(i)));
        return itemLabels().get(i);
    }

    private List<String> itemLabels() {
        return view(CELL_LABEL).sendMessage("accessibilityLabel");
    }

    private IosView itemWithLabel(String label) {
        return view(By.igor(String.format(CELL_WITH_LABEL, label)));
    }

    public IosView items() {
        return view(CELL);
    }

    public void visitCell(Integer i) {
        item(i).sendMessage("tap");
    }
}
