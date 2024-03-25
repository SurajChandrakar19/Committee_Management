package com.skc.committeemanagement.ui.manage_pay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skc.committeemanagement.databinding.FragmentManagePayBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;

public class ManagePayFragment extends Fragment {

    private FragmentManagePayBinding binding;
    DBManagerAll dbManagerAll;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        ManagePayViewModel managePayViewModel =
//                new ViewModelProvider(this).get(ManagePayViewModel.class);

        binding = FragmentManagePayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbManagerAll = new DBManagerAll(getContext());

        binding.managePayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.managePayRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        ManagePayAdapter managePayAdapter = new ManagePayAdapter(dbManagerAll.fetchManagePayListFromPay());
        binding.managePayRecyclerView.setAdapter(managePayAdapter);
//        final TextView textView = binding.textReceive;
//        memberViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManagerAll.close();
    }

//    @Override
//    public void onItemClick(ManagePayStructure managePayStructure) {
////        Fragment fragment = ManagePayUpdateDeleteFragment.passData(managePayStructure);
////        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.nav_host_fragment_content_main, fragment, "manage_fragment");
////        transaction.disallowAddToBackStack();
////        transaction.commit();
//        Navigation.findNavController().navigate(R.id.action_nav_pay_manage_to_managePayUpdateDeleteFragment);
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//            binding.managePayRecyclerView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Navigation.findNavController(view).navigate(R.id.action_nav_pay_manage_to_managePayUpdateDeleteFragment);
//                }
//
//            });

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
}