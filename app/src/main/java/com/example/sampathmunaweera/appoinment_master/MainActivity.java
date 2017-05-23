package com.example.sampathmunaweera.appoinment_master;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int selectedy;
    int selectedm;
    int selectedd;
    final Context context = this;
    private EditText result;
    private String c_title = "";
    private String c_dec = "";
    private String c_time = "";
    private String etStr;
    private String etStr1;

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // this can be add to make calendar
        calendar = (CalendarView) findViewById(R.id.calendar);


        calendar.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(),
                        dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_LONG).show();

                selectedd = dayOfMonth;

                selectedm = month + 1;

                selectedy = year;

                Log.i("DatePicker", " Selected Date = " + selectedd + selectedm + selectedy);
            }
        });

        //exit
        Button btnexit = (Button) findViewById(R.id.exit);
        btnexit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // exit from app
                finish();
                System.exit(0);
            }

        });


        //create appoinment

        Button btncreate = (Button) findViewById(R.id.create);
        btncreate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent createIntend = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(createIntend);

                Intent in = new Intent(getApplicationContext(), CreateActivity.class);
                in.putExtra("selectedd", selectedd);
                in.putExtra("selectedm", selectedm);
                in.putExtra("selectedy", selectedy);
                startActivity(in);


            }


        });


        //delete appoinment

        Button btndel = (Button) findViewById(R.id.delete);
        btndel.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent deleteIntend = new Intent(getApplicationContext(), DeleteActivity.class);
                deleteIntend.putExtra("selectedd", selectedd);
                deleteIntend.putExtra("selectedm", selectedm);
                deleteIntend.putExtra("selectedy", selectedy);
                startActivity(deleteIntend);

            }


        });


        //search appoinment

        Button btnsearch = (Button) findViewById(R.id.search);
        btnsearch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent searchIntend = new Intent(getApplicationContext(), SearchActivity.class);
                searchIntend.putExtra("selectedd", selectedd);
                searchIntend.putExtra("selectedm", selectedm);
                searchIntend.putExtra("selectedy", selectedy);
                startActivity(searchIntend);

            }


        });

        //Move appoinment

        Button btnmove = (Button) findViewById(R.id.move);
        btnmove.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent moveIntend = new Intent(getApplicationContext(), MoveActivity.class);
                moveIntend.putExtra("selectedd", selectedd);
                moveIntend.putExtra("selectedm", selectedm);
                moveIntend.putExtra("selectedy", selectedy);
                startActivity(moveIntend);

            }


        });


    }


}
