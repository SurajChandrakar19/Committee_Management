package com.skc.committeemanagement.login_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.SignupTabFragmentBinding;
import com.skc.committeemanagement.ui.Editing;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignupTabFragment extends Fragment
{
    //typecasting all variable
    SignupTabFragmentBinding binding;
    FirebaseAuth mAuth;
    float v = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = SignupTabFragmentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        //signup input layout slide
        Editing.slideToLayout(binding.signupLayoutTextSamitiName,binding.signupLayoutTextAddress,binding.signupLayoutTextMobileNumber
                ,binding.signupLayoutTextCreateUsername,binding.signupLayoutTextCreatePassword);

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
                    Toast.makeText(getContext(), "Fill All", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (mobile.trim().length() < 10)
                    {
                        Toast.makeText(getContext(), "mobile number is < 10", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(username,password)
                            .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            binding.signupInputTextSamitiName.setText("");
                                            binding.signupInputTextAddress.setText("");
                                            binding.signupInputTextMobileNumber.setText("");
                                            binding.signupInputTextCreateUsername.setText("");
                                            binding.signupInputTextCreatePassword.setText("");
                                            Toast.makeText(getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getContext(), "Process Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });

        return view;
    }

    //this is maintain to jump and stop Fragment over stack flow
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        binding.backToLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                navController.navigate(R.id.action_nav_tab_signup_fragment_to_nav_tab_login_fragment);
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.nav_tab_login_fragment,true).build();

                navController.navigate(R.id.action_nav_tab_signup_fragment_to_nav_tab_login_fragment,null,navOptions);
            }
        });
    }
}
