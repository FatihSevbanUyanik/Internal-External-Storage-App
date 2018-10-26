package com.example.fatihpc.internalexternalstorage;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fatihpc.internalexternalstorage.ExternalStorage.ExternalStorageActivity;
import com.example.fatihpc.internalexternalstorage.InternalExternalCache.InternalExternalCache;
import com.example.fatihpc.internalexternalstorage.InternalStorage.InternalStorageActivity;

import java.security.acl.Permission;


public class MainActivity extends RuntimeIzinlerActivity {

    /**
     * creates the main view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void izinVerildi(int requestCode) {
        if ( requestCode == 5 ) {
            Intent intent = new Intent(this, ExternalStorageActivity.class);
            startActivity(intent);
        }
    }

    public void goToInternalStorage(View view) {
        Intent intent = new Intent(this, InternalStorageActivity.class);
        startActivity(intent);
    }

    public void goToInternalExternalCache(View view) {
        Intent intent = new Intent(this, InternalExternalCache.class);
        startActivity(intent);
    }

    public void goToExternalStorage(View view) {

        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE };

        super.izinIste(permissions, 5);
    }

}