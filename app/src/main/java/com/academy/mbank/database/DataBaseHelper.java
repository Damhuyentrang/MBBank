package com.academy.mbank.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.academy.mbank.TypeSwapMoney;
import com.academy.mbank.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String USERS_TABLE = "USERS_TABLE";

    public static final String COLUMN_ID = "ID";

    public static final String COLUMN_USERNAME = "USERNAME";

    public static final String COLUMN_PHONE = "PHONE";

    public static final String COLUMN_PASSWORD = "PASSWORD";

    public static final String COLUMN_EMAIL = "EMAIL";

    public static final String COLUMN_MONEY = "MONEY ";

    public static final String HISTORY_TABLE = "HISTORY_TABLE";

    public static final String COLUMN_TYPE = "TYPE";

    public static final String COLUMN_DATE = "DATE";

    public static final String COLUMN_ACCOUNTO = "ACCOUNTO";

    public static final String COLUMN_CONTENT = "CONTENT";

    public static final String COLUMN_PRICE = "PRICE";





    public DataBaseHelper(@Nullable Context context) {
        super(context, "myDataBase.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + USERS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT ,"
                + COLUMN_PHONE + " TEXT ,"
                + COLUMN_PASSWORD + " TEXT ,"
                + COLUMN_EMAIL + " TEXT ,"
                + COLUMN_MONEY + " INTEGER)";

        sqLiteDatabase.execSQL(sql);
        String sql1 = "INSERT INTO "+USERS_TABLE+" ("+COLUMN_USERNAME+","+COLUMN_PHONE+","+COLUMN_PASSWORD+","+COLUMN_EMAIL+","+COLUMN_MONEY+") VALUES('NGUYEN VAN A','0774897878','123123','lehoang21998@gmail.com',100000000)";
        String sql2 = "INSERT INTO "+USERS_TABLE+" ("+COLUMN_USERNAME+","+COLUMN_PHONE+","+COLUMN_PASSWORD+","+COLUMN_EMAIL+","+COLUMN_MONEY+") VALUES('HOANG VAN THU','0774897879','123123','lehoang21998@gmail.com',100000000)";
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
         sql = "CREATE TABLE " + HISTORY_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + COLUMN_PHONE + " TEXT ,"
                 + COLUMN_TYPE + " TEXT ,"
                 + COLUMN_DATE + " TEXT ,"
                 + COLUMN_ACCOUNTO + " TEXT ,"
                 + COLUMN_CONTENT + " TEXT ,"
                 + COLUMN_PRICE + " INTEGER)";

        sqLiteDatabase.execSQL(sql);


        Log.d("DatabaseHelper", "Database created successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }





    public void addHistory(TypeSwapMoney object) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONE, object.getPhone());
        values.put(COLUMN_TYPE, object.getTypeSwap());
        values.put(COLUMN_DATE, object.getDate());
        values.put(COLUMN_ACCOUNTO, object.getAccountTo());
        values.put(COLUMN_CONTENT, object.getContent());
        values.put(COLUMN_PRICE, object.getPrice());
        // Inserting Row
        db.insert(HISTORY_TABLE, null, values);

        // Closing database connection
        db.close();
    }


    public List<TypeSwapMoney> getHistorys(String phone) {

        List<TypeSwapMoney> histories = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + HISTORY_TABLE + " WHERE "+ COLUMN_PHONE + " = '"+ phone +"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TypeSwapMoney object = new TypeSwapMoney();
                object.setId(Integer.parseInt(cursor.getString(0)));
                object.setPhone(cursor.getString(1));
                object.setTypeSwap(cursor.getString(2));
                object.setDate(cursor.getString(3));
                object.setAccountTo(cursor.getString(4));
                object.setContent(cursor.getString(5));
                object.setPrice(Integer.parseInt(cursor.getString(6)));
                // Adding note to list
                histories.add(object);
            } while (cursor.moveToNext());
        }

        // return note list
        return histories;
    }


    public User getUser(String phone) {


        // Select All Query
        String selectQuery = "SELECT  * FROM " + USERS_TABLE + " WHERE "+ COLUMN_PHONE + " = '"+ phone +"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPhone(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setMoney(Integer.parseInt(cursor.getString(5)));
                // Adding note to list
                return user;
            } while (cursor.moveToNext());
        }

        // return note list
        return null;
    }


    public long updateMoney(String phone, int money){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONEY, money);
        return db.update(USERS_TABLE, values, COLUMN_PHONE + " = ?", new String[] { phone });
    }

    public boolean checkLogin(String phone, String pass) {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + USERS_TABLE +" WHERE "
                + COLUMN_PHONE +" ='"+ phone+"'" +
                " AND "+ COLUMN_PASSWORD +" ='"+ pass+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        return cursor.moveToFirst();
        // return note list
    }
}
