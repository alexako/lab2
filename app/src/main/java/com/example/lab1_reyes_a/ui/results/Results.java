package com.example.lab1_reyes_a.ui.results;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.lab1_reyes_a.R;
import com.example.lab1_reyes_a.db.AppDatabase;
import com.example.lab1_reyes_a.db.User;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        AppDatabase mDb = AppDatabase.getDatabase(getApplicationContext());

        String email = getIntent().getStringExtra("email");
        System.out.println("intent param: " + email);
        User user = mDb.userModel().findUserByEmail(email);

        System.out.println("retrieved user: " + user.email);
        System.out.println("retrieved user: " + user.password);

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
    }
}
