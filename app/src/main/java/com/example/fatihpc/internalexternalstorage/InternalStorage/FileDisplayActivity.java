package com.example.fatihpc.internalexternalstorage.InternalStorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fatihpc.internalexternalstorage.R;

import java.io.FileInputStream;
import java.io.IOException;

public class FileDisplayActivity extends AppCompatActivity {

    // properties
    private EditText etFileName;
    private TextView tvContent;

    /**
     * creates the main view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_display);
        initaliseComponents();
    }

    /**
     * initialises the required components.
     */
    private void initaliseComponents() {
        etFileName = findViewById(R.id.etFileName);
        tvContent  = findViewById(R.id.tvContent);
    }

    /**
     * loads data from a file and displays it.
     */
    public void loadData(View view) {

        FileInputStream fileInputStream = null;
        String fileName = etFileName.getText().toString();

        try {

            fileInputStream = openFileInput(fileName);
            int readedData = fileInputStream.read();
            String result = "";

            while (readedData != -1) {
                result = result + (char)readedData;
                readedData = fileInputStream.read();
            }

            tvContent.setText(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if ( fileInputStream != null ) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}