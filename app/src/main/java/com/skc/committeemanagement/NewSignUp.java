package com.skc.committeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.skc.committeemanagement.databinding.ActivityNewSignUpBinding;
import com.skc.committeemanagement.ui.Editing;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class NewSignUp extends AppCompatActivity {

    ActivityNewSignUpBinding binding;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        //signup input layout slide
        Editing.slideToLayout(binding.signupLayoutTextSamitiName,binding.signupLayoutTextAddress,binding.signupLayoutTextMobileNumber
                ,binding.signupLayoutTextCreateUsername,binding.signupLayoutTextCreatePassword);
        //back to newLogin page
        binding.backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityBack();
            }
        });

        //click on signup button then empilement all condition
        binding.loginSignupButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name, address, mobile, username, password;
                name = Objects.requireNonNull(binding.signupInputTextSamitiName.getText()).toString();
                address = Objects.requireNonNull(binding.signupInputTextAddress.getText()).toString();
                mobile = Objects.requireNonNull(binding.signupInputTextMobileNumber.getText()).toString();
                username = Objects.requireNonNull(binding.signupInputTextCreateUsername.getText()).toString();
                password = Objects.requireNonNull(binding.signupInputTextCreatePassword.getText()).toString();
//                SignupDataHolder obj = new SignupDataHolder(address,mobile,name,password);
//
//                FirebaseDatabase db = FirebaseDatabase.getInstance();
//                DatabaseReference node = db.getReference("signup/id");
//                node.child("809").setValue(obj);
                if (name.trim().equals("") || address.trim().equals("") || mobile.trim().equals("") ||
                        username.trim().equals("") || password.trim().equals(""))
                {
                    Toast.makeText(NewSignUp.this, "Fill All", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    if (mobile.trim().length() < 10)
                    {
                        Toast.makeText(NewSignUp.this, "mobile number is < 10", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        mAuth = FirebaseAuth.getInstance();
                        mAuth.createUserWithEmailAndPassword(username,password)
                                .addOnCompleteListener(NewSignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            binding.signupInputTextSamitiName.setText("");
                                            binding.signupInputTextAddress.setText("");
                                            binding.signupInputTextMobileNumber.setText("");
                                            binding.signupInputTextCreateUsername.setText("");
                                            binding.signupInputTextCreatePassword.setText("");
                                            Toast.makeText(NewSignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(NewSignUp.this, "Process Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });
        }

    private void startActivityBack(){
        Intent intent = new Intent(NewSignUp.this, NewLoginPage.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}