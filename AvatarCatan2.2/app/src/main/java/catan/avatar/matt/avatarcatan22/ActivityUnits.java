package catan.avatar.matt.avatarcatan22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityUnits extends AppCompatActivity {
    HashMap<String, List<Unit>> unitlist;
    List<String> statList;
    ExpandableListView exp_list;
    AdapterUnitExpandList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        exp_list = (ExpandableListView) findViewById(R.id.expandableListView);
        unitlist = DataProviderUnitList.getUnitHashMap();
        statList = new ArrayList<>(unitlist.keySet());
        adapter = new AdapterUnitExpandList(this, unitlist, statList);
        exp_list.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
