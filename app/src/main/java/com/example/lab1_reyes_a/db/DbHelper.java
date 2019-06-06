package com.example.lab1_reyes_a.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lab2-database";
    private static final String TABLE_USER = "User";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_USER +
                        " (id integer primary key," +
                        " name text," +
                        " email text," +
                        " gender text," +
                        " password text," +
                        " degree text," +
                        " year text," +
                        " birthday text," +
                        " hobbies text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("email", user.email);
        values.put("gender", user.gender);
        values.put("password", user.password);
        values.put("degree", user.degree);
        values.put("year", user.year);
        values.put("birthday", user.birthday);
        values.put("hobbies", user.hobbies);

        try{
            db.insert(TABLE_USER, null, values);
        }catch (Exception e){

            Log.d("e",e.toString());
        }
    }

    public List<User> GetAllUsers() {
        List<User> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USER,                 // The table to query
                null,               // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,           // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                null             // The sort order
        );

        while (cursor.moveToNext()) {
            User user = new User();
            user.id = cursor.getString(cursor.getColumnIndex("id"));
            user.name = cursor.getString(cursor.getColumnIndex("name"));
            user.email = cursor.getString(cursor.getColumnIndex("email"));
            user.gender = cursor.getString(cursor.getColumnIndex("gender"));
            user.password = cursor.getString(cursor.getColumnIndex("password"));
            user.degree = cursor.getString(cursor.getColumnIndex("degree"));
            user.year = cursor.getString(cursor.getColumnIndex("year"));
            user.birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            user.hobbies = cursor.getString(cursor.getColumnIndex("hobbies"));

            users.add(user);
        }
        cursor.close();

        return users;
    }

    public User getUserByEmail(String email) {

        User user = new User();

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "email = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                TABLE_USER,                 // The table to query
                null,               // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,           // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                null             // The sort order
        );

        while(cursor.moveToNext()) {
            user.id = cursor.getString(cursor.getColumnIndex("id"));
            user.name = cursor.getString(cursor.getColumnIndex("name"));
            user.email = cursor.getString(cursor.getColumnIndex("email"));
            user.gender = cursor.getString(cursor.getColumnIndex("gender"));
            user.password = cursor.getString(cursor.getColumnIndex("password"));
            user.degree = cursor.getString(cursor.getColumnIndex("degree"));
            user.year = cursor.getString(cursor.getColumnIndex("year"));
            user.birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            user.hobbies = cursor.getString(cursor.getColumnIndex("hobbies"));
        }
        cursor.close();

        return user;
    }
}
