package com.example.android.habittrackerapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by miche on 7/11/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABITS_DATABASE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_HABIT_TIMES + " INTEGER NOT NULL" + ");";

        db.execSQL(SQL_CREATE_HABITS_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean insertData(String habitName, int times) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, habitName);
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT_TIMES, times);

        long dataInserted = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, contentValues);

        return dataInserted != -1;
    }

    public Cursor selectData() {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_TIMES};


        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, projection, null, null, null, null, null);

        return cursor;
    }

    public void deleteHabits() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(HabitContract.HabitEntry.TABLE_NAME, null, null);
    }
}