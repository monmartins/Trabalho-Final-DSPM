package br.ufc.dc.dspm.balancobrasil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.WebService.ApiEndpointInterface;
import br.ufc.dc.dspm.balancobrasil.WebService.QueryRecipes;
import br.ufc.dc.dspm.balancobrasil.WebService.QuerySpending;
import br.ufc.dc.dspm.balancobrasil.WebService.QueryTransference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ramon on 03/07/2016.
 */
public class Query {

    private ApiEndpointInterface authApi = ApiEndpointInterface.retrofit.create(ApiEndpointInterface.class);
    private int result=-1;
    private List<List<String>> result1;
    private List<String> result2;
    public void query(final Long id, final Context context){
        try {
            Toast.makeText(context, "Entrou query 1", Toast.LENGTH_SHORT).show();
            QueryTransference queryTransference = new QueryTransference();
            queryTransference.setCodigoFuncao(id);
            Call<Integer> call = authApi.getQuery(queryTransference);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    result = response.body();
                    if(result==3534){
                        Toast.makeText(context, "Deu certo", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Toast.makeText(context, "Falha na conexão", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void consult(final Context context,final br.ufc.dc.dspm.balancobrasil.WebService.Query query){
        try {
            Toast.makeText(context, "Entrou query 1", Toast.LENGTH_SHORT).show();
            Call<List<List<String>>> call = authApi.getConsult(query);
            call.enqueue(new Callback<List<List<String>>>() {
                @Override
                public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                    result1 = response.body();
                }

                @Override
                public void onFailure(Call<List<List<String>>> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
    public void getCapsName(final Context context,final Resources re){
        try {
            Toast.makeText(context, "Entrou query 1", Toast.LENGTH_SHORT).show();
            br.ufc.dc.dspm.balancobrasil.WebService.Query query = new br.ufc.dc.dspm.balancobrasil.WebService.Query();
            query.setTipo("1");
            Call<List<String>> call = authApi.getCapsName(query);
            call.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    result2 = response.body();
                    int i = 0;

                    AssetManager assetManager = re.getAssets();
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
                            Municipio municipio = new Municipio();
                            latitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                            longitudeMunicipio = Double.parseDouble(bufferedReader.readLine());
                            if(result2.get(i).equalsIgnoreCase("Governo do Estado")){
                                i=i+1;
                               municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio,result2.get(i));
                            }
                            else{
                                municipio = new Municipio(nomeMunicipio,latitudeMunicipio,longitudeMunicipio,result2.get(i));
                            }

                            municipios.add(municipio);
                            i++;
                        }
                        inputStream.close();
                        System.out.println(municipios.get(0).getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Errou", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
}
