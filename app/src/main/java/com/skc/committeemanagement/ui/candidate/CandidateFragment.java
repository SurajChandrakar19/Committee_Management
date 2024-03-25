package com.skc.committeemanagement.ui.candidate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skc.committeemanagement.databinding.FragmentCandidateBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;

public class CandidateFragment extends Fragment {
    DBManagerAll dbManagerAll;
    private FragmentCandidateBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        com.example.samitisath.ui.candidate.CandidateViewModel candidateViewModel =
//                new ViewModelProvider(this).get(com.example.samitisath.ui.candidate.CandidateViewModel.class);

        binding = FragmentCandidateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbManagerAll = new DBManagerAll(getContext());

        binding.idRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

         CandidateListAdapter cndidateListAdapter = new CandidateListAdapter(dbManagerAll.fetchPayListNameNumber());
        binding.idRecyclerView.setAdapter(cndidateListAdapter);

//        final TextView textView = binding.textCandidate;
//        candidateViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManagerAll.close();
    }
}