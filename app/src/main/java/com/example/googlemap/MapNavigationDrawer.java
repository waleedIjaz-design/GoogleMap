package com.example.googlemap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googlemap.databinding.ActivityMapNavigationDrawerBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MapNavigationDrawer extends AppCompatActivity implements OnMapReadyCallback {

//    FusedLocationProviderClient fusedLocationProviderClient;
//    LocationCallback locationCallback;
//
//    public static Location mLastLocation = null;
    private GoogleMap mMap;
//    Marker mCurrent;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMapNavigationDrawerBinding binding;

    SupportMapFragment mapFragment;

//    private LocationRequest mLocationRequest;
//
//    private static int UPDATE_INTERVAL = 5000;
//    private static int FASTEST_INTERVAL = 3000;
//    private static int DISPLACEMENT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    //    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        //Assign variable
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);


        //Navigation

        setSupportActionBar(binding.appBarMapNavigationDrawer.toolbar);
        binding.appBarMapNavigationDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginWithFirebase.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_map).setDrawerLayout(drawer).build();
        //   R.id.nav_slideshow


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_map_navigation_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


     //   setUpLocation();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map_navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_map_navigation_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        mMap.setTrafficEnabled(false);
//        mMap.setIndoorEnabled(false);
//        mMap.setBuildingsEnabled(false);
//        mMap.getUiSettings().setAllGesturesEnabled(true);


        // adding marker 1
        MarkerOptions marker0 = new MarkerOptions().position(new LatLng(31.46040887091082, 74.28651083512122))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Marker 1");
        googleMap.addMarker(marker0);


        // adding marker 2
        MarkerOptions marker = new MarkerOptions().position(new LatLng(31.46506697429912, 74.28866733115719))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title("Marker 2");
        googleMap.addMarker(marker);


        // adding marker 3
        MarkerOptions marker2 = new MarkerOptions().position(new LatLng(31.461003730025975, 74.26969874910951))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("Marker 3");
        googleMap.addMarker(marker2);


        // adding marker 4
        MarkerOptions marker3 = new MarkerOptions().position(new LatLng(31.477438650282494, 74.27085746334662))
                .title("Marker 4");
        googleMap.addMarker(marker3);

    }


//    private void buildLocationRequest() {
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(UPDATE_INTERVAL);
//        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
//    }


//    private void setUpLocation() {
//
//        //check Permission
//        if (ActivityCompat.checkSelfPermission(MapNavigationDrawer.this,
//                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//
//            //when permission granted
//            //call method
//
//            buildLocationRequest();
//            buildLocationCallBack();
//            fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper());
////            displayLocation();
//
//        } else {
//            //when permission denied
//            //Request permission
//            ActivityCompat.requestPermissions(MapNavigationDrawer.this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//        }
//
//    }


//    private void buildLocationCallBack() {
//
//        locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                for (Location location : locationResult.getLocations()) {
//                    mLastLocation = location;
//
//                }
//                displayLocation();
//            }
//        };
//    }

//
//    private void displayLocation() {
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//
//        fusedLocationProviderClient.getLastLocation();
//
//        if (mLastLocation != null) {
//            final double latitude = mLastLocation.getLatitude();
//            final double longitude = mLastLocation.getLongitude();
//
//            //Add Marker
//            if (mCurrent != null)
//                mCurrent.remove();          //Remove Already Marker
//            mCurrent = mMap.addMarker(new MarkerOptions()
//                    .position(new LatLng(latitude, longitude))
//                    // .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
//                    .title("Your Current Location"));
//
//            //Move camera to this position
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10));
//
//        }
//
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//
//        fusedLocationProviderClient.getLastLocation()
//                .addOnSuccessListener(new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        mLastLocation = location;
//
//                        if (mLastLocation != null) {
//
//                            final double latitude = mLastLocation.getLatitude();
//                            final double longitude = mLastLocation.getLongitude();
//
//
//                            if (mCurrent != null)
//                                mCurrent.remove();          //Remove Already Marker
//                            mCurrent = mMap.addMarker(new MarkerOptions()
//                                    .position(new LatLng(latitude, longitude))
//                                    //.icon(BitmapDescriptorFactory.defaultMarker())
//                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
//                                    .title("Your Current Location"));
//
//                            //Move camera to this position
//                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10));
//
//                        } else {
//                            Log.d("ERROR", "Cannot get your Location");
//                        }
//                    }
//                });
//
//    }

//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 44) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                displayLocation();
//            }
//        }
//    }

}