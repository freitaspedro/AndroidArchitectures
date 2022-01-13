package com.example.androidarchitectures.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidarchitectures.R;

import java.util.ArrayList;
import java.util.List;

public class MVCActivity extends AppCompatActivity {

    private List<String> values = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        setTitle(getString(R.string.title_mvc));

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.item_layout, R.id.textView, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "You clicked " + values.get(position), Toast.LENGTH_SHORT).show()
        );

        ArrayList<String> mockValues = new ArrayList<>();
        mockValues.add("USA");
        mockValues.add("UK");
        mockValues.add("France");
        mockValues.add("Italy");
        mockValues.add("China");
        mockValues.add("Japan");
        mockValues.add("USA");
        mockValues.add("UK");
        mockValues.add("France");
        mockValues.add("Italy");
        mockValues.add("China");
        mockValues.add("Japan");
        mockValues.add("USA");
        mockValues.add("UK");
        mockValues.add("France");
        mockValues.add("Italy");
        mockValues.add("China");
        mockValues.add("Japan");
        mockValues.add("USA");
        mockValues.add("UK");
        mockValues.add("France");
        mockValues.add("Italy");
        mockValues.add("China");
        mockValues.add("Japan");
        mockValues.add("USA");
        mockValues.add("UK");
        mockValues.add("France");
        mockValues.add("Italy");
        mockValues.add("China");
        mockValues.add("Japan");
        setValues(mockValues);
    }

    public void setValues(List<String> values) {
        this.values.clear();
        this.values.addAll(values);
        adapter.notifyDataSetChanged();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVCActivity.class);
    }

}