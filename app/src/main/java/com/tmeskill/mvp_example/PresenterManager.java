package com.tmeskill.mvp_example;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by tmeskill on 2/24/2016.
 */
public class PresenterManager {

    private static final String PRESENTER_ID_KEY = "com.tmeskill.mvp_example.presenterId";

    private static PresenterManager instance;

    private final AtomicLong currentId;

    // Map for example - use Cache
    private final Map<Long, AbstractPresenter> presenters;

    PresenterManager() {
        currentId = new AtomicLong();
        presenters = new HashMap<>();
    }

    public static PresenterManager getInstance() {
        if (instance == null) {
            instance = new PresenterManager();
        }
        return instance;
    }

    public <P extends AbstractPresenter<?>> P restorePresenter(Bundle savedInstanceState) {
        Long presenterId = savedInstanceState.getLong(PRESENTER_ID_KEY);
        P presenter = (P) presenters.get(presenterId);
        return presenter;
    }

    public void savePresenter(AbstractPresenter<?> presenter, Bundle outState) {
        long presenterId = currentId.incrementAndGet();
        presenters.put(presenterId, presenter);
        outState.putLong(PRESENTER_ID_KEY, presenterId);
    }
}
