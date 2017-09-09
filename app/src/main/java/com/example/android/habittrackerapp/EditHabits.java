package com.example.android.habittrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittrackerapp.Data.HabitDbHelper;


/**
 * Created by miche on 7/12/2017.
 */

public class EditHabits extends AppCompatActivity {

    private EditText nameEditText, timesEditText;
    private Button addHabitButton, deleteHabitsButton;

    private HabitDbHelper habitDataBase;
    private String name;
    private int times;
    private FloatingActionButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_habits);

        nameEditText = (EditText) findViewById(R.id.name_editText);
        timesEditText = (EditText) findViewById(R.id.times_editText);

        backButton = (FloatingActionButton) findViewById(R.id.back_fab_button);

        addHabitButton = (Button) findViewById(R.id.add_habit_button);

        deleteHabitsButton = (Button) findViewById(R.id.drop_habit_button);

        habitDataBase = new HabitDbHelper(this);

        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString().trim();
                times = Integer.parseInt(timesEditText.getText().toString().trim());

                if (name.length() > 0 && times >= 0) {
                    habitDataBase.insertData(name, times);
                    nameEditText.setText(null);
                    timesEditText.setText(null);
                    Toast.makeText(getApplicationContext(), "Data added successfully !", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "No Enough Inputs to add !", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        deleteHabitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitDataBase.deleteHabits();
                Toast.makeText(getApplicationContext(), "Habits Cleaned Successfully !", Toast.LENGTH_LONG).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditHabits.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}