package com.skc.committeemanagement.ui.setting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skc.committeemanagement.databinding.FragmentSettingBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;

import java.util.Objects;

public class SettingFragment extends Fragment {
    SettingStructure settingStructure;
    DBManagerAll dbManagerAll;
    FragmentSettingBinding binding;
    String monthlyAmount,depositAmount;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       dbManagerAll = new DBManagerAll(getContext());

        binding.setSettingStructure(dbManagerAll.fetchSetting());


        binding.setMothlyPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthlyAmount = Objects.requireNonNull(binding.monthlyPayEditText.getText()).toString();
                    try {
                        if (dbManagerAll.fetchSetting().id == 0){
                            dbManagerAll.settingInsert(monthlyAmount);
                        }else {dbManagerAll.updateSetting(monthlyAmount);}
                    } catch (Exception ignored) {}
                Toast.makeText(getContext(), "Set Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        binding.cashDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depositAmount = Objects.requireNonNull(binding.cashAmountEditText.getText()).toString();
                if (!depositAmount.equals("")){
                try {
                   dbManagerAll.cashInsert("cashDeposit",depositAmount,"","");
                    Toast.makeText(getContext(), "Deposited Successfully", Toast.LENGTH_SHORT).show();
                    binding.cashAmountEditText.setText("");
                } catch (Exception ignored) {}
            }}
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManagerAll.close();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}