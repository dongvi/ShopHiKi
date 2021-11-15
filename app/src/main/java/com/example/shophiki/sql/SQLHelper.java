package com.example.shophiki.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shophiki.User;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Account.db";
    private static final String DB_NAME_TABLE_ACCOUNT = "Accounts";
    private static final String DB_ACCOUNT_USERNAME = "username";
    private static final String DB_ACCOUNT_PASSWORD = "password";
    private static final String DB_ACCOUNT_FULLNAME = "fullname";
    private static final String DB_ACCOUNT_GENDER = "gender";
    private static final String DB_ACCOUNT_BIRTHDAY = "birthday";
    private static final String DB_ACCOUNT_PHONENUMBER = "phoneNumber";
    private static final String DB_ACCOUNT_IMGUSER = "imgUser";
    private static final String DB_ACCOUNT_NICKNAME = "nickname";
    private static final String DB_ACCOUNT_ADDRESS = "address";
    private static final int DB_VERSION = 6;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DB_NAME_TABLE_ACCOUNT + " ( " +
                DB_ACCOUNT_USERNAME + " TEXT NOT NULL PRIMARY KEY, " +
                DB_ACCOUNT_PASSWORD + " TEXT, " +
                DB_ACCOUNT_FULLNAME + " TEXT, " +
                DB_ACCOUNT_GENDER + " TEXT, " +
                DB_ACCOUNT_BIRTHDAY + " TEXT, " +
                DB_ACCOUNT_PHONENUMBER + " TEXT, " +
                DB_ACCOUNT_IMGUSER + " TEXT, " +
                DB_ACCOUNT_NICKNAME + " TEXT, " +
                DB_ACCOUNT_ADDRESS + " TEXT " +
                " )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVS, int newVS) {
        if(oldVS < newVS){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME_TABLE_ACCOUNT);
            onCreate(sqLiteDatabase);
        }
    }

    public void add(String user, String pass, String name, String gender, String birthday, String phone, String imgUser){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(DB_ACCOUNT_USERNAME, user);
        contentValues.put(DB_ACCOUNT_PASSWORD, pass);
        contentValues.put(DB_ACCOUNT_FULLNAME, name);
        contentValues.put(DB_ACCOUNT_GENDER, gender);
        contentValues.put(DB_ACCOUNT_BIRTHDAY, birthday);
        contentValues.put(DB_ACCOUNT_PHONENUMBER, phone);
        contentValues.put(DB_ACCOUNT_IMGUSER, imgUser);

        sqLiteDatabase.insert(DB_NAME_TABLE_ACCOUNT, null, contentValues);
    }

    public void update(User user){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(DB_ACCOUNT_FULLNAME, user.getFullname());
        contentValues.put(DB_ACCOUNT_GENDER, user.getGender());
        contentValues.put(DB_ACCOUNT_BIRTHDAY, user.getBirthday());
        contentValues.put(DB_ACCOUNT_PHONENUMBER, user.getPhoneNumber());
        contentValues.put(DB_ACCOUNT_NICKNAME, user.getNickname());
        contentValues.put(DB_ACCOUNT_ADDRESS, user.getAddress());

        sqLiteDatabase.update(DB_NAME_TABLE_ACCOUNT, contentValues, "username=?", new String[]{user.getUsername()});
    }

    public void changePW(String user, String newPass){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(DB_ACCOUNT_PASSWORD, newPass);

        sqLiteDatabase.update(DB_NAME_TABLE_ACCOUNT, contentValues, "username=?", new String[]{user});
    }

    public List<User> getAllUser(){
        List<User> users = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(false, DB_NAME_TABLE_ACCOUNT, null, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            @SuppressLint("Range") String user = cursor.getString(cursor.getColumnIndex(DB_ACCOUNT_USERNAME));
            @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex(DB_ACCOUNT_PASSWORD));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DB_ACCOUNT_FULLNAME));
            @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex(DB_ACCOUNT_GENDER));
            @SuppressLint("Range") String birthday = cursor.getString(cursor.getColumnIndex(DB_ACCOUNT_BIRTHDAY));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(DB_ACCOUNT_PHONENUMBER));
            @SuppressLint("Range") String imgUser = cursor.getString(cursor.getColumnIndex(DB_ACCOUNT_IMGUSER));

            users.add(new User(user, pass, name, gender, birthday, phone, null));
        }
        return users;
    }
}
