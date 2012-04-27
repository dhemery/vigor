package com.dhemery.victor.examples.tests;

import com.dhemery.victor.IosView;
import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.VictorTest;
import com.dhemery.victor.frank.FrankIosView;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.extensions.ViewListIsEmptyMatcher.empty;
import static com.dhemery.victor.examples.extensions.ViewListSizeQuery.size;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApplicationTests extends VictorTest {
    private MasterPage master;

    @Before
    public void setUp() {
        master = new MasterPage(application, timer);
    }

    @Test
    public void aNewlyLaunchedVigorHasNoItems() {
        assertThat(master.items(), has(size(), equalTo(0)));
    }

    @Test
    public void aNewItemAppearsInTheMasterPage() {
        master.addItem();
        assertThat(master.items(), has(size(), equalTo(1)));
    }

    // This test fails because it can't distinguish between two
    // items created at the same time (they have the same label).
    // It therefore never raises the confirmation button.
    // But even it it raised the confirmation button, touching the
    // confirmation button has no effect.
    @Test
    public void aDeletedItemDoesNotAppearInTheMasterPage() {
        master.addItem();
        master.deleteItemAtRow(0);
        assertThat(master.items(), is(empty()));
    }

    @Test
    public void aNewItemHasADetailPage() {
        master.addItem();
        master.visitItemAtRow(0);
    }
}
