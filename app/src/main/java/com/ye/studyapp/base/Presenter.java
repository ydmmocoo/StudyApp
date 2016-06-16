package com.ye.studyapp.base;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
