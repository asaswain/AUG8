package asa.scps.nyu.edu.nycmaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //setUpMapIfNeeded();
        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(MapsActivity.this);  // This calls OnMapReady(..). (Asynchronously)
    };

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng nyu = new LatLng(40.734457, -73.993886);
        map.setMyLocationEnabled(true);
        float tilt = MapSettings.getCameraTilt();
        CameraPosition x = new CameraPosition(nyu, 15, tilt, 0);
        map.moveCamera(CameraUpdateFactory.newCameraPosition(x));
       int mapType = MapSettings.getMapType();
        map.setMapType(mapType);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.addMarker(new MarkerOptions()
                .title("NYU")
                .snippet("(home of course INFO1-CE9416)")
                .position(nyu));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
