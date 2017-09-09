package com.example.android.habittrackerapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.habittrackerapp.Data.HabitDbHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton addButton;
    ListView listView;
    HabitDbHelper habitDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitDataBase = new HabitDbHelper(this);

        addButton = (FloatingActionButton) findViewById(R.id.add_fab_button);

        listView = (ListView) findViewById(R.id.list_view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditHabits.class);
                startActivity(intent);
            }
        });
        addToListView();
    }

    public void addToListView(){

        ArrayList<String> habits = new ArrayList<>();
        Cursor data = habitDataBase.selectData();

        if(data.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Empty Database !",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                habits.add("Habit Name : " + data.getString(1) + "\n" + "Habit Times : " + data.getInt(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,habits);
                listView.setAdapter(listAdapter);
            }
        }
    }
}