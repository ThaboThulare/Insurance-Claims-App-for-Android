package com.insuranceclaim.rss_thabo.claim;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {



    public static final String KEY_ROWID = "_id";
    public static final String KEY_USERNAME = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_POLICY_NUMBER = "pnumber";
    public static final String KEY_FULL_NAME = "name";
    public static final String KEY_PHONE_NUMBER = "phonenumber";
    public static final String KEY_LICENCE_NUMBER = "licencenumber";
    public static final String KEY_ADDRESS = "address";
    private static final String TAG = "DBAdapter";

    private static final String LOCATION = "currentLocation";


    private static final String DATABASE_NAME = "usersdb";
    private static final String DATABASE_INFO = "info";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 1;



    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String DATABASE_CREATE =
                "create table users (_id integer primary key autoincrement, "
                        + "email text not null, "
                        + "password text not null, "
                        + "pnumber text not null, "
                        + "name text not null, "
                        + "phonenumber text not null, "
                        + "licencenumber text not null, "
                        + "address text not null);";

        String INFO_CREATE = "create table info (_id integer primary key autoincrement, "
                                                  + "currentLocation text not null);";

        Log.d("onCreate()", DATABASE_CREATE);

        db.execSQL(DATABASE_CREATE);

        db.execSQL(INFO_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS members");
        onCreate(db);

    }
    public void addRecord(String email, String password, String pnumber, String name, String phonenumber, String licencenumber, String address ) {

        String insertSQL = "INSERT INTO " + DATABASE_TABLE + " (" + KEY_USERNAME + ", " + KEY_PASSWORD + " ," + KEY_POLICY_NUMBER + " ," + KEY_FULL_NAME + " ," + KEY_PHONE_NUMBER + " ," + KEY_LICENCE_NUMBER + " ," + KEY_ADDRESS +") "

                + "VALUES" + " ('" + email + "', '" + password + "', '" + pnumber + "', '" + name + "', '" + phonenumber + "', '" + licencenumber + "', '" + address +"')" ;

        Log.d("addRecord()", insertSQL);
        SQLiteDatabase dataBase = this.getWritableDatabase();

        dataBase.execSQL(insertSQL);

        dataBase.close();

    }

    public void addInfo(String currentl)
    {
          String insertInfo = "INSERT INTO" + DATABASE_INFO + " (" + LOCATION +") "
                                + "VALUES" + " ('" + currentl +"')" ;
    }

    public List<String>getInfo(String locatio)
    {
        List<String> locationlist = new ArrayList<String>();
        SQLiteDatabase dataBase = this.getReadableDatabase();

        String getinfo = "SELECT * FROM " + DATABASE_INFO + "'";
        Cursor cursor = dataBase.rawQuery(getinfo , null);
        cursor.moveToFirst();
        String location = cursor.getString(1);

        dataBase.close();

        return locationlist;
    }

    public List<String> getRecord(String emails, String passwords) {


        List<String> recordList = new ArrayList<String>();

        SQLiteDatabase dataBase = this.getReadableDatabase();

        String getSQL = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_USERNAME + " = '" + emails + "' AND " + KEY_PASSWORD + " = '" + passwords + "'";

        Cursor cursor = dataBase.rawQuery(getSQL , null);

        Log.d("getRecord()", getSQL + "##Count = " + cursor.getCount());

        cursor.moveToFirst();

        String pnumber = cursor.getString(1);

        String name = cursor.getString(2);

        String phonenumber = cursor.getString(3);

        String licencenumber= cursor.getString(4);
        String address = cursor.getString(5);

        Log.d("getRecord()", "Policy Number: " + pnumber + "Full name: " + name + "Phone Number: " + phonenumber + "Licence Number :" + licencenumber + "Address :" + address);

        recordList.add(pnumber);

        recordList.add(name);

        recordList.add(phonenumber);

        recordList.add(licencenumber);
        recordList.add(address);

        dataBase.close();

        return recordList;



    }
}
