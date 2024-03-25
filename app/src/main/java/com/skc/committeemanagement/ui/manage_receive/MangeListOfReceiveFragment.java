package com.skc.committeemanagement.ui.manage_receive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skc.committeemanagement.databinding.FragmentMangeListOfReceiveBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;
import com.skc.committeemanagement.ui.Editing;
import com.skc.committeemanagement.ui.receive.ReceiveStructure;

public class MangeListOfReceiveFragment extends Fragment {

    ReceiveStructure receiveStructure;
    FragmentMangeListOfReceiveBinding binding;
    DBManagerAll dbManager;
    //int _id;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMangeListOfReceiveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Editing.slideToLayout(binding.nameLayout, binding.addressLayout, binding.rateLayout, binding.intrestAmountLayout,
                binding.totalAmountLayout, binding.dateLayout);
        Editing.slideToLayout(binding.principalAmountLayout, binding.numberLayout);
        dbManager = new DBManagerAll(getContext());
        //receive data
        assert getArguments() != null;
        receiveStructure = getArguments().getParcelable("receive");

        //set data
        binding.setManagereceivelist(receiveStructure);
/*
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

        binding.buttonUpdateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,address,amount,rate,number,date,interest,total;
                name = Objects.requireNonNull(binding.editTextName.getText()).toString();
                address = Objects.requireNonNull(binding.editTextAddress.getText()).toString();
                amount = Objects.requireNonNull((binding.editTextPrincipalAmount).getText()).toString();
                interest = Objects.requireNonNull((binding.editTextIntrestAmount).getText()).toString();
                total = Objects.requireNonNull((binding.editTextTotalAmount).getText()).toString();
                rate = Objects.requireNonNull((binding.editTextRate).getText()).toString();
                number = Objects.requireNonNull((binding.editTextNumber).getText()).toString();
                date = Objects.requireNonNull((binding.editTextDate).getText()).toString();

                if (name.equals("") || address.equals("") || amount.equals("") || rate.equals("") || number.equals("") || date.equals("") || interest.equals("") || total.equals("")){
                    Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
                }else {
//                            managePayStructure.setId(_id);
                    receiveStructure.setName(name);
                    receiveStructure.setAddress(address);
                    receiveStructure.setPrincipal_amount(amount);
                    receiveStructure.setRate(rate);
                    receiveStructure.setNumber(number);
                    receiveStructure.setDate(date);
                    int affectedRows = dbManager.updateManageReceive(receiveStructure);

//                    binding.idPayInputEditTextName.setText("");
//                    binding.idPayInputEditTextAddress.setText("");
//                    binding.idReceiveInputEditTextPrincipalAmount.setText("");
//                    binding.idReceiveInputEditTextIntrestAmount.setText("");
//                    binding.idReceiveInputEditTextTotalAmount.setText("");
//                    binding.idPayInputEditTextRate.setText("");
//                    binding.idPayInputEditTextMobileNumber.setText("");
//                    binding.idPayInputEditTextDate.setText("");
                    Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                    requireActivity().onBackPressed();
                }
//                returnHome();
            }
        });

        binding.buttonDeleteDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,address,amount,rate,number,date,interest,total;
                name = Objects.requireNonNull(binding.editTextName.getText()).toString();
                address = Objects.requireNonNull(binding.editTextAddress.getText()).toString();
                amount = Objects.requireNonNull((binding.editTextPrincipalAmount).getText()).toString();
                interest = Objects.requireNonNull((binding.editTextIntrestAmount).getText()).toString();
                total = Objects.requireNonNull((binding.editTextTotalAmount).getText()).toString();
                rate = Objects.requireNonNull((binding.editTextRate).getText()).toString();
                number = Objects.requireNonNull((binding.editTextNumber).getText()).toString();
                date = Objects.requireNonNull((binding.editTextDate).getText()).toString();

                if (name.equals("") || address.equals("") || amount.equals("") || rate.equals("") || number.equals("") || date.equals("") || interest.equals("") || total.equals("")){
                    Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
                }else {
                    dbManager.deleteManageReceiveOneRow(receiveStructure.getId());
//                    binding.idPayInputEditTextName.setText("");
//                    binding.idPayInputEditTextAddress.setText("");
//                    binding.idReceiveInputEditTextPrincipalAmount.setText("");
//                    binding.idReceiveInputEditTextIntrestAmount.setText("");
//                    binding.idReceiveInputEditTextTotalAmount.setText("");
//                    binding.idPayInputEditTextRate.setText("");
//                    binding.idPayInputEditTextMobileNumber.setText("");
//                    binding.idPayInputEditTextDate.setText("");
                    Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                    requireActivity().onBackPressed();
                }
//                returnHome();
            }
        });
*/
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        dbManager.close();
    }
}