package com.tmeskill.mvp_example;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenterImpl presenter;

    private Button loadDataButton;
    private TextView dataTextView;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new MainPresenterImpl();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_main);

        loadDataButton = (Button) findViewById(R.id.loadDataButton);
        dataTextView = (TextView) findViewById(R.id.dataTextView);

        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoadDataClicked();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    @Override
    public void removeLoading() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    @Override
    public void showEmpty() {
        dataTextView.setText("No Data!");
    }

    @Override
    public void showLoading() {
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();
    }

    @Override
    public void updateData(String dataString) {
        dataTextView.setText(dataString);
    }
}
