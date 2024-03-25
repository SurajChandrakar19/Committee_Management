package com.skc.committeemanagement.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.skc.committeemanagement.databinding.FragmentDashboardBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;
import com.skc.committeemanagement.ui.Checking;

public class DashboardFragment extends Fragment {
    DBManagerAll dbManagerAll;
    FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbManagerAll = new DBManagerAll(getContext());

        binding.showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.receivePandding.setText(Checking.receivePending(dbManagerAll.fetchPayReceiveList()));

                long cash = Checking.getCashInHand(dbManagerAll.fetchCash());
                binding.cashInHandTextView.setText(String.valueOf(cash));

                binding.totalMemberCollection.setText(Checking.memberTotalCollection(dbManagerAll.fetchPayMonthly()));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManagerAll.close();
    }
}