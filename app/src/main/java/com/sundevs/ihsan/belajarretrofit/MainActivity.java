package com.sundevs.ihsan.belajarretrofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sundevs.ihsan.belajarretrofit.API.SampleAPI;
import com.sundevs.ihsan.belajarretrofit.Adapter.RecyclerItemClickListener;
import com.sundevs.ihsan.belajarretrofit.Adapter.WeatherAdapter;
import com.sundevs.ihsan.belajarretrofit.Model.List;
import com.sundevs.ihsan.belajarretrofit.Model.WeatherAPI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by iihsa on 10/8/2017.
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    java.util.List<List> data = new ArrayList<>();
    WeatherAdapter adapter;
    TextView holla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager ll = new LinearLayoutManager(this);
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(ll);
        rv.setHasFixedSize(true);
        ambilData();
        //tesPersistent();

    }
    private void ambilData(){
        SampleAPI objek = SampleAPI.Factory.getIstance(MainActivity.this);

        objek.getAllData().enqueue(new Callback<WeatherAPI>() {
            @Override
            public void onResponse(Call<WeatherAPI> call, Response<WeatherAPI> response) {
                if(response.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    data = response.body().getList();
                    adapter = new WeatherAdapter(data);
                    rv.setAdapter(adapter);
                    rv.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this,rv, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            SharedPreferences.Editor berbagi = getSharedPreferences("data",MODE_PRIVATE).edit();
                            berbagi.putString("cuaca",data.get(position).getWeather().get(0).getMain());
                            berbagi.putString("suhu", String.valueOf(data.get(position).getTemp().getDay()));
                            berbagi.putString("waktu",new SimpleDateFormat("E, dd MM yyyy").format(data.get(position).getDt()));
                            berbagi.commit();

                            startActivity(new Intent(MainActivity.this,DetailActivity.class));
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            Toast.makeText(MainActivity.this, "Berhassssssssillll ngakkkk??", Toast.LENGTH_SHORT).show();
                        }
                    }));

                }else{
                    Toast.makeText(MainActivity.this, "Error onResponse : "+response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherAPI> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
