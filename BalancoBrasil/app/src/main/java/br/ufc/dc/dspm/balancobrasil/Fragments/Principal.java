package br.ufc.dc.dspm.balancobrasil.Fragments;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

import br.ufc.dc.dspm.balancobrasil.Adapter.TabMainActivityAdapter;
import br.ufc.dc.dspm.balancobrasil.Query;
import br.ufc.dc.dspm.balancobrasil.R;

/**
 * Created by Vasco on 06/07/2016.
 */
public class Principal extends AppCompatActivity {

    Query query = new Query();
    Context context = this;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_principal);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        assert tabLayout != null;
        tabLayout.addTab(tabLayout.newTab().setText("Municípios"));
        tabLayout.setSelectedTabIndicatorColor(4);
        tabLayout.addTab(tabLayout.newTab().setText("Setor"));
        //tabLayout.addTab(tabLayout.newTab().setText("Opiniões"));

        viewPager = (ViewPager) findViewById(R.id.container);
        final PagerAdapter adapter = new TabMainActivityAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        getSupportActionBar().setTitle("Principal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        TabHost tabHost;

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }
}
