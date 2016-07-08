package br.ufc.dc.dspm.balancobrasil;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import br.ufc.dc.dspm.balancobrasil.Query;
import br.ufc.dc.dspm.balancobrasil.R;
public class ConsultasActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Context context = this;
    Query processingQuery = new Query();
    br.ufc.dc.dspm.balancobrasil.WebService.Query query =new br.ufc.dc.dspm.balancobrasil.WebService.Query();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*query.setCaps("AMONTADA");
        query.setTipo("1");
        //query.setSetor();
        query.setTamanhoBusca(100);
        //query.setFonteFinalidade("STN - Conv�nios/Contratos de Repasses/Fundo a Fundo/Outros");
        query.setComecarDe(2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processingQuery.query(context,query);
            }
        });*/
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    /*public void next(View view){
        query.setCaps("AMONTADA");
        query.setTipo("1");
        //query.setFonteFinalidade("STN - Conv�nios/Contratos de Repasses/Fundo a Fundo/Outros");
        query.setTamanhoBusca(100);
        query.setComecarDe(0+query.getComecarDe());
        processingQuery.query(context,query);
    }*/
}
