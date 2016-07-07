package br.ufc.dc.dspm.balancobrasil.Fragments;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.R;
import br.ufc.dc.dspm.balancobrasil.WebService.Query;

/**
 * Created by Vasco on 06/07/2016.
 */
public class Consultas extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private EditText nameQuery;
    ArrayList<Municipio> municipiosQ;
    private Button save;
    private br.ufc.dc.dspm.balancobrasil.WebService.Query queryObject = new br.ufc.dc.dspm.balancobrasil.WebService.Query();
    private br.ufc.dc.dspm.balancobrasil.Query query = new br.ufc.dc.dspm.balancobrasil.Query();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view;

        view = inflater.inflate(R.layout.fragment_consultas, container, false);

        nameQuery = (EditText) view.findViewById(R.id.name_query);

        nameQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //some_button.performClick();
                    return true;
                }
                return false;
            }
        });
        createSpinners(view);
        readFile();

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(queryObject.getFonteFinalidade().equals("Nenhum")&&queryObject.getSetor().equals("Nenhum")&&queryObject.getMunicipios().equals("Nenhum")&&queryObject.getValor().equals("Nenhum")){
                    Toast.makeText(getActivity(),"Por favor não deixe nenhum campo com 'Nenhum' ",Toast.LENGTH_SHORT).show();
                }
                else{
                    municipiosQ = readFile();
                    for(int i =0; i<municipiosQ.size();i++){
                        if(municipiosQ.get(i).getName().equals(queryObject.getMunicipios())){
                            queryObject.setCaps(municipiosQ.get(i).getNameScape());
                        }
                    }

                    query.getCapsName(getActivity(),getResources());
//                    queryObject.setTipo("2");
//                    query.consult(getActivity(), queryObject);
                }
            }
        });

        return view;
    }

    public void createSpinners(View view){



        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        municipios = readFile();
        List<String> categoriesMunicipio = new ArrayList<String>();

        categoriesMunicipio.add("Nenhum");
        for(int i=0 ; i< municipios.size();i++){
            categoriesMunicipio.add(municipios.get(i).getName());
        }


        //Spinner 1
        // Spinner element
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Nenhum");
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categoriesMunicipio);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        /*******/




        //Spinner 2
        // Spinner element
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);

        // Spinner click listener
        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesSetor = new ArrayList<String>();
        categoriesSetor.add("Nenhum");
        categoriesSetor.add("Seguran�a P�blica");
        categoriesSetor.add("Direitos da Cidadania");
        categoriesSetor.add("Assist�ncia Social");
        categoriesSetor.add("Encargos Especiais");
        categoriesSetor.add("Trabalho");
        categoriesSetor.add("Educa��o");
        categoriesSetor.add("Administra��o");
        categoriesSetor.add("Rela��es Exteriores");
        categoriesSetor.add("Sa�de");
        categoriesSetor.add("Previd�ncia Social");
        categoriesSetor.add("Ci�ncia e Tecnologia");
        categoriesSetor.add("Com�rcio e Servi�os");
        categoriesSetor.add("Defesa Nacional");
        categoriesSetor.add("Organiza��o Agr�ria");
        categoriesSetor.add("Saneamento");
        categoriesSetor.add("Urbanismo");
        categoriesSetor.add("Desporto e Lazer");
        categoriesSetor.add("Cultura");
        categoriesSetor.add("Agricultura");
        categoriesSetor.add("Gest�o Ambiental");
        categoriesSetor.add("Comunica��es");
        categoriesSetor.add("Habita��o");
        categoriesSetor.add("Ind�stria");
        categoriesSetor.add("Transporte");
        categoriesSetor.add("Energia");
        categoriesSetor.add("Judici�ria");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categoriesSetor);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);



        //Spinner 3
        // Spinner element
        spinner3 = (Spinner) view.findViewById(R.id.spinner3);

        // Spinner click listener
        spinner3.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesFonteFinalidade = new ArrayList<String>();
        categoriesFonteFinalidade.add("Nenhum");
        categoriesFonteFinalidade.add("STN - Transfer�ncias a Munic�pios");
        categoriesFonteFinalidade.add("STN - Conv�nios/Contratos de Repasses/Fundo a Fundo/Outros");
        categoriesFonteFinalidade.add("STN - Transfer�ncias a Estados");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categoriesFonteFinalidade);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner3.setAdapter(dataAdapter3);


        //Spinner 4
        // Spinner element
        spinner4 = (Spinner) view.findViewById(R.id.spinner4);

        // Spinner click listener
        spinner4.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesValor = new ArrayList<String>();
        categoriesValor.add("Nenhum");
        categoriesValor.add(">100");
        categoriesValor.add(">1000");
        categoriesValor.add(">10000");
        categoriesValor.add(">100000");
        categoriesValor.add(">1000000");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categoriesValor);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner4.setAdapter(dataAdapter4);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if(item.equals("Nenhum")){

        }
        else{
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner1")){
                queryObject.setMunicipios(item);
            }
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner2")){
                queryObject.setSetor(item);
            }
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner3")){
                queryObject.setFonteFinalidade(item);
            }
            if(parent.toString().substring(parent.toString().length()-9,parent.toString().length()-1).equals("spinner4")){
                queryObject.setValor(item.substring(1,item.length()-1));
            }
        }


     }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void saveQuery(View v){
        Toast.makeText(getActivity(),"Salvo", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<Municipio> readFile() {

        query.getCapsName(getActivity(),getResources());
        /*
            Dados dos municipios encontrados em : http://www.mbi.com.br/mbi/biblioteca/utilidades/dddcepce/
         */

        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream;

        String linha;
        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        String nomeMunicipio;
        String nomeMunicipioSG;
        double latitudeMunicipio;
        double longitudeMunicipio;

        try {
            inputStream = assetManager.open("municipiosCeara.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((nomeMunicipio = bufferedReader.readLine()) != null) {
                latitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                longitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                nomeMunicipioSG = bufferedReader.readLine();

                Municipio municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio,nomeMunicipioSG);

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
