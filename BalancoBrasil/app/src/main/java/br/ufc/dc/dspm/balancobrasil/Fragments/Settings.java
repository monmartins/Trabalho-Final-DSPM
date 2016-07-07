package br.ufc.dc.dspm.balancobrasil.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import br.ufc.dc.dspm.balancobrasil.R;

public class Settings extends Fragment implements View.OnClickListener {


    private CheckBox switchCompact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view;
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.frameLayout:
                if (switchCompact.isChecked())
                    switchCompact.setChecked(false);
                else
                    switchCompact.setChecked(true);
                break;
            case R.id.relativeLayoutChooseTheme:
                Toast.makeText(null,"Alfa",Toast.LENGTH_LONG);
                break;
        }
    }

}
