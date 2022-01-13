package com.example.androidarchitectures.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidarchitectures.R;
import com.example.androidarchitectures.mvc.MVCActivity;

public class MVVMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle(getString(R.string.title_mvvm));
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVVMActivity.class);
    }

}