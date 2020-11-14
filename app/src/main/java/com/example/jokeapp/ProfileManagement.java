package com.example.jokeapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileManagement extends AppCompatActivity {

    private Button update;
    private Button joke;
    private EditText uname, dob, pass;
    private RadioButton male, female;
    private DBHelper dbHelper;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        userId = intent.getStringExtra("id");

        uname = findViewById(R.id.user);
        dob = findViewById(R.id.date);
        pass = findViewById(R.id.word);
        update = findViewById(R.id.btnUpdate);
        joke = findViewById(R.id.button2);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFe);

        ArrayList<User> list =  dbHelper.readAllInfo(userId, null);

        for (User u : list){

            uname.setText(u.getUserName());
            pass.setText(u.getPassword());
            dob.setText(u.getDateOfBirth());

            if(u.getGender() != null){

                if(u.getGender().equals("Male")){

                    male.setChecked(true);
                }
                else
                {
                    female.setChecked(true);
                }
            }
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileManagement.this, EditProfile.class);
                intent.putExtra("id", userId);
                startActivity(intent);
            }
        });

        joke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileManagement.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}