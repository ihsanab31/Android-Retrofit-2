package com.sundevs.ihsan.belajarretrofit;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by iihsa on 10/8/2017.
 */

public class DetailActivity extends AppCompatActivity {

    TextView suhu,waktu,cuaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        suhu = (TextView)findViewById(R.id.suhudetail);
        waktu = (TextView)findViewById(R.id.waktudetail);
        cuaca = (TextView)findViewById(R.id.cuacadetail);

        SharedPreferences terbagi = getSharedPreferences("data",MODE_PRIVATE);
        suhu.setText(terbagi.getString("suhu",null));
        waktu.setText(terbagi.getString("waktu",null));
        cuaca.setText(terbagi.getString("cuaca",null));

    }
}
