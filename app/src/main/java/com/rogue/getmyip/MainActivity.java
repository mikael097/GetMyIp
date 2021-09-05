package com.rogue.getmyip;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.net.*;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.inputmethod.InputMethodManager;


import com.google.android.material.snackbar.Snackbar;
import com.rogue.getmyip.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 97;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.button.setOnClickListener(view -> {
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            String url=binding.urlPath.getText().toString();
           if(!url.isEmpty()) {
                try {

                    String address = getIp(url);
                    if (!address.equals("-1")) {
                        //binding.displayIp.setText(address);
                        Intent intent=new Intent(this,displayActivity.class);
                        intent.putExtra(getString(R.string.ipKey), address);
                        startActivityForResult(intent,REQUEST_CODE);
                    }
                    else
                        Snackbar.make(view, R.string.UnknownHost, Snackbar.LENGTH_SHORT).show();
                } catch (Exception e) {
                    binding.displayIp.setText(Arrays.toString(e.getStackTrace()));
                }
            }
           else
               Snackbar.make(view, R.string.EmptyAddress, Snackbar.LENGTH_SHORT).show();

        });
    }
    private String getIp(String host){
        try {
            InetAddress address = InetAddress.getByName(host);
            return address.toString();
        }
        catch (UnknownHostException uhEx){
            return "-1";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}