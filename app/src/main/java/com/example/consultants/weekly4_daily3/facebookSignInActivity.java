package com.example.consultants.weekly4_daily3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.consultants.weekly4_daily3.ui.user.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class facebookSignInActivity extends AppCompatActivity {

    public static final String TAG = facebookSignInActivity.class.getSimpleName() + "_TAG";

    MainActivity mainActivity;
    private FirebaseAuth auth;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_sign_in);

        Log.d(TAG, "onCreate: ");

        auth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();


//---------------------------------------------------------------------------------------------------
        //TODO WILL THIS FUCKING WORK, CRASH GIVEN CALL FROM OUTSIDE
//        LoginButton loginButton = findViewById(R.id.buttonFacebookLogin);
        LoginButton loginButton = findViewById(R.id.buttonFacebookLogin);
//        Button loginButton = findViewById(R.id.btnFacebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // [START_EXCLUDE]
//                updateUI(null);
                // [END_EXCLUDE]
            }


            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // [START_EXCLUDE]
//                updateUI(null);
                // [END_EXCLUDE]
            }
        });
// [END initialize_fblogin]
//---------------------------------------------------------------------------------------------------

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();

        getIntent();
        auth.getCurrentUser();

//        Intent signInIntent = ;
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(facebookSignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    public void onfbSignOut(View view) {
        Log.d(TAG, "onfbSignOut: ");
        auth.signOut();
        LoginManager.getInstance().logOut();
    }

    public void onfbSignIn(View view) {
    }
}
