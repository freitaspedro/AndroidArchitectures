package com.example.androidarchitectures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidarchitectures.mvc.MVCActivity;
import com.example.androidarchitectures.mvp.MVPActivity;
import com.example.androidarchitectures.mvvm.MVVMActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonMVC).setOnClickListener(v -> startActivity(MVCActivity.getIntent(this)));
        findViewById(R.id.buttonMVP).setOnClickListener(v -> startActivity(MVPActivity.getIntent(this)));
        findViewById(R.id.buttonMVVM).setOnClickListener(v -> startActivity(MVVMActivity.getIntent(this)));
    }

}