package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.views.UIViewBackgroundColorQuery.backgroundColor;
import static org.hamcrest.Matchers.equalTo;

public class PreviewBackgroundColor extends OnMasterPage {
    private static final String BLUE = "{\"green\":1,\"red\":0,\"alpha\":1,\"blue\":0}";
    private static final String GREEN = "{\"green\":0,\"red\":0,\"alpha\":1,\"blue\":1}";
    private static final String RED = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":0}";
    private static final String YELLOW = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":1}";

    @Test
    public void backgroundColorFollowsPrefixFieldEditMode() {
        demo(6);

        beginEditing();
        demo(5);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        endEditing();
        demo(3);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));

        beginEditing();
        demo(1);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        endEditing();
        demo(1);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));

        beginEditing();
        demo(1);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        endEditing();
        demo(1);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));

        beginEditing();
        demo(1);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));

        endEditing();
        demo(1);
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));
    }
}
