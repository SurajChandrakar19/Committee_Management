package com.skc.committeemanagement.ui.manage_pay;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.skc.committeemanagement.databinding.FragmentManagePayUpdateDeleteBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;
import com.skc.committeemanagement.ui.Editing;

import java.util.Calendar;


public class ManagePayUpdateDeleteFragment extends Fragment
{
    ManagePayStructure managePayStructure;
    FragmentManagePayUpdateDeleteBinding binding;
   public DBManagerAll dbManager;
    int _id;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        binding = FragmentManagePayUpdateDeleteBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        Editing.slideToLayout(binding.nameLayout,binding.addressLayout,binding.amountLayout,binding.rateLayout,binding.numberLayout,binding.dateLayout);
        Editing.slideToButton(binding.buttonDeleteDb,binding.buttonUpdateDb);

            dbManager = new DBManagerAll(getContext());

             assert getArguments() != null;
             managePayStructure = getArguments().getParcelable("hello");

            binding.setManagepaystructure(managePayStructure);
            _id = managePayStructure.getId();


        //for use select date and set
        Calendar calendarDate = Calendar.getInstance();
        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        String date = view.getDayOfMonth() + "/" + (view.getMonth()+1) + "/" + view.getYear();
                        binding.editTextDate.setText(date);
                    }
                },calendarDate.get(Calendar.YEAR),calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

            binding.buttonUpdateDb.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (check())
                    {
                        if (binding.getManagepaystructure().getNumber().length() < 10){
                            Toast.makeText(getContext(), "Mobile Number is < 10", Toast.LENGTH_SHORT).show();
                        }
                        else{
//                            ArrayList<CashStructure> cashStructureArrayList = dbManager.fetchCash();
//                            long cash = Checking.getCashInHand(cashStructureArrayList);
//                            if (Long.parseLong(binding.getManagepaystructure().getAmount()) <= cash){
                                managePayStructure = binding.getManagepaystructure();
                                managePayStructure.setId(_id);
                                int affectedRows = dbManager.updateManagePay(managePayStructure);
                                binding.setManagepaystructure(null);
                                Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                                requireActivity().onBackPressed();
                            }
//                            else Toast.makeText(getContext(), "Amount is > Cash", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
                }
            });

            binding.buttonDeleteDb.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (check())
                    {
                        managePayStructure = binding.getManagepaystructure();
                        managePayStructure.setId(_id);
                        dbManager.deleteManagePayOneRow(managePayStructure.id);
                        Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                        requireActivity().onBackPressed();

                    }

                    else
                        Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
                }
            });

        return root;
    }

    public boolean check()
    {
        //            Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
        return !binding.getManagepaystructure().getName().equals("") && !binding.getManagepaystructure().getAddress().equals("")
                && !binding.getManagepaystructure().getAmount().equals("") && !binding.getManagepaystructure().getRate().equals("")
                && !binding.getManagepaystructure().getNumber().equals("") && !binding.getManagepaystructure().getDate().equals("");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        binding=null;
        dbManager.close();
    }

}