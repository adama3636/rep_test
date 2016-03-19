package com.example.adama.findmypharmacie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adama.findmypharmacie.R;
import com.example.adama.findmypharmacie.models.Pharmacie;

import java.util.List;


/**
 * Created by adama on 16/03/2016.
 */
public class PharmacieAdapter extends RecyclerView.Adapter<PharmacieAdapter.MyViewHolder>{
    private List<Pharmacie> pharmacieList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nom, telephone, address, emei, latitude, logitude, accracy;

        public MyViewHolder(View view) {
            super(view);
            this.nom = (TextView) view.findViewById(R.id.name);
            this.telephone = (TextView) view.findViewById(R.id.phone);
            this.address = (TextView) view.findViewById(R.id.neighborhood);
            this.emei = (TextView) view.findViewById(R.id.emei);
            this.latitude = (TextView) view.findViewById(R.id.latitude);
            this.logitude = (TextView) view.findViewById(R.id.logitude);
            this.accracy = (TextView) view.findViewById(R.id.accuracy);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PharmacieAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
