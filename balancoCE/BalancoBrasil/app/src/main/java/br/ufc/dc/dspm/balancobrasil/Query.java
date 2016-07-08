package br.ufc.dc.dspm.balancobrasil;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.ufc.dc.dspm.balancobrasil.Pojo.Consulta;
import br.ufc.dc.dspm.balancobrasil.WebService.ApiEndpointInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ramon on 03/07/2016.
 */
public class Query {

    private ApiEndpointInterface authApi = ApiEndpointInterface.retrofit.create(ApiEndpointInterface.class);
    private List<List<String>> result;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list = new ArrayList<>();
//    Esse metodo procura por municipio e retorna os dados:
    /*Nome Funcao
    *Nome Programa
    *Nome Acao
    *Nome Favorecido
    *Fonte-Finalidade
    * Valor Parcela
    * Mes
    * nessa ordem
    * */

    public void query(final Context context, final br.ufc.dc.dspm.balancobrasil.WebService.Query query, final View view){
        try {
            Call<List<List<String>>> call = authApi.getQuery(query);
            call.enqueue(new Callback<List<List<String>>>() {
                ArrayList<String> values = new ArrayList<String>();
                @Override
                public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                    result = response.body();
//                    for(int i =0;i<result.size();i++){
//                        Consulta consulta = new Consulta(result.get(i).get(0),result.get(i).get(1),result.get(i).get(2),result.get(i).get(3),result.get(i).get(4),result.get(i).get(5),result.get(i).get(6));
//                        values.add(consulta.toString());
//                    }
//
//                    //list = values;
//                    adapter = new ArrayAdapter<String>(context,
//                            android.R.layout.simple_list_item_1,values );
//                    ((ListActivity)context).setListAdapter(adapter);
                    ArrayList<String> arrayList = new ArrayList<String>();

                    for(int i=0;i<10;i++){
                        arrayList.add("Teste");
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                            android.R.layout.simple_list_item_1,
                            arrayList );

                    ListView listView = (ListView) view.findViewById(R.id.list);
                    listView.setAdapter(arrayAdapter);
                }

                @Override
                public void onFailure(Call<List<List<String>>> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayAdapter<String> getAdapter(){
        return adapter;
    }

    public ArrayList<String> getList(){
        return list;
    }
}
