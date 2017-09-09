package com.example.android.habittrackerapp.Data;

import android.provider.BaseColumns;

/**
 * Created by miche on 7/11/2017.
 */

public class HabitContract {

    public HabitContract() {

    }

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_HABIT_NAME = "name";

        public final static String COLUMN_HABIT_TIMES = "times";
    }
}