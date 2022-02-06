package com.example.logindatabase.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.logindatabase.Util.Util;
import com.example.logindatabase.model.User;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context){
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating the table
//
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME +" (" + Util.KEY_ID + " integer primary key,"+Util.KEY_EMAIL+ " text, "
                + Util.KEY_PASSWORD + " text, " + Util.KEY_FIRST_NAME + " text, " + Util.KEY_LAST_NAME + " text,"
                + Util.KEY_TIMESTAMP +" text)";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(DROP_TABLE, new String[] {Util.DATABASE_NAME});

        //create table again after upgrading
        onCreate(db);

    }

    //adding users
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_EMAIL,user.getEmail());
        values.put(Util.KEY_PASSWORD, user.getPassword());
        values.put(Util.KEY_FIRST_NAME,user.getFirstName());
        values.put(Util.KEY_LAST_NAME, user.getLastName());
        values.put(Util.KEY_TIMESTAMP, user.getTimestamp());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);
        Log.d("Database", "addUser Method: " + "new user added");
        db.close();
    }

    //show all users
    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //select all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setEmail(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setFirstName(cursor.getString(3));
                user.setLastName(cursor.getString(4));
                user.setTimestamp(cursor.getString(5));

                //add user object to ArrayList
                userList.add(user);
            }while (cursor.moveToNext());
        }
        return userList;
    }


    //getting one contact
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_EMAIL, Util.KEY_PASSWORD,Util.KEY_FIRST_NAME,
                Util.KEY_LAST_NAME, Util.KEY_TIMESTAMP}, Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},null, null,null);

        if(cursor != null){
            cursor.moveToFirst();

        }

        User user = new User();
        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setEmail(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setFirstName(cursor.getString(3));
        user.setLastName(cursor.getString(4));
        user.setTimestamp(cursor.getString(5));

        return user;
    }


    public User getLastUser(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);

        User user = new User();

        if(cursor.moveToLast()){
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setFirstName(cursor.getString(3));
            user.setLastName(cursor.getString(4));
            user.setTimestamp(cursor.getString(5));
        }

        return user;
    }


    public void removeOneUser(int getID){
        SQLiteDatabase db = this.getWritableDatabase();
        String stringDBPosition = String.valueOf((getID));
        Log.d("Delete_one_DB", "stringPosition: " + stringDBPosition);
        db.execSQL("DELETE FROM " +Util.TABLE_NAME + " WHERE " + Util.KEY_ID +  " = '" + stringDBPosition + "'");
    }

    public int getID(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor getUserID = db.rawQuery("SELECT " + Util.KEY_ID + " from " + Util.TABLE_NAME +  " WHERE " +
                Util.KEY_EMAIL + " LIKE " + "'" + email + "'", null);
        if(getUserID != null && getUserID.moveToFirst()){
            return getUserID.getInt(0);
        } else {
            return 0;
        }
    }

}
