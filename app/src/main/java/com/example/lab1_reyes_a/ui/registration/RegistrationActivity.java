package com.example.lab1_reyes_a.ui.registration;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lab1_reyes_a.R;
import com.example.lab1_reyes_a.db.AppDatabase;
import com.example.lab1_reyes_a.db.User;
import com.example.lab1_reyes_a.ui.results.Results;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity
        implements Registration1.OnFragmentInteractionListener,
                    Registration2.OnFragmentInteractionListener {

    private boolean isOnPage2;

    private AppDatabase mDb;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user = new User();

        final Button nextButton = findViewById(R.id.nextReg);
        final EditText passwordField = findViewById(R.id.password);
        final EditText cPasswordField = findViewById(R.id.cPassword);

//        TextWatcher afterTextChangedListener = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // ignore
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (passwordField.getText().toString().equals(cPasswordField.getText().toString())) {
//                    nextButton.setEnabled(true);
//                }
//            }
//        };
//        passwordField.addTextChangedListener(afterTextChangedListener);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnPage2) {
                    storeReg2Values();
                    Intent intent = new Intent(RegistrationActivity.this, Results.class);
                    intent.putExtra("email", user.email);

                    mDb = AppDatabase.getDatabase(getApplicationContext());
                    mDb.userModel().insertUser(user);

                    startActivity(intent);
                    isOnPage2 = false;
                } else { // Display registration Page 2

                    storeReg1Values();

                    FragmentTransaction transaction = RegistrationActivity.this.getSupportFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    Registration2 reg2Fragment = new Registration2();
                    transaction.replace(R.id.fragment_container, reg2Fragment, "REG2");
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();

                    isOnPage2 = true;
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
        EditText nText = findViewById(R.id.name);
        EditText eText = findViewById(R.id.email);
        EditText pText = findViewById(R.id.password);
        EditText cText = findViewById(R.id.cPassword);

        user.name = nText.getText().toString();
        user.email = eText.getText().toString();
        user.password = pText.getText().toString();
        user.cPassword = cText.getText().toString();
        user.gender = getSelectedRadio();
    }

    private void storeReg2Values() {
        Spinner dText = findViewById(R.id.degreeProgram);
        EditText yearText = findViewById(R.id.yearLevel);
        EditText bText = findViewById(R.id.birthday);

        user.degree = dText.getSelectedItem().toString();
        user.year = yearText.getText().toString();
        user.birthday = bText.getText().toString();
        user.hobbies = getSelectedCheckboxes();
    }

    private String getSelectedRadio() {
        RadioGroup gGrp = findViewById(R.id.gender);
        int id = gGrp.getCheckedRadioButtonId();
        View radioButton = gGrp.findViewById(id);
        int radioId = gGrp.indexOfChild(radioButton);
        RadioButton btn = (RadioButton) gGrp.getChildAt(radioId);
        return (String) btn.getText();
    }

    private String getSelectedCheckboxes() {
        CheckBox c1 = findViewById(R.id.cb_aircraft);
        CheckBox c2 = findViewById(R.id.cb_amRadio);
        CheckBox c3 = findViewById(R.id.cb_bell);
        CheckBox c4 = findViewById(R.id.cb_blacksmithing);
        CheckBox c5 = findViewById(R.id.cb_bridge);
        CheckBox c6 = findViewById(R.id.cb_candle);
        CheckBox c7 = findViewById(R.id.cb_crochet);
        CheckBox c8 = findViewById(R.id.cb_dumpster);
        List<String> h = new ArrayList<>();

        if (c1.isChecked()) h.add(c1.getText().toString());
        if (c2.isChecked()) h.add(c2.getText().toString());
        if (c3.isChecked()) h.add(c3.getText().toString());
        if (c4.isChecked()) h.add(c4.getText().toString());
        if (c5.isChecked()) h.add(c5.getText().toString());
        if (c6.isChecked()) h.add(c6.getText().toString());
        if (c7.isChecked()) h.add(c7.getText().toString());
        if (c8.isChecked()) h.add(c8.getText().toString());

        return TextUtils.join(", ", h);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
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
