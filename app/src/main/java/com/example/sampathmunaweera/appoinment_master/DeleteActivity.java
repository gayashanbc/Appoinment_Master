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

public class DeleteActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        final Context contex;
        //DBAdapter dbAdapter;




        //get passed value from main activity
        Intent in = getIntent();
        int  selectedd = in.getIntExtra("selectedd",0);
        int selectedm = in.getIntExtra("selectedm",0);
        int selectedy = in.getIntExtra("selectedy",0);
        int dateint = selectedd+selectedm+selectedy;
        final String date= Integer.toString(dateint);

        Button btndelall = (Button)findViewById(R.id.deleAll);
        Button btndel = (Button)findViewById(R.id.dele);
        contex=this;


        String fullText= "";


        DBAdapter dbAdapter = new DBAdapter(contex);
        List<String> i = dbAdapter.serach(date);

        TextView display = (TextView)findViewById(R.id.move);
        for(int s=0;s <i.size();s+=4){
            fullText =  fullText +"\n" + i.get(s) + " " + i.get(s+1) + " " + i.get(s+2) + "\n";
        }
        display.setText(fullText);





        btndelall.setOnClickListener(new View.OnClickListener() {


            DBAdapter dbAdapter;



            @Override
            public void onClick(View v) {



                try{


                    dbAdapter = new DBAdapter(contex);
                    Boolean i = dbAdapter.deleteAllAppointments(date);


                    if(i == true){
                        Toast.makeText(DeleteActivity.this,"All Appoinment Deleted",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }

                }catch (SQLException e)
                {
                    Toast.makeText(DeleteActivity.this,"Error Occured",Toast.LENGTH_LONG).show();
                }

            }


        });


        //button dele solo
        btndel.setOnClickListener(new View.OnClickListener() {


            DBAdapter dbAdapter;


            @Override
            public void onClick(View v) {

                EditText getID   = (EditText)findViewById(R.id.input);
                dbAdapter = new DBAdapter(contex);
                dbAdapter = dbAdapter.open();
                String c_title = getID.getText().toString();
                System.out.println("C-Titile "+c_title);

                int val = Integer.parseInt(c_title);

                boolean i = dbAdapter.delesolo(val);

                if(i == true){
                    Toast.makeText(DeleteActivity.this,"Delete Successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }


            }

        });



    }

}
