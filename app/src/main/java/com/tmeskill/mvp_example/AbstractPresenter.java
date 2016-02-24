package com.tmeskill.mvp_example;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by tmeskill on 2/24/2016.
 */
public abstract class AbstractPresenter<V> {

    private WeakReference<V> view;

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
    }

    public void unbindView() {
        this.view = null;
    }

    protected abstract void updateView();

    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }
}
