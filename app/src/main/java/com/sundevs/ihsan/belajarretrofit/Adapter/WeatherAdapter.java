package com.sundevs.ihsan.belajarretrofit.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sundevs.ihsan.belajarretrofit.Model.List;
import com.sundevs.ihsan.belajarretrofit.Model.Weather;
import com.sundevs.ihsan.belajarretrofit.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;




/**
 * Created by iihsa on 10/8/2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.itemHolder> {

    java.util.List<List> data = new ArrayList<>();

    public WeatherAdapter(java.util.List<List> data) {
        this.data = data;
    }

    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent ,false);
        itemHolder objek = new itemHolder(v);

        return objek;
    }

    @Override
    public void onBindViewHolder(itemHolder holder, int position) {

        List inimodel = data.get(position);
        Weather inimodel2 = data.get(position).getWeather().get(0);

        holder.cuaca.setText(inimodel2.getMain());
        holder.waktu.setText(new SimpleDateFormat("E, dd MM yyyy").format(inimodel.getDt()));
        holder.suhu.setText(String.valueOf(inimodel.getTemp().getDay())+" \u2103");


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class itemHolder extends RecyclerView.ViewHolder {

        TextView suhu, waktu, cuaca;

        public itemHolder(View itemView) {
            super(itemView);
            suhu = (TextView) itemView.findViewById(R.id.suhu);
            waktu = (TextView) itemView.findViewById(R.id.waktu);
            cuaca = (TextView) itemView.findViewById(R.id.cuaca);
        }
    }
}
