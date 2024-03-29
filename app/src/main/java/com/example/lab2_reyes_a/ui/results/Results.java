package com.example.lab2_reyes_a.ui.results;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab2_reyes_a.R;
import com.example.lab2_reyes_a.db.DbHelper;
import com.example.lab2_reyes_a.db.ExportCSV;
import com.example.lab2_reyes_a.db.User;
import com.example.lab2_reyes_a.ui.login.LoginActivity;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String email = getIntent().getStringExtra("email");
        User user = new DbHelper(this).getUserByEmail(email);


        TextView valName = findViewById(R.id.valName);
        TextView valEmail= findViewById(R.id.valEmail);
        TextView valPass = findViewById(R.id.valPass);
        TextView valGender = findViewById(R.id.valGender);
        TextView valDegree = findViewById(R.id.valDegree);
        TextView valYear = findViewById(R.id.valYear);
        TextView valBirthday = findViewById(R.id.valBirthday);
        TextView valHobbies = findViewById(R.id.valHobbies);

        String pass = "";
        if (!user.password.isEmpty()) {
            for (int i = 0; i < user.password.length(); i++) {
                pass += "*";
            }
        }

        valName.setText(user.name);
        valEmail.setText(user.email);
        valPass.setText(pass);
        valGender.setText(user.gender);
        valDegree.setText(user.degree);
        valYear.setText(user.year);
        valBirthday.setText(user.birthday);
        valHobbies.setText(user.hobbies);

        new ExportCSV("users.csv", new DbHelper(this).GetAllUsers());

        Button signInButton = findViewById(R.id.signIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
