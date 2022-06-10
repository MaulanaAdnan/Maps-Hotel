package com.example.maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

//Maulana Adnan Khalid	(F55119028)
//Nadia Venda           (F55119001)
//Hanrini Salangkas	    (F55119012)
//Deralita Ruruk	    (F55119038)
//Jafar Umar Cholik	    (F55119119)


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                // No location access granted.
                            }
                        }
                );
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng untad = new LatLng(-0.83643, 119.89369);
        LatLng sma = new LatLng(-0.8420789148870214, 119.88401729859929);
        LatLng gmh = new LatLng(-0.8981852127011656, 119.86328664837673);
        LatLng pih = new LatLng(-0.8949739792225755, 119.87866936490796);
        LatLng sb = new LatLng(-0.8767162409483529, 119.83611992944844);
        LatLng hs = new LatLng(-0.8968070913610927, 119.87289530129586);
        LatLng gh = new LatLng(-0.8848328339949657, 119.86823090195236);
        LatLng ch = new LatLng(-0.8980819874449957, 119.85976690366473);
        LatLng lh = new LatLng(-0.8911419636800703, 119.86433494172478);
        LatLng ri = new LatLng(-0.8994633553060063, 119.87552787978467);
        LatLng rn = new LatLng(-0.8647113284503853, 119.87746760779454);
        LatLng srh = new LatLng(-0.9189487409332981, 119.89793110000075);

        //costum size marker
        int tinggi = 100;
        int lebar = 100;
        BitmapDrawable bitmapStart = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_map_hitam);
        BitmapDrawable bitmapDes = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_map_merah);
        BitmapDrawable bitmapHotel = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_hotel);
        Bitmap s = bitmapStart.getBitmap();
        Bitmap d  = bitmapDes.getBitmap();
        Bitmap h  = bitmapHotel.getBitmap();
        Bitmap markerStart = Bitmap.createScaledBitmap(s, lebar, tinggi, false);
        Bitmap markerDes = Bitmap.createScaledBitmap(d, lebar, tinggi, false);
        Bitmap markerHot = Bitmap.createScaledBitmap(h, lebar, tinggi, false);

        //add marker to map
        mMap.addMarker(new MarkerOptions().position(untad).title("Marker in Untad")
                .snippet("Ini adalah Kampus Kami")
                .icon(BitmapDescriptorFactory.fromBitmap(markerStart)));

        mMap.addMarker(new MarkerOptions().position(sma).title("Marker in SMAN 5 Palu")
                .snippet("Ini adalah SMAN 5 Palu")
                .icon(BitmapDescriptorFactory.fromBitmap(markerDes)));

        mMap.addMarker(new MarkerOptions().position(gmh).title("Marker in Gajah Mada Hotel")
                .snippet("Ini adalah Gajah Mada Hotel")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(pih).title("Marker in Pondok Indah Hotel")
                .snippet("Ini adalah Pondok Indah Hotel")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(sb).title("Marker in Swiss-Belhotel Silae Palu")
                .snippet("Ini adalah Swiss-Belhotel Silae Palu")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(hs).title("Marker in Hotel Santika Palu")
                .snippet("Ini adalah Hotel Santika Palu")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(gh).title("Marker in Palu Golden Hotel")
                .snippet("Ini adalah Palu Golden Hotel")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(ch).title("Marker in Palu City Hotel")
                .snippet("Ini adalah Palu City Hotel")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(lh).title("Marker in Lucky Hotel")
                .snippet("Ini adalah Lucky Hotel")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(ri).title("Marker in Rajawali Inn")
                .snippet("Ini adalah Rajawali Inn")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(rn).title("Marker in Renjana - Kampung Nelayan Hotel, Restaurant & Convention Hall")
                .snippet("Ini adalah Renjana - Kampung Nelayan Hotel, Restaurant & Convention Hall")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addMarker(new MarkerOptions().position(srh).title("Marker in Sutan Raja Hotel & Covention")
                .snippet("Ini adalah Sutan Raja Hotel & Covention")
                .icon(BitmapDescriptorFactory.fromBitmap(markerHot)));

        mMap.addPolyline(new PolylineOptions().add(
                untad,
                new LatLng(-0.836341, 119.892311),
                new LatLng(-0.836545, 119.892279),
                new LatLng(-0.836384, 119.889565),
                new LatLng(-0.836363, 119.889340),
                new LatLng(-0.836282, 119.889233),
                new LatLng(-0.836282, 119.889233),
                new LatLng(-0.836204, 119.883431),
                new LatLng(-0.836743, 119.883487),
                new LatLng(-0.839093, 119.883360),
                new LatLng(-0.841530, 119.883290),
                new LatLng(-0.841571, 119.884040),
                sma
                ).width(10)
                        .color(Color.GREEN)
        );

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Lokasi Saat ini");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}