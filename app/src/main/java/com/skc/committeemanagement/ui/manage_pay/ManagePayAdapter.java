package com.skc.committeemanagement.ui.manage_pay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.ManagePayViewerBinding;

import java.util.ArrayList;

public class ManagePayAdapter extends RecyclerView.Adapter<ManagePayAdapter.ManagePayViewHolder> {
//    private ItemClickListener itemClickListener;
    private ArrayList<ManagePayStructure> managePayStructureArrayList;
//    NavController navController;

    public ManagePayAdapter() {
    }

    public ManagePayAdapter(ArrayList<ManagePayStructure> managePayStructureArrayList) {
        this.managePayStructureArrayList = managePayStructureArrayList;
//        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ManagePayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ManagePayViewerBinding binding = ManagePayViewerBinding.inflate(inflater, parent,false);
        return new ManagePayViewHolder(binding);

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_pay_viewer,parent,false);
//        return (new ManagePayViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ManagePayViewHolder holder, int position) {
        ManagePayStructure managePayStructure = managePayStructureArrayList.get(position);
        holder.binding.setManagepaystructure(managePayStructure);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Bundle bundle = new Bundle();
                 bundle.putParcelable("hello",managePayStructure);
//                 bundle.put
//                 bundle.putInt("_id",managePayStructure.getId());
//                 bundle.putString("name",managePayStructure.getName());
//                 bundle.putString("address",managePayStructure.getAddress());
//                 bundle.putString("amount",managePayStructure.getAmount());
//                 bundle.putString("rate",managePayStructure.getRate());
//                 bundle.putString("number",managePayStructure.getNumber());
//                 bundle.putString("date",managePayStructure.getDate());

//                navController = Navigation.findNavController(view);
                Navigation.findNavController(view).navigate(R.id.action_nav_pay_manage_to_managePayUpdateDeleteFragment,bundle);

//                itemClickListener.onItemClick(managePayStructure);
            }
        });
    }

    @Override
    public int getItemCount() {
        return managePayStructureArrayList.size();
    }

    public static class ManagePayViewHolder extends RecyclerView.ViewHolder{

        ManagePayViewerBinding binding;
        public ManagePayViewHolder(@NonNull ManagePayViewerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
