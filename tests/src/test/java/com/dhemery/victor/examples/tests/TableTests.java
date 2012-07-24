package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.VigorTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.views.UIViewCountQuery.count;
import static org.hamcrest.Matchers.equalTo;

public class TableTests extends VigorTest {
    private MasterPage master;

    @Before
    public void setUp() {
        master = new MasterPage(viewFactory, timer);
    }

    @After
    public void deleteAllItems() {
        master.deleteAllItems();
    }

    @Test
    public void aNewlyLaunchedVigorHasNoItems() {
        assertThat(master.items(), has(count(), equalTo(0)));
    }

    @Test
    public void aNewItemAppearsInTheMasterPage() {
        master.addItem();
        assertThat(master.items(), eventually(), has(count(), equalTo(1)));
    }

    @Test
    public void aDeletedItemDoesNotAppearInTheMasterPage() {
        master.addItem();
        master.deleteItem(0);
        assertThat(master.items(), has(count(), equalTo(0)));
    }

    @Test
    public void aNewItemHasADetailPage() {
        master.addItem();
        master.visitItem(0);
    }
}
