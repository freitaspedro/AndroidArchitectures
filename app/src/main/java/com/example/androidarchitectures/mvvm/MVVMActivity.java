package com.example.androidarchitectures.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidarchitectures.R;
import com.example.androidarchitectures.mvc.MVCActivity;
import com.example.androidarchitectures.mvp.CountriesPresenter;

import java.util.ArrayList;
import java.util.List;

public class MVVMActivity extends AppCompatActivity {

    private List<String> values = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private CountriesViewModel viewModel;
    private Button retryButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle(getString(R.string.title_mvvm));

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.item_layout, R.id.textView, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "You clicked " + values.get(position), Toast.LENGTH_SHORT).show()
        );

        retryButton = findViewById(R.id.retryButton);
        retryButton.setOnClickListener(this::onRetry);

        progressBar = findViewById(R.id.progressBar);

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getCountries().observe(this, countries -> {
            if (countries != null) {
                values.clear();
                values.addAll(countries);
                listView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            } else {
                listView.setVisibility(View.GONE);
            }
        });
        viewModel.getError().observe(this, error -> {
            progressBar.setVisibility(View.GONE);
            if (error) {
                Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                retryButton.setVisibility(View.VISIBLE);
            } else {
                retryButton.setVisibility(View.GONE);
            }
        });
    }

    public void onRetry(View view) {
        viewModel.onRefresh();
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVVMActivity.class);
    }

}