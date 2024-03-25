package com.skc.committeemanagement.manage_SQLite_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database Information
    static final String DB_NAME = "SAMITI.DB";

    //database version
    static final int DB_VERSION = 29;

    //Table Name
    public static final String TABLE_NAME = "TABLE_NAME";
    public static final String TABLE_PAY = "PAY";
    public static final String TABLE_ADD_MEMBER = "ADD_MEMBER";
    public static final String TABLE_CANDIDATE = "CANDIDATE";
    public static final String TABLE_DASHBOARD = "DASHBOARD";
    public static final String TABLE_PAY_MONTHLY = "MONTHLY";
    public static final String TABLE_RECEIVE = "RECEIVE";
    public static final String TABLE_CASH = "CASH";
    public static final String TABLE_SETTING = "SETTING";

    //Table Columns Pay
    public static final String _ID = "_id";
    public static final String _ID_MEMBER = "_id_member";
    public static final String _ID_PAY_CANDIDATE = "_id_member";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String AMOUNT = "amount";
    public static final String DATE = "date";
    public static final String RDATE = "rdate";
    public static final String RATE = "rate";
    public static final String NUMBER = "number";
    public static final String INTEREST = "interest";
    public static final String TOTAL_AMOUNT = "total";
    public static final String TYPE = "type";
    public static final String BASE = "base";
    public static final String CASH_IN_HAND = "cash_in_hand";
    public static final String POSITIVE = "positive";
    public static final String NIGETIVE = "negetive";
    public static final String PAY_MONTHLY_AMOUNT = "pay_monthly_amount";
    public static final String SET_CASH_DEPOSIT = "set_cash_deposit";

    // Creating ALL table query
    private static final String CREATE_SETTING_TABLE = "create table " + TABLE_SETTING + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PAY_MONTHLY_AMOUNT + " TEXT NOT NULL, " + SET_CASH_DEPOSIT + " TEXT NOT NULL);";

    private static final String CREATE_CASH_TABLE = "create table " + TABLE_CASH + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BASE + " TEXT NOT NULL, " + CASH_IN_HAND + " TEXT NOT NULL, " + POSITIVE + " TEXT NOT NULL, " + NIGETIVE + " TEXT NOT NULL);";

    private static final String CREATE_PAY_TABLE = "create table " + TABLE_PAY + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " + AMOUNT + " TEXT NOT NULL, " + RATE + " TEXT NOT NULL, " + NUMBER + " TEXT NOT NULL, " +
            DATE + " TEXT NOT NULL);";

    private static final String CREATE_ADD_MEMBER_TABLE = "create table " + TABLE_ADD_MEMBER + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " + TYPE + " TEXT NOT NULL, " + DATE + " TEXT NOT NULL, " + NUMBER + " TEXT NOT NULL);";

    private static final String CREATE_CANDIDATE_TABLE = "create table " + TABLE_CANDIDATE + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " + AMOUNT + " TEXT NOT NULL, " + RATE + " TEXT NOT NULL, " + NUMBER + " TEXT NOT NULL, " +
            DATE + " TEXT NOT NULL);";

    private static final String CREATE_DASHBOARD_TABLE = "create table " + TABLE_DASHBOARD + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " + AMOUNT + " TEXT NOT NULL, " + RATE + " TEXT NOT NULL, " + NUMBER + " TEXT NOT NULL, " +
            DATE + " TEXT NOT NULL);";

    private static final String CREATE_PAY_MONTHLY_TABLE = "create table " + TABLE_PAY_MONTHLY + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " +
            AMOUNT + " TEXT NOT NULL, " + NUMBER + " TEXT NOT NULL);";

    private static final String CREATE_RECEIVE_TABLE = "create table " + TABLE_RECEIVE + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " + AMOUNT + " TEXT NOT NULL, " + INTEREST + " TEXT NOT NULL, " + TOTAL_AMOUNT + " TEXT NOT NULL, "+ RATE + " TEXT NOT NULL, " + NUMBER + " TEXT NOT NULL, " +
            DATE + " TEXT NOT NULL, " + RDATE + " TEXT NOT NULL);";



    //constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //this method is under the SQLiteOpenHelper class
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PAY_TABLE);
        db.execSQL(CREATE_DASHBOARD_TABLE);
        db.execSQL(CREATE_CANDIDATE_TABLE);
        db.execSQL(CREATE_ADD_MEMBER_TABLE);
        db.execSQL(CREATE_RECEIVE_TABLE);
        db.execSQL(CREATE_PAY_MONTHLY_TABLE);
        db.execSQL(CREATE_CASH_TABLE);
        db.execSQL(CREATE_SETTING_TABLE);
    }

    //this method is under the SQLiteOpenHelper class
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DASHBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANDIDATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_MEMBER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIVE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAY_MONTHLY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CASH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        onCreate(db);
    }

    /**
    public ArrayList<DBManager> fetch()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<DBManager> arrayList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            DBManager dbManager = new DBManager();
        }
    }**/
}
