package com.skc.committeemanagement.ui.pay_monthly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skc.committeemanagement.R;
import com.skc.committeemanagement.manage_SQLite_db.DBManagerAll;

import java.util.List;

public class PayMonthlyAdapter extends RecyclerView.Adapter<PayMonthlyAdapter.PayViewHolder> {

    List<PayMonthlyStructure> payMonthlyStructureList;
    DBManagerAll dbManagerAll;
    Context context;
    String monthlyAmount;

    public PayMonthlyAdapter(Context context, List<PayMonthlyStructure> payMonthlyStructureList, String monthlyAmount) {
        this.payMonthlyStructureList = payMonthlyStructureList;
        this.context = context;
        this.monthlyAmount = monthlyAmount;
    }

    @NonNull
    @Override
    public PayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pay_monthly_viewer_layout,parent,false);
        return (new PayViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull PayViewHolder holder, int position) {
            holder.name.setText(payMonthlyStructureList.get(position).name);
            holder.number.setText(payMonthlyStructureList.get(position).number);
            PayMonthlyStructure structure = payMonthlyStructureList.get(position);

            holder.paid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (monthlyAmount.equals("")) {
                            Toast.makeText(view.getContext(), "set your monthly paid amount", Toast.LENGTH_SHORT).show();
                        } else {
                            dbManagerAll = new DBManagerAll(view.getContext());
                            dbManagerAll.monthlyInsert(structure.name, monthlyAmount, structure.number);

                            Toast.makeText(view.getContext(), "Paid Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(view.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), structure.getName(), Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return payMonthlyStructureList.size();
    }

    public static class PayViewHolder extends RecyclerView.ViewHolder
    { TextView name;
        TextView number;
        Button paid;
        public PayViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.textView1);
            number = view.findViewById(R.id.textView2);
            paid = view.findViewById(R.id.id_pay_monthly_viewer_paid_button);
        }
    }
}
