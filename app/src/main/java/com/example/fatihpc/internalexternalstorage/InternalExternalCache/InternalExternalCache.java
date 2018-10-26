package com.example.fatihpc.internalexternalstorage.InternalExternalCache;

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

public class InternalExternalCache extends AppCompatActivity {

    // properties
    private TextView tvContentInternal;
    private EditText etContentInternal;
    private TextView tvContentExternal;
    private EditText etContentExternal;

    /**
     * creates the main view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_external_cache);
        initaliseComponents();
    }

    /**
     * initialises the required components.
     */
    private void initaliseComponents() {
        etContentInternal = findViewById(R.id.etContentInternal);
        tvContentInternal = findViewById(R.id.tvContentInternal);
        etContentExternal = findViewById(R.id.etContentExternal);
        tvContentExternal = findViewById(R.id.tvContentExternal);
    }

    /*
     * CACHE INTERNAL DATA
     * very fast but very limited.
     * private: only app has access.
     * Are deleted on uninstalisation of app.
     */

    /**
     * saves data to internal cache.
     * @param view is the button on the layout.
     */
    public void saveDataToInternalCache(View view) {
        String fileName = "InternalCacheContent"; // Type file name here.
        String content  = etContentInternal.getText().toString();
        File cacheFile = new File(getCacheDir(), fileName);
        saveDataToFile(cacheFile, content);
    }

    /**
     * loads data from internal cache.
     * @param view is the text view on the layout.
     */
    public void loadDataFromInternalCache(View view) {
        String fileName = "InternalCacheContent"; // Type file name here.
        File cacheFile = new File(getCacheDir(), fileName);
        tvContentInternal.setText(loadDataFromFile(cacheFile));
    }

    /*
     * CACHE EXTERNAL DATA
     * very fast but very limited.
     * private: only app has access.
     */

    /**
     * saves data to external cache.
     * @param view is the button on the layout.
     */
    public void saveDataToExternalCache(View view) {
        String fileName = "ExternalCacheContent"; // Type file name here.
        String content  = etContentExternal.getText().toString();
        File cacheFile = new File(getExternalCacheDir(), fileName);
        saveDataToFile(cacheFile, content);
    }

    /**
     * loads data from external cache.
     * @param view is the text view on the layout.
     */
    public void loadDataFromExternalCache(View view) {
        String fileName = "ExternalCacheContent"; // Type file name here.
        File cacheFile = new File(getExternalCacheDir(), fileName);
        tvContentExternal.setText(loadDataFromFile(cacheFile));
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