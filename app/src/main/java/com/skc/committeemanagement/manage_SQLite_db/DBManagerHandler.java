package com.skc.committeemanagement.manage_SQLite_db;

import android.content.ContentValues;

public class DBManagerHandler {

    public ContentValues addContentValue(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put("a",name);
        return contentValues;
    }

}
