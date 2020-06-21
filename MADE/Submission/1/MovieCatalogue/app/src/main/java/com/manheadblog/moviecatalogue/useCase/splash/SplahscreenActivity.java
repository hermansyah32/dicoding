package com.manheadblog.moviecatalogue.useCase.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.manheadblog.moviecatalogue.R;
import com.manheadblog.moviecatalogue.useCase.main.MainActivity;

public class SplahscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splahscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplahscreenActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }

}
