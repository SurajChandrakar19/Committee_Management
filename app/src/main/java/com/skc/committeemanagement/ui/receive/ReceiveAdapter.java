package com.skc.committeemanagement.ui.receive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.ReceiveDropDownListViewerBinding;

import java.util.ArrayList;

public class ReceiveAdapter extends RecyclerView.Adapter<ReceiveAdapter.ReceiveManageViewHolder> {

    ArrayList<ReceiveStructure> receivePayArrayList;

    public ReceiveAdapter(ArrayList<ReceiveStructure> receiveStructure) {
        this.receivePayArrayList = receiveStructure;
    }

    @NonNull
    @Override
    public ReceiveManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ReceiveDropDownListViewerBinding binding = ReceiveDropDownListViewerBinding.inflate(inflater, parent,false);
        return new ReceiveManageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiveManageViewHolder holder, int position) {

        ReceiveStructure receivePayStructure = receivePayArrayList.get(position);
        holder.binding.setReceivestructure(receivePayStructure);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("receive",receivePayStructure);
//                 bundle.put
//                 bundle.putInt("_id",managePayStructure.getId());
//                 bundle.putString("name",managePayStructure.getName());
//                 bundle.putString("address",managePayStructure.getAddress());
//                 bundle.putString("amount",managePayStructure.getAmount());
//                 bundle.putString("rate",managePayStructure.getRate());
//                 bundle.putString("number",managePayStructure.getNumber());
//                 bundle.putString("date",managePayStructure.getDate());

//                navController = Navigation.findNavController(view);
                Navigation.findNavController(view).navigate(R.id.action_nav_receive_to_nav_receive_after,bundle);
//                itemClickListener.onItemClick(managePayStructure);
            }
        });
    }

    @Override
    public int getItemCount() {
        return receivePayArrayList.size();
    }

    public static class ReceiveManageViewHolder extends RecyclerView.ViewHolder {

        ReceiveDropDownListViewerBinding binding;

        public ReceiveManageViewHolder(@NonNull ReceiveDropDownListViewerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
