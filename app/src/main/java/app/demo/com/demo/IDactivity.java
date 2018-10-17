package app.demo.com.demo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/////////////TASKS IN THIS ACTIVITY

//asks internet connection
//asks id input
//if id matches in firebase then go to sign up
//else create new id field in existing database
//databasereference to IdChild is passed on further so that we have id in entire app


public class IDactivity extends AppCompatActivity {

    private EditText edtID;
    private Button btnSubmit,btnBuyNow,btnHeatmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idactivity);

        edtID=(EditText) findViewById(R.id.edtID);
        btnSubmit=(Button) findViewById(R.id.btnSubmit);
        btnHeatmap=(Button) findViewById(R.id.btnHeatmap);
        btnBuyNow=(Button) findViewById(R.id.btnBuy);

        //change the rules for writing into database
        //also check if could database should be used


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toActualInterface=new Intent(IDactivity.this,ActualInterface.class);
                startActivity(toActualInterface);
            }
        });

        btnHeatmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to website
                //check
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to activity for shop now
                Intent toShopNow=new Intent(IDactivity.this,ShopNow.class);
                startActivity(toShopNow);
            }
        });
    }
}
