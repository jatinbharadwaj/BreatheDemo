package app.demo.com.demo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                Intent intent = new Intent(ActualInterface.this, app.demo.com.demo.AccDetails.class);
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }
}
