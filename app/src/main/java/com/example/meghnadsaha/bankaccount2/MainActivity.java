package com.example.meghnadsaha.bankaccount2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvData = (TextView)findViewById(R.id.tvData);
        Button btnLoadPrfererence  = (Button) findViewById(R.id.btnLoadPrfererence);
        Button btnsavePrfererence  = (Button) findViewById(R.id.btnsaveExternal);

       final SharedPreferences prefs = getSharedPreferences("my_prefs",MODE_PRIVATE);


        btnLoadPrfererence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = prefs.getString("name","Name key not found");
                tvData.setText(name);
            }
        });
        btnsavePrfererence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name","Meghnad");
                editor.commit();
            }
        });
    }
}
