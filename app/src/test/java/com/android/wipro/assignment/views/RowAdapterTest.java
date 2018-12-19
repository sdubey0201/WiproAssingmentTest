package com.android.wipro.assignment.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.android.wipro.assignment.BuildConfig;
import com.android.wipro.assignment.R;
import com.android.wipro.assignment.model.Row;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class RowAdapterTest {
    private RowsAdapter adapter;
    private View mockView;
    private RowsAdapter.RowsViewholder holder;

    @Before
    public void setUp() throws Exception {
        adapter = new RowsAdapter();
        mockView = mock(View.class);

    }

    @Test
    public void itemCount() {
        Row row = new Row();
        adapter.setRows(Arrays.asList(row, row, row));
        assertEquals(adapter.getItemCount(),3);
    }


    @Test
    public void onBindViewHolder_test() {
        Row row = new Row();
        row.setTitle("title");

        adapter.setRows(Arrays.asList(row));
        LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //We have a layout especially for the items in our recycler view. We will see it in a moment.
        View listItemView = inflater.inflate(R.layout.fact_item, null, false);
        holder = new RowsAdapter.RowsViewholder(listItemView);
        adapter.onBindViewHolder(holder, 0);
        assertEquals(holder.titleView.getText().toString(),"title");
    }

    @After
    public void tearDown() {
        adapter = null;
        mockView = null;
    }


}