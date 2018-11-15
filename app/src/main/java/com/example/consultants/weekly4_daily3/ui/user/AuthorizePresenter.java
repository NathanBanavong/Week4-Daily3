package com.example.consultants.weekly4_daily3.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.consultants.weekly4_daily3.GoogleSignInActivity;
import com.example.consultants.weekly4_daily3.R;
import com.example.consultants.weekly4_daily3.facebookSignInActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthorizePresenter extends AppCompatActivity {

    public static final String TAG = AuthorizePresenter.class.getSimpleName() + "_TAG";
    private static final int RC_SIGN_IN = 9001;

    FirebaseAuth auth;
    Callback callback;
    Activity activity;
    AuthorizeListener completeListener;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private Intent signInIntent;

    public AuthorizePresenter(Activity activity, AuthorizeListener completeListener) {
        auth = FirebaseAuth.getInstance();
        this.callback = (Callback) activity;
        this.activity = activity;
        this.completeListener = completeListener;
    }

    public void signIn(String userEmail, String password) {
        completeListener.setType(0);
        auth.signInWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(activity, completeListener);
    }

    public void signUp(String userEmail, String password) {
        completeListener.setType(1);
        auth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(activity,completeListener);
    }

    //TODO see if presenter will handle the google sign in
    //can handle Firebase Auth
    public void googleSignIn(){

//        -----------------------------------------------------------------------------------------------
//        TODO intent to new class WOOOORRRRRRKKKKKSSSS!!! -> try to have presenter handle it
        Intent googleIntent = new Intent(activity.getApplicationContext(), GoogleSignInActivity.class);
        activity.startActivity(googleIntent);
//        -----------------------------------------------------------------------------------------------
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        auth.getCurrentUser();
//        signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);


    }

    public void facebookSignIn() {
        Log.d(TAG, "facebookSignIn: ");
        Intent fbIntent = new Intent(activity.getApplicationContext(), facebookSignInActivity.class);
        activity.startActivity(fbIntent);
    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//        // [START_EXCLUDE silent]
////        showProgressDialog();
//        // [END_EXCLUDE]
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = auth.getCurrentUser();
////                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
////                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//                    }
//                });
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account);
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
//                // [START_EXCLUDE]
////                updateUI(null);
//                // [END_EXCLUDE]
//            }
//        }
//
//    }

    //TODO need to make this a contract between the AuthorizePresenter and the AuthorizeListener
    interface Callback{
        void onUserValidated(FirebaseUser user);

        void onUserInvalidated();
    }
}
