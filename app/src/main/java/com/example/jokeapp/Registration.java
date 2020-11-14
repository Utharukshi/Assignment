package com.example.jokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    private Button register,reset;
    private EditText uname, dob, pass;
    private RadioGroup radioGroup;
    private RadioButton male, female;
    private String gender;
    private DBHelper dbHelper;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        userId = intent.getStringExtra("id");

        Toast.makeText(Registration.this, userId, Toast.LENGTH_SHORT).show();

        uname = findViewById(R.id.userName);
        dob = findViewById(R.id.dateOfB);
        pass = findViewById(R.id.psswrd);
        register = findViewById(R.id.btnRegister);
        reset = findViewById(R.id.btnReset);
        radioGroup = findViewById(R.id.radio);
        male = findViewById(R.id.maleR);
        female = findViewById(R.id.femaleR);

//        ArrayList<User> list =  dbHelper.readAllInfo(userId, null);
//
//        if(!list.isEmpty()){
//
//            for (User u : list){
//
//                uname.setText(u.getUserName());
//                pass.setText(u.getPassword());
//                dob.setText(u.getDateOfBirth());
//
//                if(u.getGender() != null){
//
//                    if(u.getGender().equals("Male")){
//
//                        male.setChecked(true);
//                    }
//                    else
//                    {
//                        female.setChecked(true);
//                    }
//                }
//            }
//        }
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int view) {
//
//                if(view == R.id.femaleR){
//
//                    gender = "Female";
//                }
//                else{
//
//                    gender = "Male";
//                }
//            }
//        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(uname.getText()) || TextUtils.isEmpty(pass.getText()) || TextUtils.isEmpty(dob.getText())) {

                    Toast.makeText(Registration.this, "Fill All The Details.!", Toast.LENGTH_SHORT).show();

                }
                else {
                String userName = uname.getText().toString();
                String date = dob.getText().toString();
                String pwrd = pass.getText().toString();
                if (female.isChecked()) {

                    gender = "Female";
                } else {

                    gender = "Male";
                }

                int count = (int) dbHelper.addInfo(userName, pwrd, date, gender);

                if (count > 0) {

                    Toast.makeText(Registration.this, "User Registered!", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(Registration.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname.setText("");
                dob.setText("");
                pass.setText("");
                male.setChecked(false);
                female.setChecked(false);
                uname.requestFocus();
            }
        });
    }
}
