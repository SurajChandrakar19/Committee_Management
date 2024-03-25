package com.skc.committeemanagement.ui.receive;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.skc.committeemanagement.databinding.FragmentReceiveMangeBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;
import com.skc.committeemanagement.ui.Checking;
import com.skc.committeemanagement.ui.Editing;

import java.util.Calendar;
import java.util.Objects;


public class ReceiveMangeFragment extends Fragment {

    ReceiveStructure receiveStructure;
    FragmentReceiveMangeBinding binding;
    DBManagerAll dbManagerAll;
    int _id;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentReceiveMangeBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        //sliding all Input text layout
        Editing.slideToLayout(binding.nameLayout,binding.principalAmountLayout,binding.dateLayout,binding.intrestAmountLayout,binding.totalAmountLayout);
        Editing.slideToLayout(binding.dateLayoutTo);
        Editing.slideToButton(binding.receiveButton);

        //select to database
        dbManagerAll = new DBManagerAll(getContext());

        //Receive all data
        assert getArguments() != null;
        receiveStructure = getArguments().getParcelable("receive");

        //set value
        binding.calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editTextDateTo.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "Select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    //call to checking class calculate method
                    String r = Checking.calculateInterest(receiveStructure.principal_amount, receiveStructure.date
                            , binding.editTextDateTo.getText().toString(), receiveStructure.rate);
//                    if (Integer.parseInt(r) < 1){
//                        Toast.makeText(getContext(), "Receive Date is Wrong", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
                        receiveStructure.setInterest(r);
                        int total = Integer.parseInt(r) + Integer.parseInt(receiveStructure.principal_amount);
                        receiveStructure.setTotal_amount(String.valueOf(total));
//                Toast.makeText(getContext(), d, Toast.LENGTH_SHORT).show();
                        binding.setReceivemanageStructure(receiveStructure);
//                    }

                }
            }
        });
        binding.setReceivemanageStructure(receiveStructure);

//        //for use select date receive date
        Calendar calendarDate = Calendar.getInstance();
        binding.editTextDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        String day,month;
                        if (view.getDayOfMonth() < 10 || view.getMonth() < 10){

                            if (view.getDayOfMonth() < 10 && view.getMonth() > 9 )
                            {
                                receiveStructure.setReceiveDate("0" + view.getDayOfMonth() + "/" + (view.getMonth()+1) + "/" + view.getYear());
                                binding.setReceivemanageStructure(receiveStructure);
                            }
                            else{
                                if ((view.getMonth()+1) < 10 && view.getDayOfMonth() > 9)
                                {
                                    receiveStructure.setReceiveDate( view.getDayOfMonth() + "/" + "0" + (view.getMonth()+1) + "/" + view.getYear());
                                    binding.setReceivemanageStructure(receiveStructure);
                                }
                                else{
                                        receiveStructure.setReceiveDate("0" + view.getDayOfMonth() + "/" + "0" + (view.getMonth()+1) + "/" + view.getYear());
                                        binding.setReceivemanageStructure(receiveStructure);
                                }
                            }
                        }else {
                            receiveStructure.setReceiveDate(view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear());
                            binding.setReceivemanageStructure(receiveStructure);
                        }
                    }
                },calendarDate.get(Calendar.YEAR),calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        //insert after clicking receive button
        binding.receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,amount,date,interest,totalAmount;
                name = Objects.requireNonNull(binding.editTextName.getText()).toString();
                amount = Objects.requireNonNull(binding.editTextPrincipalAmount.getText()).toString();
                date = Objects.requireNonNull((binding.editTextDate).getText()).toString();
                interest = Objects.requireNonNull((binding.editTextIntrestAmount).getText()).toString();
                totalAmount = Objects.requireNonNull((binding.editTextTotalAmount).getText()).toString();

                if (name.equals("") || amount.equals("") || date.equals("") || interest.equals("") || totalAmount.equals("")){
                    Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
                }else {
//                            managePayStructure.setId(_id);
                    receiveStructure.setName(name);
                    receiveStructure.setPrincipal_amount(amount);
                    receiveStructure.setInterest(interest);
                    receiveStructure.setDate(date);
                    receiveStructure.setTotal_amount(totalAmount);
                    dbManagerAll.receiveInsert(receiveStructure);

                    requireActivity().onBackPressed();
//                    binding.idPayInputEditTextName.setText("");
//                    binding.idPayInputEditTextAddress.setText("");
//                    binding.idPayInputEditTextAmount.setText("");
//                    binding.idPayInputEditTextRate.setText("");
//                    binding.idPayInputEditTextMobileNumber.setText("");
//                    binding.idPayInputEditTextDate.setText("");

                    Toast.makeText(getContext(), "Received", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManagerAll.close();
    }
}