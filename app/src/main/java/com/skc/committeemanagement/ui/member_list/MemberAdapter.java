package com.skc.committeemanagement.ui.member_list;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.MemberListShowBinding;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.mberberviewholder>{
    ArrayList<ListMemberModel> listMemberModelArrayList;

    public MemberAdapter(ArrayList<ListMemberModel> memberModelList) {
        this.listMemberModelArrayList = memberModelList;
    }

    @NonNull
    @Override
    public mberberviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MemberListShowBinding binding = MemberListShowBinding.inflate(inflater, parent,false);
        return new mberberviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull mberberviewholder holder, int position) {

        ListMemberModel listMemberModel = listMemberModelArrayList.get(position);
        holder.singlerowdesignBinding.setMemberlist(listMemberModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("memberAdapter",listMemberModel);

                Navigation.findNavController(view).navigate(R.id.action_nav_list_member_to_nav_list_edit_member,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMemberModelArrayList.size();
    }


    //member class
    static class mberberviewholder extends RecyclerView.ViewHolder
    {
        MemberListShowBinding singlerowdesignBinding;
        public mberberviewholder(@NonNull MemberListShowBinding singlerowdesignBinding) {
            super(singlerowdesignBinding.getRoot());
            this.singlerowdesignBinding = singlerowdesignBinding;
        }
    }
}
