package catan.avatar.matt.avatarcatan22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import java.util.HashMap;

public class UnitsActivity extends AppCompatActivity {
    HashMap<String, Unit> unitlist;
    ExpandableListView exp_list;
    UnitExpandListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        exp_list = (ExpandableListView) findViewById(R.id.expandableListView);
        unitlist = UnitListDataProvider.getUnitList();
        adapter = new UnitExpandListAdapter(this, unitlist);
        exp_list.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
