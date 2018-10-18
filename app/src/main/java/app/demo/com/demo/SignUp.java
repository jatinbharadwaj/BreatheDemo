package app.demo.com.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//asks details and send to firebase at idChild
//NAME for name
//E-MAIL for email
//PASSWORD for password
//PHONE for phone
//after sign up move to LocationDetection(in actual interface)


public class SignUp extends AppCompatActivity {
    private EditText edtName,edtEmail,edtPhNo,edtPwd,edtRePwd;
    private Button btnsignup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idactivity);


        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhNo = (EditText) findViewById(R.id.edtPhNo);
        edtPwd = (EditText) findViewById(R.id.edtPwd);
        edtRePwd = (EditText) findViewById(R.id.edtRePwd);
        btnsignup2 = (Button) findViewById(R.id.btnsignup2);


        //BtnSignup Ka kya krna hai



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater =getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.profile_settings) {
        //   return true;
        //}
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {

            case R.id.profile_settings:
                Intent intent = new Intent(SignUp.this, app.demo.com.demo.AccDetails.class);
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }
}

   


