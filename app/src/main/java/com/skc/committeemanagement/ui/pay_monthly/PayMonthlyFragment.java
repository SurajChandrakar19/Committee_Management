package com.skc.committeemanagement.ui.pay_monthly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skc.committeemanagement.databinding.FragmentPayMonthlyBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;

public class PayMonthlyFragment extends Fragment {

    private FragmentPayMonthlyBinding binding;
    String monthlyAmount;
    DBManagerAll dbManagerAll;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        com.example.samitisath.ui.pay_monthly.PayMonthlyViewModel payMonthlyViewModel =
//                new ViewModelProvider(this).get(com.example.samitisath.ui.pay_monthly.PayMonthlyViewModel.class);

        binding = FragmentPayMonthlyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textPayMonthly;
//        payMonthlyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        dbManagerAll = new DBManagerAll(getContext());
         monthlyAmount =  dbManagerAll.fetchSetting().getPayMonthlyAmount();
        binding.idPayMonthlyFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PayMonthlyAdapter payMonthlyAdapter = new PayMonthlyAdapter(getContext(), dbManagerAll.fetchPayMonthlyListFromMember(),monthlyAmount);
        binding.idPayMonthlyFragmentRecyclerView.setAdapter(payMonthlyAdapter);


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManagerAll.close();
    }
}