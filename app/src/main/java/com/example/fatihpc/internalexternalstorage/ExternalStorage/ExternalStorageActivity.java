package com.example.fatihpc.internalexternalstorage.ExternalStorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fatihpc.internalexternalstorage.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity {

    // properties.
    private TextView tvExternalPrivateContent;
    private EditText etExternalPrivateData;
    private TextView tvExternalPublicContent;
    private EditText etExternalPublicData;

    /**
     * creates the main view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        initaliseComponents();
    }

    /**
     * initialises the required components.
     */
    private void initaliseComponents() {
        tvExternalPrivateContent = findViewById(R.id.tvExternalPrivateContent);
        etExternalPrivateData = findViewById(R.id.etExternalPrivateData);
        tvExternalPublicContent = findViewById(R.id.tvExternalPublicContent);
        etExternalPublicData = findViewById(R.id.etExternalPublicData);
    }

    /*
     * EXTERNAL PRIVATE DATA
     * Nobody has access except the app.
     * Are deleted on uninstalisation of app.
     */

    /**
     * saves private data to External Storage.
     * @param view is the button on the layout.
     */
    public void saveExternalPrivateData(View view) {
        File file = new File(getExternalFilesDir("externalPrivate"), "External Private Data" );
        String content = etExternalPrivateData.getText().toString();
        saveDataToFile(file, content);
    }

    /**
     * loads external private data to External Storage.
     * @param view is the text view on the layout.
     */
    public void loadExternalPrivateData(View view) {
        File file = new File(getExternalFilesDir("externalPrivate"), "External Private Data" );
        tvExternalPrivateContent.setText(loadDataFromFile(file));
    }

    /*
     * EXTERNAL PUBLIC DATA
     * Everybody has access.
     * Are not deleted on uninstalisation of app.
     */

    /**
     * loads public data from External Storage.
     * @param view is the text view on the layout.
     */
    public void loadExternalPublicData(View view) {
        File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(filePath, "ExternalStoragePublic");
        tvExternalPublicContent.setText(loadDataFromFile(file));
    }

    /**
     * saves public data to External Storage.
     * @param view is the button on the layout.
     */
    public void saveExternalPublicData(View view) {
        File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(filePath, "ExternalStoragePublic");
        String content = etExternalPublicData.getText().toString();
        saveDataToFile(file, content);
    }


    /**
     * loads data from a file.
     * @param file is the file.
     * @return result is the loaded data.
     */
    private String loadDataFromFile(File file) {

        FileInputStream fileInputStream = null;
        String result = "";

        try {
            fileInputStream = new FileInputStream(file);
            int readedData = fileInputStream.read();

            while (readedData != -1) {
                result = result + (char)readedData;
                readedData = fileInputStream.read();
            }

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

        return result;
    }

    /**
     * saves data to a file.
     * @param file is the file.
     * @param content is the content.
     */
    private void saveDataToFile(File file, String content) {

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
