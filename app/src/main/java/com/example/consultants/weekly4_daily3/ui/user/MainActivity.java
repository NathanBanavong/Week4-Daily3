package com.example.consultants.weekly4_daily3.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.consultants.weekly4_daily3.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseUser;

//TODO will need to reorganize -> implement Authorize Contract
public class MainActivity extends AppCompatActivity implements AuthorizePresenter.Callback {

    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final int RC_SIGN_IN = 9001; //for google


    private EditText etUserEmail;
    private EditText etPassword;
    private GoogleSignInClient mGoogleSignInClient;
    private AuthorizePresenter authorizePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBind();

        //TODO may not read gso
        //---------------------------------------------------------------------------------------------
        // [START config_signin]
        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
        // [END config_signin]
        //---------------------------------------------------------------------------------------------


    }

    public void onBind() {
        etUserEmail = findViewById(R.id.etUserEmail);
        etPassword = findViewById(R.id.etPassword);
        //TODO see if if 'this' is correct
        authorizePresenter = new AuthorizePresenter(this, new AuthorizeListener(this));

    }

    //TODO: 1 - when click google pass to presenter
    //TODO: 2 -

    public void onSignUp(View view) {
        authorizePresenter.signUp(etUserEmail.getText().toString(),
                etPassword.getText().toString());
    }

    public void onSignIn(View view) {
        authorizePresenter.signIn(etUserEmail.getText().toString(),
                etPassword.getText().toString());
    }


    //TODO this will need to be handled by the presenter
    @Override
    public void onUserValidated(FirebaseUser user) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUserInvalidated() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }


    public void onOptionalSignIn(View view) {

        switch (view.getId()) {
            case R.id.btnGoogle:
                //TODO see if below works once seeing if google works first
                //sending to presenter -> googleSign in

                authorizePresenter.googleSignIn();


//                Intent googleIntent = new Intent(getApplicationContext(), GoogleSignInActivity.class);
//                startActivity(googleIntent);
                //private static final int RC_SIGN_IN = 9001;
                //TODO test if intent send here to GoogleSign in
                //will pass to the start activity below
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;

            case R.id.btnTwitter:

                break;

            case R.id.buttonFacebookLogin:
                    authorizePresenter.facebookSignIn();
                break;
            case R.id.btnFacebook:
                //TODO attempt the leap of faith -> pass to presenter
                authorizePresenter.facebookSignIn();
                break;
        }

    }

    //TODO THIS IS A MESS -> need to reorganize: for google
////-------------------------------------------------------------------------------------------------------------------------
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
//    }
//
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//        // [START_EXCLUDE silent]
//        showProgressDialog();
//        // [END_EXCLUDE]
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        Auth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
////                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//
//                        // [START_EXCLUDE]
//                        hideProgressDialog();
//                        // [END_EXCLUDE]
//                    }
//                });
//    }

//-------------------------------------------------------------------------------------------------------------------------

}
