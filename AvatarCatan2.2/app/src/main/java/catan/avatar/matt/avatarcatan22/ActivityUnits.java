package catan.avatar.matt.avatarcatan22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityUnits extends AppCompatActivity {
    List<Unit>listOfHeroes;
    ExpandableListView exp_list;
    AdapterUnitExpandList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        exp_list = (ExpandableListView) findViewById(R.id.expandableListView);
        listOfHeroes = DataProviderUnitList.getMainUnitsList();
        adapter = new AdapterUnitExpandList(this, listOfHeroes);
        exp_list.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
