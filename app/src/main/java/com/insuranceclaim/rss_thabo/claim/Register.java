package com.insuranceclaim.rss_thabo.claim;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends ActionBarActivity {


    Form form = new Form();
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button btnSignUp;
        final Button cancel;



        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        cancel = (Button)findViewById(R.id.cancel);

        final EditText  email = (EditText)findViewById(R.id.edtEnail);
        final EditText password= (EditText)findViewById(R.id.edtPassword);
        final EditText policyNumber = (EditText)findViewById(R.id.edtpolicyNunber);
        final EditText names = (EditText)findViewById(R.id.edtFullnames);
        final EditText phonenumber = (EditText)findViewById(R.id.edtPnumber);
        final EditText licenceNumber = (EditText)findViewById(R.id.edtLicence);
        final EditText address = (EditText)findViewById(R.id.edtAddress);




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                form.setKEY_USERNAME(email.getEditableText().toString());

                form.setKEY_PASSWORD(password.getEditableText().toString());

                form.setKEY_POLICY_NUMBER(policyNumber.getEditableText().toString());

                form.setKEY_FULL_NAME(names.getEditableText().toString());

                form.setKEY_PHONE_NUMBER(phonenumber.getEditableText().toString());

                form.setKEY_LICENCE_NUMBER(licenceNumber.getEditableText().toString());
                form.setKEY_ADDRESS(address.getEditableText().toString());


                db.addRecord(form.getKEY_USERNAME(), form.getKEY_PASSWORD(), form.getKEY_POLICY_NUMBER(),
                             form.getKEY_FULL_NAME(), form.getKEY_PHONE_NUMBER(), form.getKEY_LICENCE_NUMBER(),
                             form.getKEY_ADDRESS());

                Toast.makeText(getApplicationContext(), "Record Added!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Register.this, Log_in_activity.class);
                startActivity(i);
                finish();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Log_in_activity.class);
                startActivity(i);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
