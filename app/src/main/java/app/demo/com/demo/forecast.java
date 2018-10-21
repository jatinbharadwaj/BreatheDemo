package app.demo.com.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class forecast extends AppCompatActivity {

    BarChart barChart1f,barChart2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        barChart1f=(BarChart) findViewById(R.id.barChart1f);
        barChart2f=(BarChart) findViewById(R.id.barChart2f);

        barChart1f.setDrawBarShadow(false);
        barChart1f.setDrawValueAboveBar(true);
        barChart1f.setMaxVisibleValueCount(50);
        barChart1f.setPinchZoom(false);
        barChart1f.setDrawGridBackground(true);

        barChart2f.setDrawBarShadow(false);
        barChart2f.setDrawValueAboveBar(true);
        //barChart1.setMaxVisibleValueCount(50);
        barChart2f.setPinchZoom(false);
        barChart2f.setDrawGridBackground(true);

        ArrayList<BarEntry> entries1=new ArrayList<>();
        entries1.add(new BarEntry(1,187f));
        entries1.add(new BarEntry(2,188f));
        entries1.add(new BarEntry(3,186f));
        entries1.add(new BarEntry(4,185f));
        entries1.add(new BarEntry(5,185f));
        entries1.add(new BarEntry(6,184f));
        entries1.add(new BarEntry(7,188f));
        entries1.add(new BarEntry(8,186f));
        entries1.add(new BarEntry(9,185f));
        entries1.add(new BarEntry(10,185f));
//        entries1.add(new BarEntry(11,180f));
//        entries1.add(new BarEntry(12,188f));
//        entries1.add(new BarEntry(13,186f));
//        entries1.add(new BarEntry(14,185f));
//        entries1.add(new BarEntry(15,185f));
//        entries1.add(new BarEntry(16,180f));
//        entries1.add(new BarEntry(17,188f));
//        entries1.add(new BarEntry(18,186f));
//        entries1.add(new BarEntry(19,185f));
//        entries1.add(new BarEntry(20,185f));
//        entries1.add(new BarEntry(21,180f));
//        entries1.add(new BarEntry(22,188f));
//        entries1.add(new BarEntry(23,186f));
//        entries1.add(new BarEntry(24,185f));


//        InputStream inputStream = getResources().openRawResource(R.raw.csvjson);
//        String json = new Scanner(inputStream).useDelimiter("\\A").next();
//        try{
//            JSONArray array = new JSONArray(json);
//            for (int i = 0; i < 24; i++) {
//                JSONObject object = array.getJSONObject(i);
//                double value=object.getDouble("AQI");
//                float fvalue=(float)value;
//                entries1.add(new BarEntry(i,fvalue));
//            }
//        }catch( JSONException e){
//            Toast.makeText(this, "Problem reading data for chart", Toast.LENGTH_SHORT).show();
//        }
//

        BarDataSet barDataSet1=new BarDataSet(entries1,"Today");
        BarDataSet barDataSet2=new BarDataSet(entries1,"Tomorrow");

        barDataSet1.setColors(getRed());
        barDataSet2.setColors(ColorTemplate.getHoloBlue());

        BarData data1=new BarData(barDataSet1);
        BarData data2=new BarData(barDataSet2);



        barChart1f.setData(data1);
        data1.setBarWidth(0.45f);
        barChart2f.setData(data2);
        data2.setBarWidth(0.45f);


    }

    public static int getRed(){
        return Color.rgb(255, 99, 71);
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
                Intent intent = new Intent(forecast.this, app.demo.com.demo.AccDetails.class);
                startActivity(intent);
                return true;

            case R.id.information_settings:
                Intent ntent = new Intent(forecast.this, app.demo.com.demo.Information.class);
                startActivity(ntent);
                return true;

            default:
                return false;
        }
    }
}
