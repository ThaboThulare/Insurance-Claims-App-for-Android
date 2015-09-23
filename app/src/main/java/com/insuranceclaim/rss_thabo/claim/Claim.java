package com.insuranceclaim.rss_thabo.claim;




import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class  Claim extends Activity implements OnClickListener {

    DBHelper db = new DBHelper(this);

    EditText editTextEmail, editTextSubject;
    TextView txtTextMessage;
    Button btnSend, btnAttachment;
    String email, subject, message, attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    String username;
    int columnIndex;
    Info info = new Info();
GetUsersLocation addressInfo = new GetUsersLocation();
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim);
        editTextEmail = (EditText) findViewById(R.id.editTextTo);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        txtTextMessage = (TextView) findViewById(R.id.editTextMessage);
        btnAttachment = (Button) findViewById(R.id.buttonAttachment);
        btnSend = (Button) findViewById(R.id.buttonSend);

        btnSend.setOnClickListener(this);
        btnAttachment.setOnClickListener(this);

        location = info.getLocation();

        txtTextMessage.setText( addressInfo.mylocation);


        //editTextEmail.setText(db.getRecord(username, "").toString());


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            /**
             * Get Path
             */
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            attachmentFile = cursor.getString(columnIndex);
            Log.e("Attachment Path:", attachmentFile);
            URI = Uri.parse("file://" + attachmentFile);
            cursor.close();
        }
    }

    @Override
    public void onClick(View v) {

        if (v == btnAttachment) {
           openGallery();
//String strDisplay = new String("");
          //  strDisplay= ""+ address.displayAddressOutput();
            Toast.makeText(getApplicationContext(),""+info.getLocation(), Toast.LENGTH_SHORT).show();

        }
        if (v == btnSend) {
            try {
                email = editTextEmail.getText().toString();
                subject = editTextSubject.getText().toString();
               // message = txtTextMessage.getText().toString();
                message = addressInfo.mylocation;
                final Intent emailIntent = new Intent(
                        android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                if (URI != null) {
                    emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                }

                this.startActivity(Intent.createChooser(emailIntent,
                        "Sending email..."));

            } catch (Throwable t) {
                Toast.makeText(this,
                        "Request failed try again: " + t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);

    }

}

/*public class Claim extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_claim, menu);
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
}*/
