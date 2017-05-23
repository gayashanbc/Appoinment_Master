package com.example.sampathmunaweera.appoinment_master;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sampathmunaweera on 4/11/17.
 */

public class MoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        final Context contex;

        String fullText= "";
        contex=this;
        String date="";

        DBAdapter dbAdapter = new DBAdapter(contex);
        List<String> i = dbAdapter.serach(date);

        TextView display = (TextView)findViewById(R.id.list);
        for(int s=0;s <i.size();s+=4){
            fullText =  fullText +"\n" + i.get(s) + " " + i.get(s+1) + " " + i.get(s+2) + "\n";
        }
        display.setText(fullText);
    }
}
