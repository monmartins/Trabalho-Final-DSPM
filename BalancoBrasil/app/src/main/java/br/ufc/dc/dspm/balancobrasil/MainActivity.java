package br.ufc.dc.dspm.balancobrasil;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufc.dc.dspm.balancobrasil.WebService.QueryTransference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Query query = new Query();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.query(3534l,context);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        //User
        TextView nameUser = (TextView) header.findViewById(R.id.nameUser);
        nameUser.setText("Balanço Brasil");

        TextView helloUser = (TextView) header.findViewById(R.id.helloUser);
        helloUser.setText("Olá, camarada!");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        //Name toolbar
        String title = "BalancoBrasil";

        //Change for fragment is better
        if (id == R.id.nav_map) {
            fragment = new Maps();
            title = "Mapa";
        } else if (id == R.id.nav_feed) {
            fragment = new Feed();
            title = "Feed de Notícias";
        } else if (id == R.id.nav_settings) {
            fragment = new Settings();
            title = "Configurações";
        } else if (id == R.id.nav_signout) {
            finish();
        }


        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();

        EditText name = (EditText) findViewById(R.id.name);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);

        values.put(InformationsProvider.NAME, name.getText().toString());

        values.put(InformationsProvider.LATITUDE, Double.parseDouble(latitude.getText().toString()));

        values.put(InformationsProvider.LONGITUDE, Double.parseDouble(longitude.getText().toString()));

        Uri uri = getContentResolver().insert(
                InformationsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        // Retrieve student records
        String URL = "content://br.ufc.dc.dspm.provider.Counties/informations";

        Uri counties = Uri.parse(URL);
        //Cursor c = managedQuery(counties, null, null, null, "name");
        Cursor c = getContentResolver().query(counties, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(InformationsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.NAME)) +
                                ", " +  c.getString(c.getColumnIndex( InformationsProvider.LATITUDE)) +
                                ", " + c.getString(c.getColumnIndex( InformationsProvider.LONGITUDE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
