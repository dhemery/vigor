package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.VictorTest;
import com.dhemery.victor.examples.pages.MasterPage;
import org.junit.Before;
import org.junit.Test;

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
        assertThat(master.numberOfItems(), is(equalTo(0)));
    }

	@Test
	public void aNewItemAppearsInTheMasterPage() {
        master.addItem();
        assertThat(master.numberOfItems(), is(equalTo(1)));
	}

    // This test fails because it touches the delete button too soon,
    // and therefore never raises the confirmation button.
    // But even it it raised the confirmation button, touching the
    // confirmation button has no effect.
    @Test
    public void aDeletedItemDoesNotAppearInTheMasterPage() {
        master.addItem();
        master.deleteItemAtRow(0);
        assertThat(master.numberOfItems(), is(equalTo(0)));
    }

    @Test
    public void aNewItemHasADetailPage() {
        master.addItem();
        master.visitItemAtRow(0);
    }
}
