package com.skc.committeemanagement.ui.candidate;

import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skc.committeemanagement.databinding.CandidateListBinding;

import java.util.ArrayList;

public class CandidateListAdapter extends RecyclerView.Adapter<CandidateListAdapter.CandidateListManageViewHolder> {
    ArrayList<CandidateListStructure> candidateListStructureArrayList;

    public CandidateListAdapter(ArrayList<CandidateListStructure> candidateListStructureArrayList){
        this.candidateListStructureArrayList = candidateListStructureArrayList;
    }
    @NonNull
    @Override
    public CandidateListAdapter.CandidateListManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CandidateListBinding binding = CandidateListBinding.inflate(inflater,parent,false);
        return new CandidateListManageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateListManageViewHolder holder, int position) {
        CandidateListStructure candidateListStructure = candidateListStructureArrayList.get(position);
        holder.binding.setCandidatelist(candidateListStructure);
    }

    @Override
    public int getItemCount() {
        return candidateListStructureArrayList.size();
    }

    public static class CandidateListManageViewHolder extends RecyclerView.ViewHolder {
        CandidateListBinding binding;
        public CandidateListManageViewHolder(@NonNull CandidateListBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
