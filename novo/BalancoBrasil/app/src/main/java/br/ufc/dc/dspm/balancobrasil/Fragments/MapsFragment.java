package br.ufc.dc.dspm.balancobrasil.Fragments;

import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.R;

public class MapsFragment extends Fragment implements OnMapReadyCallback{

    private SupportMapFragment supportMapFragment;
    private View rootView;
    private GoogleMap googleMap;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
        /*MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        fragment.getMapAsync(this);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        supportMapFragment = SupportMapFragment.newInstance();
        rootView = inflater.inflate(R.layout.fragment_maps, null);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().replace(R.id.container_map, supportMapFragment).commitAllowingStateLoss();
        }

        supportMapFragment.getMapAsync(this);

        return rootView;
    }

    public static MapsFragment newInstance() {
        MapsFragment f = new MapsFragment();
        Bundle args = new Bundle();
        //args.putInt("index", index);
        //f.setArguments(args);
        return f;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng location = new LatLng(-3.7460927, -38.5743825);

        /**Pegar no banco todos os municipios, colocar na classe Municipio e criar um while para addMarker para todos**/

        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        municipios = readFile();

        for(int i=0 ; i< municipios.size();i++){
            LatLng municipioLocation = new LatLng(municipios.get(i).getLatitude(),municipios.get(i).getLongitude());
            googleMap.addMarker(new MarkerOptions().position(municipioLocation).title(municipios.get(i).getName()));
            if(municipios.get(i).getName().equalsIgnoreCase("Quixeramobim"));

            //Posicionamento de camera
            CameraPosition cameraPosition = new CameraPosition.Builder().target(municipioLocation).zoom(8).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        googleMap.addMarker(new MarkerOptions().position(location).title("UFC - Bloco Computação"));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    public void refreshMap(Location lc, String address){
        this.googleMap.clear();
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng location = new LatLng(lc.getLatitude(), lc.getLongitude());

        /**Pegar no banco todos os municipios, colocar na classe Municipio e criar um while para addMarker para todos**/
        MarkerOptions marker = new MarkerOptions().position(location).title(address);
        this.googleMap.addMarker(marker);


        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(16).build();
        this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public ArrayList<Municipio> readFile() {
        /*
            Dados dos municipios encontrados em : http://www.mbi.com.br/mbi/biblioteca/utilidades/dddcepce/
         */

        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream;

        String linha;
        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        String nomeMunicipio;
        double latitudeMunicipio;
        double longitudeMunicipio;

        try {
            inputStream = assetManager.open("municipiosCeara.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((nomeMunicipio = bufferedReader.readLine()) != null) {
                latitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                longitudeMunicipio = Double.parseDouble(bufferedReader.readLine());

                Municipio municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio);

                municipios.add(municipio);
            }
            inputStream.close();
            System.out.println(municipios.get(0).getName());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this.getActivity(), "Errou", Toast.LENGTH_SHORT).show();
        }

        return municipios;
    }
}

