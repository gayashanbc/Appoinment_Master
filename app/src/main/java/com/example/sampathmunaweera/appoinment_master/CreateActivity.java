package com.example.sampathmunaweera.appoinment_master;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {


    DBAdapter dbAdapter;
    EditText getTitle;
    EditText getDec;
    Context contex;
    TimePicker tp;
    int selectedd;
    String StrDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        contex = this;


        Button btnsave = (Button) findViewById(R.id.save);

        getTitle = (EditText) findViewById(R.id.title);
        getDec = (EditText) findViewById(R.id.dec);
        tp = (TimePicker) findViewById(R.id.time);


        //get passed value from main activity
        Intent in = getIntent();
        selectedd = in.getIntExtra("selectedd", 0);
        int selectedm = in.getIntExtra("selectedm", 0);
        int selectedy = in.getIntExtra("selectedy", 0);


        if (selectedm < 10) {
            String m = "0" + Integer.toString(selectedm);
            System.out.println("month formatted" + m);
            int selectedm1 = Integer.parseInt(m);
            System.out.println("month " + selectedm1);


        }

        //  int date = selectedd + selectedm + selectedy;
        // String c_date = Integer.toString(date);
        StrDate = selectedy + "-" + selectedm + "-" + selectedd;

        Log.i("Date Recieved", StrDate);


//        if (StrDate == "0") {
//            Calendar c = Calendar.getInstance();
//            System.out.println("Current time => " + c.getTime());
//
//            SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
//            String formattedDate = df.format(c.getTime());
//            System.out.println("formattedDate" + formattedDate);
//            StrDate = formattedDate;
//        }

        System.out.println("%%%%%%%%%%%%%" + selectedd + selectedm + selectedy);

        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v("EditText", getTitle.getText().toString());
                Log.v("EditText", getDec.getText().toString());
                //             Log.v("TimePicker", tp.getHour()+":");
                Log.v("TimePicker", tp.getCurrentHour() + ":" + tp.getCurrentMinute());
                String c_title = getTitle.getText().toString();
                String c_dec = getDec.getText().toString();
                StrDate += " " + tp.getCurrentHour() + ":" + tp.getCurrentMinute() + ":00";
                Log.i("DateTime", StrDate);

                try {

                    System.out.println("@@@@@@@@@" + c_title);
                    System.out.println("########" + c_dec);


                    dbAdapter = new DBAdapter(contex);
                    long i = dbAdapter.insertAppoinment(c_title, c_dec, StrDate);

                    System.out.println("-------------" + c_title);
                    System.out.println("------------" + c_dec);
                    System.out.println("------------" + StrDate);

                    if (i != -1) {
                        Toast.makeText(CreateActivity.this, "Appoinment Placed", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }

                } catch (SQLException e) {
                    Toast.makeText(CreateActivity.this, "Error Occured", Toast.LENGTH_LONG).show();
                }


            }

        });


    }
}
