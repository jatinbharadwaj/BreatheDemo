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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActualInterface extends AppCompatActivity implements OnMapReadyCallback,LocationListener {

    private Button btnForecast,btnSearchLoc,btnShopNow;
    private LinearLayout LLMap,LLChart;

    private GoogleMap map;
    private LocationManager locationManager;
    private List<LatLng> list=null;
    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_interface);

        btnSearchLoc=(Button) findViewById(R.id.btnSearchLoc);
        btnForecast=(Button) findViewById(R.id.btnForecast);
        btnShopNow=(Button) findViewById(R.id.btnShop);
        LLMap=(LinearLayout) findViewById(R.id.LLMap);
        LLChart=(LinearLayout) findViewById(R.id.LLChart);



        MapFragment mapFragment=(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(ActualInterface.this);

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
                Intent toShopNow=new Intent(ActualInterface.this,ShopNow.class);
            }
        });


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


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //map.setMyLocationEnabled(true);

//        map.addMarker(new MarkerOptions()
//                .position(new LatLng(10, 10))
//                .title("Hello world"));
        //addHeatMap(map);
    }

    @Override
    public void onLocationChanged(Location location) {

        Toast.makeText(this, "yes!", Toast.LENGTH_SHORT).show();
        map.clear();
        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

        Marker myMarker=map.addMarker(new MarkerOptions().position(currentLocation).title("here!"));
        myMarker.setVisible(true);
        map.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

        //addHeatMap(map);


//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(currentLocation);
//        markerOptions.title("i'm here");

        //map.addMarker(markerOptions);

        // map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.5f));

        addHeatMap(map);
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
        list=null;
        try{
            list=readItems(R.raw.csvjson);
        }catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        }

        int[] colors = {
                Color.rgb(0, 225, 0),
                Color.rgb(255, 0, 255),
        };

        float[] startPoints = {
                0.2f, 1f
        };

        Gradient gradient = new Gradient(colors, startPoints);


        mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .gradient(gradient)
                .radius(30)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        mOverlay = map.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }

    private ArrayList<LatLng> readItems(int resource) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        //for (int i = 0; i < array.length(); i++) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("XLAT");
            double lng = object.getDouble("YLNG");
            list.add(new LatLng(lat, lng));
        }
        return list;
    }




}
