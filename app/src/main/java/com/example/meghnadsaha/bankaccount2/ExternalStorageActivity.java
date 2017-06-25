package com.example.meghnadsaha.bankaccount2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity {

    private static final String FILE_NAME = "external_file.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        final TextView tvData = (TextView)findViewById(R.id.tvData);
        Button btnLoadInternal  = (Button) findViewById(R.id.btnLoadExternal);
        Button btnsaveInternal  = (Button) findViewById(R.id.btnsaveExternal);

        final EditText editText = (EditText)findViewById(R.id.editText);
        btnLoadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //String data =   FileOperationsHelper.getInstance().readFile(getApplicationContext(),FILE_NAME);
                   String data = FileOperationsHelper.getInstance().readExternalFile(FILE_NAME);
                    tvData.setText(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnsaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataToSave = editText.getText().toString();
                try {
                    FileOperationsHelper.getInstance().saveExternalFile(FILE_NAME,dataToSave);
                    editText.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
