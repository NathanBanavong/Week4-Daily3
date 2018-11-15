package com.example.consultants.weekly4_daily3.ui.user;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthorizeListener implements OnCompleteListener<AuthResult> {

    public static final String TAG = AuthorizeListener.class.getSimpleName() + "_TAG";

    //TODO need to make a contract between Presenter and Listener
    AuthorizePresenter.Callback callback;
    int type;

    public AuthorizeListener(AuthorizePresenter.Callback callback) {
        this.callback = callback;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        switch (type) {
            case 0:

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    callback.onUserValidated(FirebaseAuth.getInstance().getCurrentUser());
//                            updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());

//                            updateUI(null);
                    callback.onUserInvalidated();
                }

                break;
            case 1:

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createWithEmail:success");
                    callback.onUserValidated(FirebaseAuth.getInstance().getCurrentUser());
//                            updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createWithEmail:failure", task.getException());

//                            updateUI(null);
                    callback.onUserInvalidated();
                }

                break;
        }
    }
}
