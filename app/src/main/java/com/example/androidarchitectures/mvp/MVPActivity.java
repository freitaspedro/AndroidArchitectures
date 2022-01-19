package com.example.androidarchitectures.mvp;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.androidarchitectures.mvc.CountriesController;
import com.example.androidarchitectures.mvc.MVCActivity;

import java.util.ArrayList;
import java.util.List;

public class MVPActivity extends AppCompatActivity implements CountriesPresenter.View {

    private List<String> values = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private CountriesPresenter presenter;
    private Button retryButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setTitle(getString(R.string.title_mvp));

        presenter = new CountriesPresenter(this);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.item_layout, R.id.textView, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "You clicked " + values.get(position), Toast.LENGTH_SHORT).show()
        );

        retryButton = findViewById(R.id.retryButton);
        retryButton.setOnClickListener(this::onRetry);

        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void setValues(List<String> values) {
        this.values.clear();
        this.values.addAll(values);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    public void onRetry(View view) {
        presenter.onRefresh();
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
        retryButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVPActivity.class);
    }
}