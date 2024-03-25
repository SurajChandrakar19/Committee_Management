package com.skc.committeemanagement.login_tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.skc.committeemanagement.LoginMainActivity;
import com.skc.committeemanagement.MainActivity;
import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.LoginTabFragmentBinding;
import com.skc.committeemanagement.ui.Editing;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class LoginTabFragment extends Fragment {
    // inherit to all layout,text,input etc.
    LoginTabFragmentBinding binding;
    FirebaseAuth mAuthlogin;



    float v = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        binding = LoginTabFragmentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        // all inherit assign values and find by id
        Editing.slideToLayout(binding.loginTabUserIdLayout,binding.loginTabPasswordLayout);
        Editing.slideToButton(binding.loginLoginButton);

        binding.loginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = new LoginMainActivity();
                String userid = Objects.requireNonNull(binding.userNameEditText.getText()).toString();
                String password = Objects.requireNonNull(binding.passwordEditText.getText()).toString();

                if(userid.equals("") || password.equals(""))
                {
                    Toast.makeText(getContext(), "Please Enter username/password", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuthlogin = FirebaseAuth.getInstance();
                    binding.progressBar.setVisibility(View.VISIBLE);
                    mAuthlogin.signInWithEmailAndPassword(userid, password)
                            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        binding.progressBar.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        intent.putExtra("email", Objects.requireNonNull(mAuthlogin.getCurrentUser()).getEmail());
                                        intent.putExtra("uid", mAuthlogin.getCurrentUser().getUid());
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        binding.progressBar.setVisibility(View.INVISIBLE);
                                        binding.userNameEditText.setText("");
                                        binding.passwordEditText.setText("");
                                        Toast.makeText(getContext(), "Invalid email/password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

//        processrewuest();
        return view;
    }

//    private void processrewuest() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        binding.loginTabCreateSamiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_tab_login_fragment_to_nav_tab_signup_fragment);
            }
        });
    }
}
