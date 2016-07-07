package br.ufc.dc.dspm.balancobrasil.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufc.dc.dspm.balancobrasil.Model.Municipio;
import br.ufc.dc.dspm.balancobrasil.R;
import br.ufc.dc.dspm.balancobrasil.TabMunicipioActivity;

/**
 * Created by thiagoripardo on 06/07/16.
 */
public class MunicipioAdapter extends RecyclerView.Adapter<MunicipioAdapter.ViewHolder> {

    private List<Municipio> listNames;
    private Resources resources;
    private Context context;


    public MunicipioAdapter(List<Municipio> listNames) {

        this.listNames = listNames;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

            resources = itemView.getResources();
            context = itemView.getContext();
        }
    }


    public class ItensViewHolder extends ViewHolder {
        public TextView name;
        //public CircleImageView pedidoImg;


        public ItensViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.itemName);

        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.municipio_card, parent, false);

        return new ItensViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        ItensViewHolder holder = (ItensViewHolder) viewHolder;

        holder.name.setText(listNames.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context, TabMunicipioActivity.class);
                intent.putExtra("municipio", listNames.get(position));
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {

        return (listNames.size());
    }
}