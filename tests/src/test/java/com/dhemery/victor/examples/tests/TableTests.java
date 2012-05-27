package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.VigorTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.views.UIViewCountQuery.count;
import static org.hamcrest.Matchers.equalTo;

public class TableTests extends VigorTest {
    Logger log = LoggerFactory.getLogger(getClass());
    private MasterPage master;

    @Before
    public void setUp() {
        master = new MasterPage(application, timer);
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
        assertThat(master.items(), has(count(), equalTo(1)));
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
