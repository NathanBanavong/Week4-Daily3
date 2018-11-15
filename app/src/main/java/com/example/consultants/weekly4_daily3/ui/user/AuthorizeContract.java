package com.example.consultants.weekly4_daily3.ui.user;

import com.example.consultants.weekly4_daily3.ui.base.BasePresenter;
import com.example.consultants.weekly4_daily3.ui.base.BaseView;
import com.google.firebase.auth.FirebaseUser;

public interface AuthorizeContract {
    interface View extends BaseView {

    }

    interface MainActivity extends BasePresenter<View> {

//        void getRandomUsers(String gender, int results);
    }

}
