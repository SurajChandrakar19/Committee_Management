package com.skc.committeemanagement.ui.receive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skc.committeemanagement.databinding.FragmentReceiveBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;

public class ReceiveFragment extends Fragment
{
    private FragmentReceiveBinding binding;
    DBManagerAll dbManager;
//    ArrayList<ReceiveStructure> fetchReceiveArrayList = new ArrayList<>();
    String[] arr = new String[2];

        public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
        {
            binding = FragmentReceiveBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            dbManager = new DBManagerAll(getContext());

            binding.list.setLayoutManager(new LinearLayoutManager(getContext()));

            binding.list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            ReceiveAdapter receiveAdapter = new ReceiveAdapter(dbManager.fetchPayReceiveList());
            binding.list.setAdapter(receiveAdapter);

//        final TextView textView = binding.textReceive;
//        receiveViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManager.close();
    }
}