package com.example.lab2_reyes_a.db;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import java.io.FileOutputStream;
import java.util.List;

public class ExportCSV extends AppCompatActivity {

    private String filename;
    private String contents;
    private List<User> users;

    public ExportCSV(String filename, List<User> users) {
        this.filename = filename;
        this.users = users;
        this.contents = this.convertToCSV();
        this.export();
    }

    private String convertToCSV() {
        String contents = "";
        for (User u: this.users) {
            String[] row = {u.name, u.email, u.gender, u.degree, u.year, u.password};
            contents += TextUtils.join(", ", row) + "\n";
        }
        return contents;
    }

    private void export() {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(contents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
