package app.demo.com.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

////////TASKS IN THIS ACTIVITY
///SIGN IN ACTIVITY


//gets the database reference for idChild
//checks the entered Email and password existing at firebase database
//goes to LocationDetection(in actual interface only) after Email and Password match which is location(function btnSignIn)
//also we can do google sign in and Fb sign in


public class MainActivity extends AppCompatActivity {

    private ImageView imgProfile;
    private EditText edtEmail;
    private EditText edtPassword;
    //private Button btnSignUp;
    private Button btnSignIn;
    private Button btnGoogle;
    private Button btnFb;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Taking data from previous activity


        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnGoogle = (Button) findViewById(R.id.btnGoogle);
        btnFb = (Button) findViewById(R.id.btnFb);


        //signing in with Email and password
        btnSignIn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                           //  Toast.makeText(MainActivity.this, "Password matches", Toast.LENGTH_SHORT).show();
                                             Intent toLocation = new Intent(MainActivity.this, ActualInterface.class);
                                             startActivity(toLocation);
                                             finish();

                                         }

                                     });

                btnGoogle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {


                        Intent toLocation = new Intent(MainActivity.this, ActualInterface.class);
                        startActivity(toLocation);
                        finish();

                    }
                });


                        btnFb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                              //  Toast.makeText(MainActivity.this, "Password matches", Toast.LENGTH_SHORT).show();
                                Intent toLocation = new Intent(MainActivity.this, ActualInterface.class);
                                startActivity(toLocation);
                                finish();

                            }

                        }

     );






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

       // noinspection SimplifiableIfStatement
        if (id == R.id.information_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } //main_activity.java

}

