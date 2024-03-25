package com.skc.committeemanagement.ui.manage_receive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skc.committeemanagement.databinding.FragmentReceiveListBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;


public class ReceiveListFragment extends Fragment {

    private FragmentReceiveListBinding binding;
    DBManagerAll dbManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReceiveListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbManager = new DBManagerAll(getContext());

        binding.list.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        ReceiveListAdapter receiveAdapter = new ReceiveListAdapter(dbManager.fetchReceiveList());

        binding.list.setAdapter(receiveAdapter);

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        dbManager.close();
    }
}