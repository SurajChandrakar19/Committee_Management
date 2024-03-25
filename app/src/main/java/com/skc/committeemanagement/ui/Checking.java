package com.skc.committeemanagement.ui;

import com.skc.committeemanagement.login_tab.SignupDataHolder;
import com.skc.committeemanagement.ui.pay_monthly.PayMonthlyStructure;
import com.skc.committeemanagement.ui.receive.ReceiveStructure;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Checking {

    public static boolean blankCheck(String s)
    {
        return s.trim().equals("");
    }

    public static String memberTotalCollection(ArrayList<PayMonthlyStructure> payMonthlyStructureArrayList){
        long amount = 0;
        int length = payMonthlyStructureArrayList.size();
        if (length == 0){ return "0";}
        else {
            for (int i = 0; i < length; i++) {
                amount = amount + Long.parseLong(payMonthlyStructureArrayList.get(i).getNumber());
            }
        }
        return String.valueOf(amount);
    }

    public static String receivePending(ArrayList<ReceiveStructure> receiveStructureArrayList)
    {
        long amount = 0;

        int lenght  = receiveStructureArrayList.size();
        if (lenght == 0)
        {
            return "0";
        }
        else {
            for (int i = 0; i < lenght; i++) {
                String temp = receiveStructureArrayList.get(i).getPrincipal_amount();
                amount = amount + Long.parseLong(temp);
            }
        }
        return String.valueOf(amount);
    }

    public static String calculateInterest(String amount, String payDate, String receiveDAte,String rate)
    {
       try{ String interest = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//        String simpleDateFormat = SimpleDateFormat.getDateInstance().format("dd/MM/yyyy");

            int principalAmount = Integer.parseInt(amount);
            int rateForCalculation = Integer.parseInt(rate);

            LocalDate localDate = LocalDate.parse(payDate,dateTimeFormatter);
            LocalDate localDate1 = LocalDate.parse(receiveDAte,dateTimeFormatter);
            //store defrence day with calculation
            long month = ChronoUnit.MONTHS.between(localDate,localDate1);
//            String totalDay = Long.toString(day);

                if (month == 0){
                    long m = Integer.toUnsignedLong((principalAmount * rateForCalculation) / 100);
                    return String.valueOf(m);
                }
                else {
                    long m = Integer.toUnsignedLong(principalAmount * rateForCalculation / 100);
                    long calculate = m * month;
                    interest = String.valueOf(calculate);
                }
        }
        return interest;}
       catch (Exception e){
           return "0";
       }
    }
    //for use select date receive date

//    public static String getDate(Context context){
//        Calendar calendarDate = Calendar.getInstance();
//        String[] date = new String[1];
//        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int y, int m, int d) {
//                if (view.getDayOfMonth() < 10 || view.getMonth() < 10){
//                    date[0] = "0" + view.getDayOfMonth() + "/" + "0" + (view.getMonth()+1) + "/" + view.getYear();
//                }else {
//                    date[0] = view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear();
//                }
//            }
//        },calendarDate.get(Calendar.YEAR),calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show();
//        return date[0];
//    }

    public static long getCashInHand(ArrayList<CashStructure> cashStructureArrayList){
        long cashInHand = 0;
        long positive = 0;
        long nagetive = 0;

        int lenght  = cashStructureArrayList.size();
        for (int i = 0; i< lenght; i++)
        {
            String temp = cashStructureArrayList.get(i).getCash();
            if (!temp.equals("")){cashInHand = cashInHand + Long.parseLong(temp);}

            String temp1 = cashStructureArrayList.get(i).getPositive();
            if (!temp1.equals("")) positive = positive + Long.parseLong(temp1);

            String temp2 = cashStructureArrayList.get(i).getNigetive();
            if (!temp2.equals("")){nagetive = nagetive + Long.parseLong(temp2);}
        }

        //        Iterator<CashStructure> itr = cashStructureArrayList.iterator();
//
//        while (itr.hasNext()){
//            if (itr.next().getCash().equals("")){}
//            else {cashInHand = cashInHand +Long.parseLong(itr.next().getCash());}
//        }
        return (cashInHand + positive) - nagetive;
    }

    public static void uploadToData(String name, String address, String mobileNumber, String password)
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference(SignupDataHolder.class.getSimpleName());

        SignupDataHolder holder = new SignupDataHolder(address,mobileNumber,name,password);
        root.push().setValue(holder);

    }

//    public static String getPayTotal(){
//
//    }
}
