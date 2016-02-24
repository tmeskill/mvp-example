package com.tmeskill.mvp_example;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by tmeskill on 2/24/2016.
 */
public class MainPresenterImpl extends AbstractPresenter<MainView> implements MainPresenter {

    private final AtomicLong dummyCounter = new AtomicLong();
    private WeakReference<MainView> view;
    private boolean isLoadingData = false;
    private String currentData;

    @Override
    public void bindView(@NonNull MainView view) {
        super.bindView(view);

        updateView();
        if (isLoadingData) {
            view().showLoading();
        }
    }

    private void loadData() {
        isLoadingData = true;
        new LoadDataTask().execute();
    }

    @Override
    public void onLoadDataClicked() {
        view().showLoading();
        loadData();
    }

    protected void updateView() {
        if (currentData != null && !currentData.isEmpty()) {
            view().updateData(currentData);
        } else {
            view().showEmpty();
        }
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(5000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            currentData = "data data data data " + dummyCounter.getAndIncrement();
            view().removeLoading();
            isLoadingData = false;
            updateView();
        }
    }
}
