package com.dhemery.victor.examples.tests;

import com.dhemery.properties.ReadProperties;
import com.dhemery.victor.device.IosDeviceConfigurationOptions;
import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.VigorTest;
import com.dhemery.victor.xcode.Xcode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.extensions.ViewListEmptyMatcher.empty;
import static com.dhemery.victor.examples.extensions.ViewListSizeQuery.size;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApplicationTests extends VigorTest {
    private MasterPage master;

    @Before
    public void setUp() {
        master = new MasterPage(application, timer);
    }

    @After
    public void removeAllItems() {
        master.deleteAllCells();
    }

    public String getSdkVersion() {
        Map<String,String> properties = ReadProperties.fromFiles(VIGOR_PROPERTIES_FILES).asMap();
        String sdkRoot = properties.get(IosDeviceConfigurationOptions.SDK_ROOT);
        if (sdkRoot == null) sdkRoot = new Xcode().newestSdkRoot();
        System.out.println("SDK root: " + sdkRoot);
        String filename = new File(sdkRoot).getName();
        System.out.println("SDK file: " + filename);
        Scanner sdkNameScanner = new Scanner(filename);
        return sdkNameScanner.findInLine("\\d\\.\\d");
    }

    @Test
    public void stuffs() {
        System.out.println("SDK version: " + getSdkVersion());
    }

    @Test
    public void aNewlyLaunchedVigorHasNoItems() {
        assertThat(master.items(), has(size(), equalTo(0)));
    }

    @Test
    public void aNewItemAppearsInTheMasterPage() {
        master.addCell();
        assertThat(master.items(), has(size(), equalTo(1)));
    }

    @Test
    public void aDeletedItemDoesNotAppearInTheMasterPage() {
        master.addCell();
        master.deleteItem(0);
        assertThat(master.items(), is(empty()));
    }

    @Test
    public void aNewItemHasADetailPage() {
        master.addCell();
        master.visitCell(0);
    }
}
