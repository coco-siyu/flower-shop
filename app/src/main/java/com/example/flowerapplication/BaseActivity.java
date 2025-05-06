package com.example.flowerapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", "onCreate+++" + getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("BaseActivity", "onStart+++" + getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BaseActivity", "onResume+++" + getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("BaseActivity", "onPause+++" + getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BaseActivity", "onStop+++" + getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BaseActivity", "onDestroy+++" + getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("BaseActivity", "onRestart+++" + getClass().getSimpleName());
    }

}
