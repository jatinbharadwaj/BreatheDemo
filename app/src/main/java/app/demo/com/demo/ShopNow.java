package app.demo.com.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ShopNow extends AppCompatActivity {

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_now);
        textView2 = (TextView) findViewById(R.id.textView2);

        // To read text file stored in asset

        String text=" ";
        try{
            InputStream is= getAssets().open("OurProduct.txt");
            int size=is.available();
            byte[] buffer =new byte[size];
            is.read(buffer);
            is.close();
            text=new String(buffer);

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        textView2.setText(text);

    }
}
