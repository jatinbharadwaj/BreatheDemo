package app.demo.com.demo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActualInterface extends AppCompatActivity {

    private Button btnForecast,btnSearchLoc,btnShopNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_interface);

        btnSearchLoc=(Button) findViewById(R.id.btnSearchLoc);
        btnForecast=(Button) findViewById(R.id.btnForecast);
        btnShopNow=(Button) findViewById(R.id.btnShop);

        btnForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to forecast activity
                Intent intentToForecast=new Intent(ActualInterface.this,forecast.class);
                startActivity(intentToForecast);
            }
        });

        btnShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to ShopNow activity
            }
        });
    }
}
