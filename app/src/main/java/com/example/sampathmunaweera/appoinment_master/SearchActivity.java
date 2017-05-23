package com.example.sampathmunaweera.appoinment_master;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sampathmunaweera on 4/10/17.
 */

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Context contex;

        //get passed value from main activity
        Intent in = getIntent();
        int selectedd = in.getIntExtra("selectedd", 0);
        int selectedm = in.getIntExtra("selectedm", 0);
        int selectedy = in.getIntExtra("selectedy", 0);
        int dateint = selectedd + selectedm + selectedy;
        final String date = Integer.toString(dateint);

        Button btnsrch = (Button) findViewById(R.id.searchbtn);
        contex = this;

        btnsrch.setOnClickListener(new View.OnClickListener() {


            DBAdapter dbAdapter;


            @Override
            public void onClick(View v) {

                EditText input = (EditText) findViewById(R.id.input);
                String typed = input.getText().toString();
                String fullText = "";

                dbAdapter = new DBAdapter(contex);
                List<String> i = dbAdapter.serach(typed);

                TextView display = (TextView) findViewById(R.id.displaylist);
                for (int s = 0; s < i.size(); s += 4) {
                    fullText = fullText + "\n" + i.get(s) + " " + i.get(s + 1) + " " + i.get(s + 2) + "\n";
                }
                display.setText(fullText);
            }


        });


    }

}
