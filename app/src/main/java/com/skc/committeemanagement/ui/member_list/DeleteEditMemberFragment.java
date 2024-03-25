package com.skc.committeemanagement.ui.member_list;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skc.committeemanagement.databinding.FragmentDeleteEditMemberBinding;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;
import com.skc.committeemanagement.ui.Editing;
import java.util.Calendar;
import java.util.Objects;

public class DeleteEditMemberFragment extends Fragment {

    ListMemberModel listMemberModel;
    private FragmentDeleteEditMemberBinding binding;
    DBManagerAll dbManagerAll;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDeleteEditMemberBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        Editing.slideToLayout(binding.nameLayout,binding.memberTypeLayout,binding.dateLayout,binding.numberLayout);
        Editing.slideToButton(binding.buttonDeleteDb,binding.buttonUpdateDb);

        dbManagerAll = new DBManagerAll(getContext());

        assert getArguments() != null;
        listMemberModel = getArguments().getParcelable("memberAdapter");

        binding.setEditDeleteMember(listMemberModel);

        //for use select date and set
        Calendar calendarDate = Calendar.getInstance();

        binding.editTextDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, y, m, d) -> {
                String date = view1.getDayOfMonth() + "/" + (view1.getMonth()+1) + "/" + view1.getYear();
                binding.editTextDate.setText(date);
            },calendarDate.get(Calendar.YEAR),calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        binding.buttonUpdateDb.setOnClickListener(v -> {
            String name,type,date,number;
            name = Objects.requireNonNull(binding.editTextName.getText()).toString();
            type = Objects.requireNonNull(binding.editTextMemberType.getText()).toString();
            number = Objects.requireNonNull((binding.editTextNumber).getText()).toString();
            date = Objects.requireNonNull((binding.editTextDate).getText()).toString();

            if (name.equals("") || type.equals("") || date.equals("") || number.equals("")){
                Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
            }else {
//                            managePayStructure.setId(_id);
                listMemberModel.setName(name);
                listMemberModel.setType(type);
                listMemberModel.setDate(date);
                listMemberModel.setNumber(number);

                int affectedRows = dbManagerAll.updateMemberList(listMemberModel);

//                    binding.idListMemberInputEditTextName.setText("");
//                    binding.idPayInputEditTextType.setText("");
//                    binding.idListMemberInputEditTextDate.setText("");
//                    binding.idMemberListInputEditTextMobileNumber.setText("");
                Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();

            }
//                returnHome();
        });

        binding.buttonDeleteDb.setOnClickListener(v -> {

            String name,type,date,number;
            name = Objects.requireNonNull(binding.editTextName.getText()).toString();
            type = Objects.requireNonNull(binding.editTextMemberType.getText()).toString();
            number = Objects.requireNonNull((binding.editTextNumber).getText()).toString();
            date = Objects.requireNonNull((binding.editTextDate).getText()).toString();

            if (name.equals("") || type.equals("") || date.equals("") || number.equals("")){
                Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
            }else {
                dbManagerAll.deleteListMemberOneRow(listMemberModel.id);
//                    binding.idListMemberInputEditTextName.setText("");
//                    binding.idPayInputEditTextType.setText("");
//                    binding.idMemberListInputEditTextMobileNumber.setText("");
//                    binding.idListMemberInputEditTextDate.setText("");
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
//                returnHome();
        });

        binding.buttonDeleteDb.setOnClickListener(v -> {
            String name,type,date,number;
            name = Objects.requireNonNull(binding.editTextName.getText()).toString();
            type = Objects.requireNonNull(binding.editTextMemberType.getText()).toString();
            number = Objects.requireNonNull((binding.editTextNumber).getText()).toString();
            date = Objects.requireNonNull((binding.editTextDate).getText()).toString();

            if (name.equals("") || type.equals("") || date.equals("") || number.equals("")){
                Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
            }else {
                dbManagerAll.deleteListMemberOneRow(listMemberModel.id);
//                    binding.idPayInputEditTextName.setText("");
//                    binding.idPayInputEditTextAddress.setText("");
//                    binding.idPayInputEditTextAmount.setText("");
//                    binding.idPayInputEditTextRate.setText("");
//                    binding.idPayInputEditTextMobileNumber.setText("");
//                    binding.idPayInputEditTextDate.setText("");
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
//                returnHome();
        });
        return root;
    }
}