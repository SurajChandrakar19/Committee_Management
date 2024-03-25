package com.skc.committeemanagement.manage_SQLite_db;


import com.skc.committeemanagement.ui.manage_pay.ManagePayStructure;

import java.util.ArrayList;

public interface APIServices {

    ArrayList<ArrayList<ManagePayStructure>> getPayData();
}
