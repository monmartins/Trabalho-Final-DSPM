package br.ufc.dc.dspm.balancobrasil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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
    public void query(final Long id, final Context context){
        try {
            Toast.makeText(context, "Entrou query 1", Toast.LENGTH_SHORT).show();
            QueryTransference queryTransference = new QueryTransference();
            queryTransference.setCodigoFuncao(id);
            Call<Integer> call = authApi.getQuery(queryTransference);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    result = (Integer)response.body();
                    Toast.makeText(context, "Deu certo", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

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
}
