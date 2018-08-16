package com.android.wipro.assignment.views;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.wipro.assignment.BuildConfig;
import com.android.wipro.assignment.R;
import com.android.wipro.assignment.model.Rows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowDialog;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class MainActivityTest {

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    private ActivityController mActivityController;
    private MainActivity mActivity;
    private RowsAdapter adapter;
    ActionBar actionBar;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Intent intent = new Intent(ShadowApplication.getInstance().getApplicationContext(), MainActivity.class);
        mActivityController = Robolectric.buildActivity(MainActivity.class, intent).create();
        mActivity = (MainActivity) mActivityController.get();
        swipeRefreshLayout = mActivity.findViewById(R.id.swipeRefreshLayout);
        progressBar = mActivity.findViewById(R.id.progress_bar);
        recyclerView = mActivity.findViewById(R.id.recyclerView);
        adapter = mock(RowsAdapter.class);
        actionBar = mock(ActionBar.class);
    }

    @After
    public void tearDown() {
        mActivityController.pause();
        mActivityController.stop();
        mActivityController.destroy();
    }

    @Test
    public void test_onError() {
        mActivity.showError("network error");
        Dialog dialog = ShadowDialog.getLatestDialog();
        assertNotNull(dialog);

        TextView title = (TextView) dialog.findViewById(R.id.error_title);
        TextView message = (TextView) dialog.findViewById(R.id.error_message);
        Button button = (Button) dialog.findViewById(R.id.button_ok);

        assertNotNull(title);
        assertNotNull(message);
        assertNotNull(button);

        assertEquals(mActivity.getString(R.string.error), title.getText().toString());

        button.performClick();
    }

    @Test
    public void test_showLoading() {
        mActivity.showLoading();
        assertTrue(progressBar.getVisibility() == View.VISIBLE);
    }

    @Test
    public void test_hideLoading() {
        mActivity.hideLoading();
        assertTrue(progressBar.getVisibility() == View.GONE);
//        assertTrue(swipeRefreshLayout.getVisibility() == View.GONE);
    }

    @Test
    public void showResult() {
        Rows rows = new Rows();
        rows.setTitle("title");
        List<Rows> list = Arrays.asList(rows);
        adapter.setRows(list);
    }

    @Test
    public void showTitle() {
        actionBar.setTitle("title");
    }


}