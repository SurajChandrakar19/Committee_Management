package com.skc.committeemanagement.ui.manage_receive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.ReceiveDropDownListViewerBinding;
import com.skc.committeemanagement.ui.receive.ReceiveStructure;

import java.util.ArrayList;

public class ReceiveListAdapter extends RecyclerView.Adapter<ReceiveListAdapter.ReceiveListManageViewHolder> {

    ArrayList<ReceiveStructure> receiveStructureArrayList;

    public ReceiveListAdapter(ArrayList<ReceiveStructure> receiveStructureArrayList) {
        this.receiveStructureArrayList = receiveStructureArrayList;
    }

    @NonNull
    @Override
    public ReceiveListAdapter.ReceiveListManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ReceiveDropDownListViewerBinding binding = ReceiveDropDownListViewerBinding.inflate(inflater, parent,false);
        return new ReceiveListManageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiveListAdapter.ReceiveListManageViewHolder holder, int position) {

        ReceiveStructure receiveStructure = receiveStructureArrayList.get(position);
        holder.binding.setReceivestructure(receiveStructure);

        holder.itemView.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putParcelable("receive",receiveStructure);

            Navigation.findNavController(view).navigate(R.id.nav_receive_list_to_edit_receive_table,bundle);

        });

    }

    @Override
    public int getItemCount() {
        return receiveStructureArrayList.size();
    }

    public static class ReceiveListManageViewHolder extends RecyclerView.ViewHolder {
        ReceiveDropDownListViewerBinding binding;
        public ReceiveListManageViewHolder(@NonNull ReceiveDropDownListViewerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
