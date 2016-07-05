package br.ufc.dc.dspm.balancobrasil;

import android.app.Fragment;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends Fragment implements OnMapReadyCallback{

    private static View view;
    private GoogleApiClient googleApiClient;
    private GoogleMap mMap;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_maps, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        this.mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng location = new LatLng(-3.7460927, -38.5743825);
        mMap.addMarker(new MarkerOptions().position(location).title("UFC - Bloco Computação"));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    public void refreshMap(Location lc,String address){
        this.mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng location = new LatLng(lc.getLatitude(), lc.getLongitude());
        MarkerOptions marker = new MarkerOptions().position(location).title(address);
        this.mMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(16).build();
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

