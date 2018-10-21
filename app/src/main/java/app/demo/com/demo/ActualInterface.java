package app.demo.com.demo;



import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static android.graphics.Color.rgb;

public class ActualInterface extends AppCompatActivity implements OnMapReadyCallback,LocationListener {

    private Button btnForecast,btnSearchLoc,btnShopNow;
    BarChart barChart1,barChart2,barChart3;
    GoogleMap map;
    LocationManager locationManager;
    List<LatLng> list=null;
    HeatmapTileProvider mProvider;
    TileOverlay mOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_interface);

        btnSearchLoc=(Button) findViewById(R.id.btnSearchLoc);
        btnForecast=(Button) findViewById(R.id.btnForecast);
        btnShopNow=(Button) findViewById(R.id.btnShop);
        barChart1=(BarChart) findViewById(R.id.barChart1);
        barChart2=(BarChart) findViewById(R.id.barChart2);
        barChart3=(BarChart) findViewById(R.id.barChart3);


        barChart1.setDrawBarShadow(false);
        barChart1.setDrawValueAboveBar(true);
        barChart1.setMaxVisibleValueCount(50);
        barChart1.setPinchZoom(false);
        barChart1.setDrawGridBackground(true);

        barChart2.setDrawBarShadow(false);
        barChart2.setDrawValueAboveBar(true);
        //barChart1.setMaxVisibleValueCount(50);
        barChart2.setPinchZoom(false);
        barChart2.setDrawGridBackground(true);

        barChart3.setDrawBarShadow(false);
        barChart3.setDrawValueAboveBar(true);
        //barChart1.setMaxVisibleValueCount(50);
        barChart3.setPinchZoom(false);
        barChart3.setDrawGridBackground(true);


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

        BarDataSet barDataSet1=new BarDataSet(entries1,"Day Before Yesterday");
        BarDataSet barDataSet2=new BarDataSet(entries1,"Yesterday");
        BarDataSet barDataSet3=new BarDataSet(entries1,"Today");

        barDataSet1.setColors(getRed());
        barDataSet2.setColors(ColorTemplate.getHoloBlue());
        barDataSet3.setColors(getyellow());

        BarData data1=new BarData(barDataSet1);
        BarData data2=new BarData(barDataSet2);
        BarData data3=new BarData(barDataSet3);



        barChart1.setData(data1);
        data1.setBarWidth(0.45f);
        barChart2.setData(data2);
        data2.setBarWidth(0.45f);
        barChart3.setData(data3);
        data3.setBarWidth(0.45f);








        MapFragment mapFragment=(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(ActualInterface.this);

        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.INTERNET}

                        ,10);

            }

            return;
        }
        else if(ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED){
            //map.setMyLocationEnabled(true);
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);




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

    public static int getRed(){
        return Color.rgb(255, 99, 71);
    }

    public static int getyellow(){
        return Color.rgb(255, 165, 0);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //map.setMyLocationEnabled(true);

        map.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10))
                .title("Hello world"));

    }

    @Override
    public void onLocationChanged(Location location) {

        Toast.makeText(this, "yes!", Toast.LENGTH_SHORT).show();
        map.clear();
        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

        Marker myMarker=map.addMarker(new MarkerOptions().position(currentLocation).title("here!"));
        myMarker.setVisible(true);
        map.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

        addHeatMap(map);


//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(currentLocation);
//        markerOptions.title("i'm here");

        //map.addMarker(markerOptions);

        // map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));

        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "on ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void addHeatMap(GoogleMap map){
        list=new ArrayList<LatLng>();
//        for(int i=0;i<pointX.length-1;++i){
//            list.add(new LatLng(pointX[i],pointY[i]));
//        }
        try {
            list = readItems(R.raw.csvjson);
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        }


        mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        mOverlay = map.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }

    private ArrayList<LatLng> readItems(int resource) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("XLAT");
            double lng = object.getDouble("YLNG");
            list.add(new LatLng(lat, lng));
        }
        return list;
    }

}
