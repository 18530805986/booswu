package com.sawapps.baymaxhealthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Arrays;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;

    View view;

    TextView appName;

    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Calorie Tracker");
        setSupportActionBar(toolbar);

        view = findViewById(R.id.rootView);
        version = findViewById(R.id.version);


        appName = findViewById(R.id.app_name);

        findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        version.setText("Version " + Utils.getBuildNumber(this));

        if (FirebaseAuth.getInstance() != null && FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
//        startActivity(new Intent(LoginActivity.this, MainIntroActivity.class));

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {


                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {


                    HashMap<String, Object> map = new HashMap<>();
                    map.put("user_id", currentUser.getUid());
                    map.put("user_name", currentUser.getDisplayName() != null ? currentUser.getDisplayName() : "");
                    map.put("user_gcm_id", FirebaseInstanceId.getInstance().getToken() != null ? FirebaseInstanceId.getInstance().getToken() : "");
                    map.put("user_email", currentUser.getEmail() != null ? currentUser.getEmail() : "");
                    map.put("user_mobile", currentUser.getPhoneNumber() != null ? currentUser.getPhoneNumber() : "");
                    map.put("user_photo_url", currentUser.getPhotoUrl() != null ? currentUser.getPhotoUrl() : "");


                    Utils.showSnackbar(view, "Successfully Signed In");


                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();

                }
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Utils.showSnackbar(view, "SignIn cancelled");
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Utils.showSnackbar(view, "No Internet Connection");
                    return;
                }

                Utils.showSnackbar(view, "Unknown error");

            }
        }
    }

    private void signIn() {
        AuthUI.IdpConfig phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
                .setDefaultCountryIso("us")
                .build();
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build(),

                                phoneConfigWithDefaultNumber
                        ))
                        .setLogo(R.mipmap.img)

                        .setIsSmartLockEnabled(false)
                        .build()
                ,
                RC_SIGN_IN);
    }

}
