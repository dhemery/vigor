package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UITextFieldEditingMatcher.editing;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class PrefixFieldAllowEditing extends OnMasterPage {
    @Test
    public void editingIsAllowedWhenPrefixIsEnabled() {
        enablePrefix();
        demo(8);

        beginEditing();
        demo(5);
        assertThat(prefix, is(editing()));
    }

    @Test
    public void editingIsDisallowedWhenPrefixIsEnabled() {
        disablePrefix();
        demo(8);

        beginEditing();
        demo(5);
        assertThat(prefix, is(not(editing())));
    }
}
