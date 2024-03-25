package com.skc.committeemanagement.ui.member_list;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skc.committeemanagement.databinding.FragmentListMemberBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;

public class ListMemberFragment extends Fragment {

    //create only reference
    private FragmentListMemberBinding binding;
    DBManagerAll dbManager;
    ListMemberViewModel memberModel;
    private ListMemberViewModel mViewModel;

    //if any use this fragment call this
    public static ListMemberFragment newInstance() {
        return new ListMemberFragment();
    }

    //connect to fragment layout
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListMemberViewModel memberViewModel =
                new ViewModelProvider(this).get(ListMemberViewModel.class);

        //bind all id in fragment
        binding = FragmentListMemberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //creating object
        dbManager = new DBManagerAll(getContext());

        //set on recyclerview Linear Layout
        binding.meberListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        //after avery item decoration line
        binding.meberListRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //calling member adapter for set the content from the database MEMBER TABLE USE ONLY NAME OR NUMBER
        MemberAdapter memberAdapter = new MemberAdapter(dbManager.fetchMemberList());
        binding.meberListRecyclerview.setAdapter(memberAdapter);
//        final TextView textView = binding.textReceive;
//        memberViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManager.close();
    }

//    public List<MemberModel> getMember()
//    {
//        List<MemberModel> slist = new ArrayList<>();
//        slist.add(new MemberModel())
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        NavController navController = Navigation.findNavController(view);
//        binding.bAddMember.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.action_nav_member_to_add_member_fragment);
//
//            }
//        });
//    }
}