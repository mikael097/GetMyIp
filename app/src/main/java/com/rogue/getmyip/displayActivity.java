package com.rogue.getmyip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rogue.getmyip.databinding.ActivityDisplayBinding;

public class displayActivity extends AppCompatActivity {

    private ActivityDisplayBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_display);
        Bundle bundle=getIntent().getExtras();
        if(!bundle.isEmpty()){
            String display=bundle.getString(getString(R.string.ipKey));
            binding.display.setText(display);
            binding.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }
}