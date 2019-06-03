package com.example.lab1_reyes_a.ui.registration;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.lab1_reyes_a.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity
        implements Registration1.OnFragmentInteractionListener,
                    Registration2.OnFragmentInteractionListener {

    private String name;
    private String email;
    private String password;
    private String cPassword;
    private String gender;
    private String degree;
    private String year;
    private String birthday;
    private List<String> hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Button nextButton = findViewById(R.id.nextReg);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create fragment and give it an argument specifying the article it should show
                Registration2 reg2Fragment = new Registration2();

                if (reg2Fragment.isVisible()) {
                    storeReg2Values();
//                    Intent intent = new Intent(this, ResultsAcivity.class);
//                    String input = "";
//                    intent.putExtra(EXTRA_MESSAGE, input);
//                    startActivities(intent);
                } else { // Display registration Page 2

                    storeReg1Values();

                    FragmentTransaction transaction = RegistrationActivity.this.getSupportFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, reg2Fragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
            }
        });

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            Registration1 reg1Fragment = new Registration1();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            reg1Fragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, reg1Fragment).commit();
        }
    }

    private void storeReg1Values() {
        name = "test name";
        email = "test@email.com";
        password = "pass123";
        cPassword = "pass123";
        gender = "male";
    }

    private void storeReg2Values() {
        degree = "CS";
        year = "2018/19";
        birthday = "02/11/1987";
        hobbies = new ArrayList<>();
        hobbies.add("painting");
        hobbies.add("sky diving");
        hobbies.add("playing guitar");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

        newFragment.setOnDateClickListener(new onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                TextView tv1 = findViewById(R.id.birthday);
                tv1.setText(datePicker.getYear()+"/"+datePicker.getMonth()+"/"+datePicker.getDayOfMonth());
            }

        });
    }
}
