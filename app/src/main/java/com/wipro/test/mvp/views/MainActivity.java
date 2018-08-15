package com.wipro.test.mvp.views;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wipro.test.R;
import com.wipro.test.mvp.util.Util;
import com.wipro.test.mvp.model.Rows;
import com.wipro.test.mvp.presenters.RowsPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements RowListView, SwipeRefreshLayout.OnRefreshListener {

    private RowsPresenter presenter;
    private ProgressBar progressBar;
    private RowsAdapter adapter;
    private ActionBar actionBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.title));
        progressBar = findViewById(R.id.progress_bar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RowsAdapter();
        recyclerView.setAdapter(adapter);

        presenter = new RowsPresenter(this);
        if (Util.isNetworkAvailable(this)) {
            presenter.startLoadRows();
        } else {
            showError(getString(R.string.network_not_available_str));
        }

    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String msg) {
        progressBar.setVisibility(View.GONE);
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.error_dialog);

        TextView title = dialog.findViewById(R.id.error_title);
        TextView errorMessage = dialog.findViewById(R.id.error_message);
        Button button = dialog.findViewById(R.id.button_ok);

        title.setText(R.string.error);
        errorMessage.setText(msg);
        button.setOnClickListener(view -> {
            if (dialog == null) {
                return;
            }
            dialog.dismiss();

        });
        dialog.show();
    }

    @Override
    public void showResult(List<Rows> list) {
        adapter.setRows(list);
    }

    @Override
    public void showTitle(String title) {
        actionBar.setTitle(title);
    }

    @Override
    public void onRefresh() {
        if (Util.isNetworkAvailable(this)) {
            presenter.startLoadRows();
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } else {
            showError(getString(R.string.network_not_available_str));
            swipeRefreshLayout.setRefreshing(false);
        }

    }


}
