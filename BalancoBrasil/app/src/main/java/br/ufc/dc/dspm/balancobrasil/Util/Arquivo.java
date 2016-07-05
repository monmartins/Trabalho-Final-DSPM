package br.ufc.dc.dspm.balancobrasil.Util;

import android.content.SyncStatusObserver;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;

/**
 * Created by Vasco on 05/07/2016.
 */
public class Arquivo {

    private static String file;

    public Arquivo(String file) {
        this.file =file;
    }

    //Metodo para ler o arquivo com a matriz
    public ArrayList<Municipio> lerArquivo() throws IOException {

        ArrayList<Municipio> municipios = new ArrayList<Municipio>();

        FileReader arq = new FileReader(file);

        BufferedReader lerArq = new BufferedReader(arq);

        String nomeMunicipio;
        double latitudeMunicipio;
        double longitudeMunicipio;



        while(lerArq.readLine() != null) {
            nomeMunicipio =lerArq.readLine();
            latitudeMunicipio = Double.parseDouble(lerArq.readLine());
            longitudeMunicipio = Double.parseDouble(lerArq.readLine());

            Municipio municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio);

            municipios.add(municipio);
        }


        arq.close();

        return municipios;
    }
}
