package com.skc.committeemanagement.ui.pay;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.FragmentPayBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;
import com.skc.committeemanagement.ui.Checking;
import com.skc.committeemanagement.ui.Editing;
import com.skc.committeemanagement.ui.setting.CashViewStructure;

import java.util.Calendar;
import java.util.Objects;


public class PayFragment extends Fragment
{

    private DBManagerAll dbManager;
    CashViewStructure cashViewStructure;
    private SimpleCursorAdapter adapter;
    ArrayAdapter<String> arrayAdapterRate;

    //    final String[] from = new String[] {DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.ADDRESS, DatabaseHelper.AMOUNT, DatabaseHelper.RATE, DatabaseHelper.NUMBER, DatabaseHelper.DATE};
    private FragmentPayBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        PayViewModel notificationsViewModel =
//                new ViewModelProvider(this).get(PayViewModel.class);

        binding = FragmentPayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //layout motion
        Editing.slideToLayout(binding.nameLayout,binding.addressLayout,binding.amountLayout,binding.rateLayout,binding.numberLayout,binding.dateLayout);
        Editing.slideToButton(binding.payButton);

        //showing dropdown list for use rate
        arrayAdapterRate = new ArrayAdapter<String>(getContext(), R.layout.drop_down_list,getResources().getStringArray(R.array.select_rate));
        binding.editTextRate.setAdapter(arrayAdapterRate);

        //select date and set text on date for mate
        Calendar calendarDate = Calendar.getInstance();
        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        String day,month;
                        if (view.getDayOfMonth() < 10 || view.getMonth() < 10){

                            if (view.getDayOfMonth() < 10 && view.getMonth() > 9 )
                            {
                                String dmy = ("0" + view.getDayOfMonth() + "/" + (view.getMonth()+1) + "/" + view.getYear());
                                binding.editTextDate.setText(dmy);
                            }
                            else{
                                if ((view.getMonth()+1) < 10 && view.getDayOfMonth() > 9)
                                {
                                    String dmy = ( view.getDayOfMonth() + "/" + "0" + (view.getMonth()+1) + "/" + view.getYear());
                                    binding.editTextDate.setText(dmy);
                                }
                                else{
                                    String dmy = ("0" + view.getDayOfMonth() + "/" + "0" + (view.getMonth()+1) + "/" + view.getYear());
                                    binding.editTextDate.setText(dmy);
                                }
                            }
                        }else {
                            String dmy = (view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear());
                            binding.editTextDate.setText(dmy);
                        }
                    }
                },calendarDate.get(Calendar.YEAR),calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
//        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int y, int m, int d) {
//                        String date = view.getDayOfMonth() + "/" + (view.getMonth()+1) + "/" + view.getYear();
//                        binding.editTextDate.setText(date);
//                    }
//                },calendarDate.get(Calendar.YEAR),calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//            }
//        });


//        final TextView textView = binding.textPay;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //calling constructor from DBManager class
        dbManager = new DBManagerAll(getContext());

//        //Open Database if create elseif not create database then create database
//        dbManager.open();

        //After clicking pay button perform this code
        binding.payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = Objects.requireNonNull(binding.editTextName.getText()).toString().trim();
                final String address = Objects.requireNonNull(binding.editTextAddress.getText()).toString();
                final String amount = Objects.requireNonNull(binding.editTextAmount.getText()).toString();
                final String rate = Objects.requireNonNull(binding.editTextRate.getText()).toString();
                final String number = Objects.requireNonNull(binding.editTextNumber.getText()).toString();
                final String date = Objects.requireNonNull(binding.editTextDate.getText()).toString();

                if (name.equals("") || address.equals("") || amount.equals("") || rate.equals("") || number.equals("") || date.equals(""))
                {
                    Toast.makeText(getContext(), "Fill All", Toast.LENGTH_SHORT).show();
                }
                else
                {
                        long cashInHand = Checking.getCashInHand(dbManager.fetchCash());
                        if (cashInHand < Long.parseLong(amount)) {
                            Toast.makeText(getContext(), "Cash Amount < Pay Amount", Toast.LENGTH_SHORT).show();
                        } else {
                            if (binding.editTextNumber.getText().length() < 10){
                                Toast.makeText(getContext(), "Mobile Number is < 10", Toast.LENGTH_SHORT).show();
                            }
                            else {
                            dbManager.payInsert(name, address, amount, rate, number, date);
                            binding.editTextName.setText("");
                            binding.editTextAddress.setText("");
                            binding.editTextAmount.setText("");
                            binding.editTextRate.setText("");
                            binding.editTextNumber.setText("");
                            binding.editTextDate.setText("");
                            Toast.makeText(getContext(), "Pay Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManager.close();
    }
}