package com.skc.committeemanagement.manage_SQLite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.skc.committeemanagement.ui.CashStructure;
import com.skc.committeemanagement.ui.candidate.CandidateListStructure;
import com.skc.committeemanagement.ui.dashboard.DashboardStructure;
import com.skc.committeemanagement.ui.manage_pay.ManagePayStructure;
import com.skc.committeemanagement.ui.member_list.ListMemberModel;
import com.skc.committeemanagement.ui.pay_monthly.PayMonthlyStructure;
import com.skc.committeemanagement.ui.receive.ReceiveStructure;
import com.skc.committeemanagement.ui.setting.SettingStructure;


import java.util.ArrayList;
import java.util.List;

public class DBManagerAll {

    private DatabaseHelper dbHelper;
    private final Context context;
    private SQLiteDatabase database;

    //constructor
    public DBManagerAll(Context c) {
        context = c;
    }

    public void open() throws SQLException {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.getWritableDatabase();
        }
    }
    //close the database method
    public void close() {
        if (dbHelper != null) {dbHelper.close();}
    }

    //insert to the data use this methods
    public void insert(String name, String address, String amount, String rate, String number, String date) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.ADDRESS, address);
        contentValue.put(DatabaseHelper.AMOUNT, amount);
        contentValue.put(DatabaseHelper.RATE, rate);
        contentValue.put(DatabaseHelper.NUMBER, number);
        contentValue.put(DatabaseHelper.DATE, date);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public void settingInsert(String payMonthlyAmount) {
        open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.PAY_MONTHLY_AMOUNT, payMonthlyAmount);
        contentValue.put(DatabaseHelper.SET_CASH_DEPOSIT, "");
        database.insert(DatabaseHelper.TABLE_SETTING, null, contentValue);
    }

    public void cashInsert(String base, String cash, String positive, String nigetive) {
        open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.BASE, base);
        contentValue.put(DatabaseHelper.CASH_IN_HAND, cash);
        contentValue.put(DatabaseHelper.POSITIVE, positive);
        contentValue.put(DatabaseHelper.NIGETIVE, nigetive);
        database.insert(DatabaseHelper.TABLE_CASH, null, contentValue);
//        candidateInsert(name, address, number, date);
    }

    public void payInsert(String name, String address, String amount, String rate, String number, String date) {
        open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.ADDRESS, address);
        contentValue.put(DatabaseHelper.AMOUNT, amount);
        contentValue.put(DatabaseHelper.RATE, rate);
        contentValue.put(DatabaseHelper.NUMBER, number);
        contentValue.put(DatabaseHelper.DATE, date);
        database.insert(DatabaseHelper.TABLE_PAY, null, contentValue);
        cashInsert(number+"pay","", "", amount);
//        candidateInsert(name, address, number, date);
    }

    public void receiveInsert(ReceiveStructure receiveStructure) {
        open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, receiveStructure.getName());
        contentValue.put(DatabaseHelper.ADDRESS, receiveStructure.getAddress());
        contentValue.put(DatabaseHelper.AMOUNT, receiveStructure.getPrincipal_amount());
        contentValue.put(DatabaseHelper.INTEREST,receiveStructure.getInterest());
        contentValue.put(DatabaseHelper.TOTAL_AMOUNT,receiveStructure.getTotal_amount());
        contentValue.put(DatabaseHelper.RATE, receiveStructure.getRate());
        contentValue.put(DatabaseHelper.NUMBER, receiveStructure.getNumber());
        contentValue.put(DatabaseHelper.DATE, receiveStructure.getDate());
        contentValue.put(DatabaseHelper.RDATE, receiveStructure.getReceiveDate());
        database.insert(DatabaseHelper.TABLE_RECEIVE, null, contentValue);
        deleteManagePayOneRow(receiveStructure.getId());

        cashInsert(receiveStructure.getNumber(),"",receiveStructure.getTotal_amount(),"");
//        candidateInsert(name, address, number, date);
    }

    public void addMemberInsert(String name, String type, String date, String number) {
        open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.TYPE, type);
        contentValue.put(DatabaseHelper.DATE, date);
        contentValue.put(DatabaseHelper.NUMBER, number);
        database.insert(DatabaseHelper.TABLE_ADD_MEMBER, null, contentValue);

    }

    public void candidateInsert(String name, String address, String number, String date) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.ADDRESS, address);
        contentValue.put(DatabaseHelper.NUMBER, number);
        contentValue.put(DatabaseHelper.DATE, date);
    }

    public void monthlyInsert(String name, String amount, String number) {
        open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.AMOUNT, amount);
        contentValue.put(DatabaseHelper.NUMBER, number);
        database.insert(DatabaseHelper.TABLE_PAY_MONTHLY, null, contentValue);
        cashInsert(number+"mp",amount,"","");
    }

    public void receiveInsert(String name, String princiapal_amount, String date, String intrest, String total_amount) {
        open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.ADDRESS, princiapal_amount);
        contentValue.put(DatabaseHelper.DATE, date);
        contentValue.put(DatabaseHelper.AMOUNT, intrest);
        contentValue.put(DatabaseHelper.RATE, total_amount);
        database.insert(DatabaseHelper.TABLE_RECEIVE, null, contentValue);
    }

    //for this method will be use for Reading database and table
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.ADDRESS, DatabaseHelper.AMOUNT, DatabaseHelper.RATE,
                DatabaseHelper.NUMBER, DatabaseHelper.DATE};

//        database.execSQL("");
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    //list of member return this function
    public SettingStructure fetchSetting() {
        open();
        SettingStructure settingStructure = new SettingStructure();

//        database.execSQL("");

//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_SETTING,null)) {
            while (cursor.moveToNext()) {
                settingStructure.setId(cursor.getInt(0));
                settingStructure.setPayMonthlyAmount(cursor.getString(1));
//                settingStructure.setCashDeposit(cursor.getString(2));
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return settingStructure;
    }

    public ArrayList<CashStructure> fetchCash() {
        open();
        ArrayList<CashStructure> cashStructureArrayList = new ArrayList<>();

//        database.execSQL("");

//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CASH,null)) {
            while (cursor.moveToNext()) {
                CashStructure cashStructure = new CashStructure();
                cashStructure.setBase(cursor.getString(1));
                cashStructure.setCash(cursor.getString(2));
                cashStructure.setPositive(cursor.getString(3));
                cashStructure.setNigetive(cursor.getString(4));
                cashStructureArrayList.add(cashStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return cashStructureArrayList;
    }

    public ArrayList<ListMemberModel> fetchMemberList() {
        open();
        ArrayList<ListMemberModel> memberModelArrayList = new ArrayList<>();

//        database.execSQL("");

//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_ADD_MEMBER,null)) {
            
                while (cursor.moveToNext()) {
                    ListMemberModel memberModel = new ListMemberModel();
                    memberModel.setId(cursor.getInt(0));
                    memberModel.setName(cursor.getString(1));
                    memberModel.setType(cursor.getString(2));
                    memberModel.setDate(cursor.getString(3));
                    memberModel.setNumber(cursor.getString(4));
                    memberModelArrayList.add(memberModel);
                }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return memberModelArrayList;
    }

    public ArrayList<PayMonthlyStructure> fetchPayMonthly() {
        open();
        ArrayList<PayMonthlyStructure> payMonthlyStructureArrayList = new ArrayList<>();

//        database.execSQL("");

//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PAY_MONTHLY,null)) {
            while (cursor.moveToNext()) {
                PayMonthlyStructure payMonthlyStructure = new PayMonthlyStructure();
                payMonthlyStructure.setName(cursor.getString(1));
                payMonthlyStructure.setNumber(cursor.getString(2));
                payMonthlyStructureArrayList.add(payMonthlyStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return payMonthlyStructureArrayList;
    }

    public List<PayMonthlyStructure> fetchPayMonthlyListFromMember() {
        open();
        String[] columns = new String[] { DatabaseHelper.NAME,DatabaseHelper.NUMBER};

        List<PayMonthlyStructure> payMonthlyStructureList = new ArrayList<>();

//        database.execSQL("");
//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_ADD_MEMBER,null)) {

            while (cursor.moveToNext()) {
                PayMonthlyStructure payMonthlyStructure = new PayMonthlyStructure();
                payMonthlyStructure.setName(cursor.getString(1));
                payMonthlyStructure.setNumber(cursor.getString(4));
                payMonthlyStructureList.add(payMonthlyStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return payMonthlyStructureList;
    }

    public ArrayList<ManagePayStructure> fetchManagePayListFromPay() {
        open();
        String[] columns = new String[] { DatabaseHelper._ID,DatabaseHelper.NAME,DatabaseHelper.ADDRESS,DatabaseHelper.AMOUNT,DatabaseHelper.RATE, DatabaseHelper.NUMBER,DatabaseHelper.DATE};

        ArrayList<ManagePayStructure> managePayStructureArrayList = new ArrayList<>();

//        database.execSQL("");
//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PAY,null)) {

            while (cursor.moveToNext()) {
                ManagePayStructure managePayStructure = new ManagePayStructure();
                managePayStructure.setId(cursor.getInt(0));
                managePayStructure.setName(cursor.getString(1));
                managePayStructure.setAddress(cursor.getString(2));
                managePayStructure.setAmount(cursor.getString(3));
                managePayStructure.setRate(cursor.getString(4));
                managePayStructure.setNumber(cursor.getString(5));
                managePayStructure.setDate(cursor.getString(6));
                managePayStructureArrayList.add(managePayStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return managePayStructureArrayList;
    }

    public ArrayList<DashboardStructure> fetchUseForDashboard() {
        open();
        String[] columns = new String[] { DatabaseHelper._ID,DatabaseHelper.NAME,DatabaseHelper.ADDRESS,DatabaseHelper.AMOUNT,DatabaseHelper.RATE, DatabaseHelper.NUMBER,DatabaseHelper.DATE};

        ArrayList<DashboardStructure> managePayStructureArrayList = new ArrayList<>();

//        database.execSQL("");
//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PAY,null)) {

            while (cursor.moveToNext()) {
                DashboardStructure managePayStructure = new DashboardStructure();
//                managePayStructure.setId(cursor.getInt(0));
                managePayStructure.setCash(cursor.getString(1));
                managePayStructure.setPayTotal(cursor.getString(2));
                    managePayStructure.setProfitTotal(cursor.getString(3));


//                managePayStructure.setCreditor(cursor.getString(4));
//                managePayStructure.setLoss(cursor.getString(5));
//                managePayStructure.setDate(cursor.getString(6));
                managePayStructureArrayList.add(managePayStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return managePayStructureArrayList;
    }

    public ArrayList<CandidateListStructure> fetchPayListNameNumber() {
        open();
        String[] columns = new String[] { DatabaseHelper._ID,DatabaseHelper.NAME,DatabaseHelper.ADDRESS,DatabaseHelper.AMOUNT,DatabaseHelper.RATE, DatabaseHelper.NUMBER,DatabaseHelper.DATE};

        ArrayList<CandidateListStructure> candidateListStructures = new ArrayList<>();

//        database.execSQL("");
//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PAY,null)) {

            while (cursor.moveToNext()) {
                CandidateListStructure candidateListStructure = new CandidateListStructure();
                candidateListStructure.setName(cursor.getString(1));
                candidateListStructure.setNumber(cursor.getString(5));
                candidateListStructures.add(candidateListStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return candidateListStructures;
    }

    public ArrayList<ReceiveStructure> fetchPayReceiveList() {
        open();
        String[] columns = new String[] { DatabaseHelper._ID,DatabaseHelper.NAME,DatabaseHelper.ADDRESS,DatabaseHelper.AMOUNT,DatabaseHelper.RATE, DatabaseHelper.NUMBER,DatabaseHelper.DATE,DatabaseHelper.RDATE};

        ArrayList<ReceiveStructure> receiveStructureArrayList = new ArrayList<>();

//        database.execSQL("");
//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PAY,null)) {

            while (cursor.moveToNext()) {
                ReceiveStructure receiveStructure = new ReceiveStructure();
                receiveStructure.setId(cursor.getInt(0));
                receiveStructure.setName(cursor.getString(1));
                receiveStructure.setAddress(cursor.getString(2));
                receiveStructure.setPrincipal_amount(cursor.getString(3));
                receiveStructure.setRate(cursor.getString(4));
                receiveStructure.setNumber(cursor.getString(5));
                receiveStructure.setDate(cursor.getString(6));
                receiveStructureArrayList.add(receiveStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return receiveStructureArrayList;
    }

    public ArrayList<ReceiveStructure> fetchReceiveList() {
        open();
        String[] columns = new String[] { DatabaseHelper._ID,DatabaseHelper.NAME,DatabaseHelper.ADDRESS,DatabaseHelper.AMOUNT,DatabaseHelper.RATE, DatabaseHelper.NUMBER,DatabaseHelper.DATE};

        ArrayList<ReceiveStructure> receiveStructureArrayList = new ArrayList<>();

//        database.execSQL("");
//        Cursor cursor = database.query(DatabaseHelper.TABLE_ADD_MEMBER, columns, null, null, null, null, null);
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_RECEIVE,null)) {

            while (cursor.moveToNext()) {
                ReceiveStructure receiveStructure = new ReceiveStructure();
                receiveStructure.setId(cursor.getInt(0));
                receiveStructure.setName(cursor.getString(1));
                receiveStructure.setAddress(cursor.getString(2));
                receiveStructure.setPrincipal_amount(cursor.getString(3));
                receiveStructure.setInterest(cursor.getString(4));
                receiveStructure.setTotal_amount(cursor.getString(5));
                receiveStructure.setRate(cursor.getString(6));
                receiveStructure.setNumber(cursor.getString(7));
                receiveStructure.setDate(cursor.getString(8));
                receiveStructure.setReceiveDate(cursor.getString(9));

                receiveStructureArrayList.add(receiveStructure);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context.getApplicationContext(), "Emty List", Toast.LENGTH_SHORT).show();
        }
        return receiveStructureArrayList;
    }

//    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,)
//    public ArrayList

    //this methods will be use for update any data table key id
    public int update(long _id, String name, String address, String amount, String rate, String number, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.ADDRESS, address);
        contentValues.put(DatabaseHelper.AMOUNT, amount);
        contentValues.put(DatabaseHelper.RATE, rate);
        contentValues.put(DatabaseHelper.NUMBER, number);
        contentValues.put(DatabaseHelper.DATE, date);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void updateSetting(String monthlyAmount) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PAY_MONTHLY_AMOUNT, monthlyAmount);
        contentValues.put(DatabaseHelper.SET_CASH_DEPOSIT, "");
        int i = database.update(DatabaseHelper.TABLE_SETTING, contentValues, DatabaseHelper._ID + " = " + 1, null);
    }

    public int updateCash(CashStructure cashStructure) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.BASE, cashStructure.getBase());
        contentValues.put(DatabaseHelper.CASH_IN_HAND, cashStructure.getCash());
        contentValues.put(DatabaseHelper.POSITIVE, cashStructure.getPositive());
        contentValues.put(DatabaseHelper.NIGETIVE, cashStructure.getNigetive());

        int i = database.update(DatabaseHelper.TABLE_CASH, contentValues, DatabaseHelper._ID + " = " + cashStructure.getBase(), null);
        return i;
    }

    public int updateManagePay(ManagePayStructure managePayStructure) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, managePayStructure.getName());
        contentValues.put(DatabaseHelper.ADDRESS, managePayStructure.getAddress());
        contentValues.put(DatabaseHelper.AMOUNT, managePayStructure.getAmount());
        contentValues.put(DatabaseHelper.RATE, managePayStructure.getRate());
        contentValues.put(DatabaseHelper.NUMBER, managePayStructure.getNumber());
        contentValues.put(DatabaseHelper.DATE, managePayStructure.getDate());

        int i = database.update(DatabaseHelper.TABLE_PAY, contentValues, DatabaseHelper._ID + " = " + managePayStructure.getId(), null);
        return i;
    }

    public int updateMemberList(ListMemberModel listMemberModel) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, listMemberModel.getName());
        contentValues.put(DatabaseHelper.TYPE, listMemberModel.getType());
        contentValues.put(DatabaseHelper.DATE, listMemberModel.getDate());
        contentValues.put(DatabaseHelper.NUMBER, listMemberModel.getNumber());

        int i = database.update(DatabaseHelper.TABLE_ADD_MEMBER, contentValues, DatabaseHelper._ID + " = " + listMemberModel.getId(), null);
        return i;
    }

    public int updateManageReceive(ReceiveStructure receiveStructure) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, receiveStructure.getName());
        contentValues.put(DatabaseHelper.ADDRESS, receiveStructure.getAddress());
        contentValues.put(DatabaseHelper.AMOUNT, receiveStructure.getPrincipal_amount());
        contentValues.put(DatabaseHelper.INTEREST, receiveStructure.getInterest());
        contentValues.put(DatabaseHelper.TOTAL_AMOUNT, receiveStructure.getTotal_amount());
        contentValues.put(DatabaseHelper.RATE, receiveStructure.getRate());
        contentValues.put(DatabaseHelper.NUMBER, receiveStructure.getNumber());
        contentValues.put(DatabaseHelper.DATE, receiveStructure.getDate());

        int i = database.update(DatabaseHelper.TABLE_RECEIVE, contentValues, DatabaseHelper._ID + " = " + receiveStructure.getId(), null);
        return i;
    }

    public int updatePay(long _id, String name, String address, String amount, String rate, String number, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.ADDRESS, address);
        contentValues.put(DatabaseHelper.AMOUNT, amount);
        contentValues.put(DatabaseHelper.RATE, rate);
        contentValues.put(DatabaseHelper.NUMBER, number);
        contentValues.put(DatabaseHelper.DATE, date);
        int i = database.update(DatabaseHelper.TABLE_PAY, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public int update1(long _id, String name, String amount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.AMOUNT, amount);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    //this method will be use for delete any data stored
    public void delete(int _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public void deleteCashOneRow(int _id) {
        open();
        database.delete(DatabaseHelper.TABLE_CASH, DatabaseHelper._ID + " = " + _id, null);
    }

    public void deleteManagePayOneRow(int _id) {
        open();
        database.delete(DatabaseHelper.TABLE_PAY, DatabaseHelper._ID + " = " + _id, null);
    }

    public void deleteListMemberOneRow(int id) {
        open();
        database.delete(DatabaseHelper.TABLE_ADD_MEMBER, DatabaseHelper._ID + " = " + id, null);
    }

    public void deleteManageReceiveOneRow(int _id) {
        open();
        database.delete(DatabaseHelper.TABLE_RECEIVE, DatabaseHelper._ID + " = " + _id, null);
    }

    public ArrayList<PayStructure> getPay()
    {
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME,null);

        ArrayList<PayStructure> arrPayTable = new ArrayList<>();

        while (cursor.moveToNext())
        {
            PayStructure model = new PayStructure();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.address = cursor.getString(2);
            model.amount = cursor.getString(3);
            model.rate = cursor.getString(4);
            model.number = cursor.getString(5);
            model.date = cursor.getString(6);

            arrPayTable.add(model);
        }

        return arrPayTable;
    }
}