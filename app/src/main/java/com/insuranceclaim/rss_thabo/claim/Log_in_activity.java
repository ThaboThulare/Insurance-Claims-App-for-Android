package com.insuranceclaim.rss_thabo.claim;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Log_in_activity extends ActionBarActivity{


    DBHelper db = new DBHelper(this);





    Button btnSignIN;
    Button btnRegister;

    EditText edtEmail;
    EditText edtpassword;

    @Override

    public   void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_activity);

        btnRegister = (Button)findViewById(R.id.btnRegister);

        btnSignIN = (Button)findViewById(R.id.btnSignIn);




        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Log_in_activity.this, Register.class);
                startActivity(i);
                finish();
            }
        });




        btnSignIN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                edtEmail = (EditText) findViewById(R.id.edtEnail);
                edtpassword = (EditText) findViewById(R.id.edtPassword);

                try {

                    List<String> userRecord = db.getRecord(edtEmail.getEditableText().toString(), edtpassword.getEditableText().toString());

                    Log.d("Login onClick()", String.valueOf(userRecord.size()));

                    Dialog dialog = new Dialog(view.getContext());

                    TextView tv = new TextView(getApplicationContext());

                    tv.setText("Policy Number : " + userRecord.get(0) +" \n" + "Name  : " + userRecord.get(1) + "\n" + "Phone number : " + userRecord.get(2)

                            + "\n" + "licence : " + userRecord.get(3)
                            + "\n" + "Address : " + userRecord.get(4) );

                    dialog.setContentView(tv);

                    dialog.setTitle("User Details");

                    dialog.show();

                    dialog.setCanceledOnTouchOutside(true);

                    Intent i = new Intent(Log_in_activity.this, Home.class);
                    startActivity(i);
                    finish();

                }



                catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Login Failed, Record Not Found", Toast.LENGTH_SHORT).show();


                    e.printStackTrace();

                }




            }



        });
}

}
