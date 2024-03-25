package com.skc.committeemanagement.ui.add_member;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.databinding.FragmentAddMemberBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;
import com.skc.committeemanagement.ui.Editing;

import java.util.Calendar;
import java.util.Objects;


public class AddMemberFragment extends Fragment {

    private FragmentAddMemberBinding binding;
    DBManagerAll dbManager;
    ArrayAdapter<String> arrayAdapterRate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAddMemberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Editing.slideToLayout(binding.nameLayout,binding.memberTypeLayout,binding.dateLayout,binding.numberLayout);
        Editing.slideToButton(binding.addMember);

        dbManager = new DBManagerAll(getContext());

        arrayAdapterRate = new ArrayAdapter<>(getContext(), R.layout.drop_down_list, getResources().getStringArray(R.array.select_type));
        binding.editTextMemberType.setAdapter(arrayAdapterRate);

        binding.addMember.setOnClickListener(view -> {
            final String name = Objects.requireNonNull(binding.editTextName.getText()).toString();
            final String type = Objects.requireNonNull(binding.editTextMemberType.getText()).toString();
            final String number = Objects.requireNonNull(binding.editTextNumber.getText()).toString();
            final String date = Objects.requireNonNull(binding.editTextDate.getText()).toString();
            if (name.equals("") || type.equals("") || number.equals("") || date.equals(""))
            {
                Toast.makeText(getContext(), "Fill All", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (number.length() < 10){
                    Toast.makeText(getContext(), "Mobile Number is < 10", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbManager.addMemberInsert(name, type, date, number);
                    binding.editTextName.setText("");
                    binding.editTextMemberType.setText("");
                    binding.editTextNumber.setText("");
                    binding.editTextDate.setText("");
                    Toast.makeText(getContext(), "Add Member Successfully", Toast.LENGTH_SHORT).show();
                }
                }
            });

        Calendar calendarDate = Calendar.getInstance();
        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, y, m, d) -> {
                    String date = view1.getDayOfMonth() + "/" + (view1.getMonth()+1) + "/" + view1.getYear();
                    binding.editTextDate.setText(date);
                },calendarDate.get(Calendar.YEAR),calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbManager.close();
    }
}