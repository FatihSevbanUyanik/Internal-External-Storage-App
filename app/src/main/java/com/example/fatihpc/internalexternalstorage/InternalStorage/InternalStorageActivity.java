package com.example.fatihpc.internalexternalstorage.InternalStorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fatihpc.internalexternalstorage.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class InternalStorageActivity extends AppCompatActivity {

    // properties
    private TextView tvStorageFilePath;
    private EditText etFileNameRemove;
    private EditText etFileName;
    private EditText etContent;
    private TextView tvFileList;

    /**
     * creates the main view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        initaliseComponents();
    }

    /**
     * initialises the required components.
     */
    private void initaliseComponents() {
        tvStorageFilePath = findViewById(R.id.tvStorageFilePath);
        etFileNameRemove = findViewById(R.id.etFileNameRemove);
        tvFileList = findViewById(R.id.tvFileList);
        etFileName = findViewById(R.id.etFileName);
        etContent  = findViewById(R.id.etContent);
    }

    /*
     * INTERNAL DATA STORAGE
     * fast but limited.
     * private: only app has access
     * .
     */

    /**
     * directs the user to FileDisplayActivity.
     * @param view is the show data button.
     */
    public void showData(View view) {
        Intent intent = new Intent(this, FileDisplayActivity.class);
        startActivity(intent);
    }

    /**
     * shows the internal storages file list.
     */
    public void showInternalStorageFileList(View view) {
        tvFileList.setText(Arrays.toString(fileList()));
    }

    /**
     * shows the internal storages file path.
     */
    public void showInternalStorageFilePath(View view) {
        tvStorageFilePath.setText( getFilesDir().getPath() );
    }

    /**
     * saves data to a file.
     * @param view is the save button.
     */
    public void saveDataToFile(View view) {

        String fileName = etFileName.getText().toString();
        String content  = etContent.getText().toString();
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
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

    /**
     * removes a file from directory.
     * @param view is the remove button.
     */
    public void removeFile(View view) {
        String fileName = etFileNameRemove.getText().toString();

        if ( deleteFile(fileName) ) {
            Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "File Not Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}
