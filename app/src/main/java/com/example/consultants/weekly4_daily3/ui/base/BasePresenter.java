package com.example.consultants.weekly4_daily3.ui.base;

public interface BasePresenter <V extends BaseView> {

    void onAttach(V view);

    void onDetach();
}
