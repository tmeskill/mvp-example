package com.tmeskill.mvp_example;

/**
 * Created by tmeskill on 2/24/2016.
 */
public interface MainView {

    void removeLoading();

    void showEmpty();

    void showLoading();

    void updateData(String data);
}
