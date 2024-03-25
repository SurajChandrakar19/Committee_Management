package com.skc.committeemanagement;


import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.skc.committeemanagement.databinding.ActivityNewLoginPageBinding;
import com.skc.committeemanagement.ui.Editing;
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

import java.util.Objects;

public class NewLoginPage extends AppCompatActivity
    {

        FirebaseAuth mAuth;
        ActivityNewLoginPageBinding binding;
        private GoogleSignInClient mGoogleSignInClient;
        private static final int REQ_ONE_TAP = 1997;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            binding = ActivityNewLoginPageBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            mAuth = FirebaseAuth.getInstance();
            processRequest();

            // all inherit assign values and find by id
            Editing.slideToLayout(binding.loginTabUserIdLayout,binding.loginTabPasswordLayout);
            Editing.slideToButton(binding.loginLoginButton);

//for creating new Account (user id, password, samiti)
            binding.loginTabCreateSamiti.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(NewLoginPage.this,NewSignUp.class);
                    startActivity(intent);
                }
            });

//Press login button
            binding.loginLoginButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Activity activity = new LoginMainActivity();
                    String userid = Objects.requireNonNull(binding.userNameEditText.getText()).toString();
                    String password = Objects.requireNonNull(binding.passwordEditText.getText()).toString();

                    if(userid.equals("") || password.equals(""))
                    {
                        Toast.makeText(NewLoginPage.this, "Please Enter username/password", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        mAuth = FirebaseAuth.getInstance();
                        binding.progressBar.setVisibility(View.VISIBLE); //round for buffering
                        mAuth.signInWithEmailAndPassword(userid, password)
                                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task)
                                    {
                                        if (task.isSuccessful())
                                        {
                                            // Sign in success, update UI with the signed-in user's information
                                            binding.progressBar.setVisibility(View.INVISIBLE);
                                            Intent intent = new Intent(NewLoginPage.this, MainActivity.class);
                                            intent.putExtra("email", Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
                                            intent.putExtra("uid", mAuth.getCurrentUser().getUid());
    //                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            finish();
                                        }

                                        else
                                        {
                                            // If sign in fails, display a message to the user.
                                            binding.progressBar.setVisibility(View.INVISIBLE);
                                            binding.userNameEditText.setText("");
                                            binding.passwordEditText.setText("");
                                            Toast.makeText(NewLoginPage.this, "Invalid email/password", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });//end login button

//google button click auto sign_in
            binding.signInGoogle.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    //this is a function for popup selecting(intent) your gmail
                    signIn();
                }
            });
        }

        @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            //updateUI(currentUser);
        }
  //       [END on_start_check_user]

    //     [START onactivityresult] this is old method
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent data)
//        {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//    //        Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_SHORT).show();
//            if (requestCode == REQ_ONE_TAP)
//            {
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//                //try for login auto with gmail
//                try
//                {
//                    // Google Sign In was successful, authenticate with Firebase
//                    GoogleSignInAccount account = task.getResult(ApiException.class);
//                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
//                    firebaseAuthWithGoogle(account.getIdToken());
//                }
//
//                //if any problem the show here only adding e with try again
//                catch (ApiException e)
//                {
//                    Toast.makeText(this,"Try again", Toast.LENGTH_SHORT).show();
//                    // Google Sign In failed, update UI appropriately
//    //                  Log.w(TAG, "Google sign in failed", e);
//                }
//            }
//        }

        // [END onactivityresult]

//here to be start function popup for selecting gmail account
        private void signIn()
        {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            //old method
//            startActivityForResult(signInIntent, REQ_ONE_TAP);
//            //new method
            activityResultLauncher.launch(signInIntent);
        }

        // [START auth_with_google]
        private void firebaseAuthWithGoogle(String idToken)
        {
            binding.progressBar.setVisibility(View.VISIBLE);
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                mAuth = FirebaseAuth.getInstance();
                                // Sign in success, update UI with the signed-in user's information
    //                            Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
    //                            updateUI(user);
                                binding.progressBar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(NewLoginPage.this, MainActivity.class);
    //                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                Toast.makeText(NewLoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }

                            else
                            {
                                // If sign in fails, display a message to the user.
    //                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                                Toast.makeText(NewLoginPage.this, "Process Error", Toast.LENGTH_SHORT).show();
    //                            updateUI(null);
                            }
                        }
                    });
        }
        // [END auth_with_google]

        // [START signin]

        private void processRequest()
        {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            finishAffinity();
        }

        @Override
        protected void onDestroy()
        {
            binding = null;
            super.onDestroy();
        }

        // this is a new method for startActivityResult

        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {

                            @Override
                            public void onActivityResult(ActivityResult activityResult) {

//                                super.onActivityResult(REQ_ONE_TAP,resultCode,data);
                                if (activityResult.getResultCode() == RESULT_OK)
                                {
                                    Log.d(TAG, "hello:");
                                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.getData());

                                    //try for login auto with gmail
                                    try
                                    {
                                        // Google Sign In was successful, authenticate with Firebase
                                        GoogleSignInAccount account = task.getResult(ApiException.class);
                                        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                                        firebaseAuthWithGoogle(account.getIdToken());
                                    }

                                    //if any problem the show here only adding e with try again
                                    catch (ApiException e)
                                    {
//                                        Log.d(TAG, "Try again" );
                                        //Toast.makeText(this,"Try again", Toast.LENGTH_SHORT).show();
                                        // Google Sign In failed, update UI appropriately
                                        Log.w(TAG, "Google sign in failed", e);
                                    }
                                }
                            }
                        }
                );
    }

