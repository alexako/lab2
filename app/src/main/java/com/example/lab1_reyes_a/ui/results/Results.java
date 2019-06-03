package com.example.lab1_reyes_a.ui.results;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lab1_reyes_a.R;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String gender = getIntent().getStringExtra("gender");
        String degree = getIntent().getStringExtra("degree");
        String year = getIntent().getStringExtra("year");
        String birthday = getIntent().getStringExtra("birthday");
        String hobbies = getIntent().getStringExtra("hobbies");

        TextView valName = findViewById(R.id.valName);
        TextView valEmail= findViewById(R.id.valEmail);
        TextView valPass = findViewById(R.id.valPass);
        TextView valGender = findViewById(R.id.valGender);
        TextView valDegree = findViewById(R.id.valDegree);
        TextView valYear = findViewById(R.id.valYear);
        TextView valBirthday = findViewById(R.id.valBirthday);
        TextView valHobbies = findViewById(R.id.valHobbies);

        String pass = "";
        for (int i = 0; i < password.length(); i++) {
            pass += "*";
        }

        valName.setText(name);
        valEmail.setText(email);
        valPass.setText(pass);
        valGender.setText(gender);
        valDegree.setText(degree);
        valYear.setText(year);
        valBirthday.setText(birthday);
        valHobbies.setText(hobbies);
    }
}
