package catan.avatar.matt.avatarcatan22;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class OffensiveTeamChoiceActivity extends AppCompatActivity {
    private List<Unit> unitList = UnitListDataProvider.getUnitsList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offensive_team_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button continueButt1 = (Button) findViewById(R.id.continueButt1);
        continueButt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OffensiveTeamChoiceActivity.this, DefensiveTeamChoiceActivity.class);
                startActivity(intent);
            }
        });
        ListView chooseOffensiveHeroes = (ListView) findViewById(R.id.listView);
        OffensiveTeamChoiceAdapter adapter = new OffensiveTeamChoiceAdapter(this,R.layout.list_unit, unitList);
        chooseOffensiveHeroes.setAdapter(adapter);
    }

}
