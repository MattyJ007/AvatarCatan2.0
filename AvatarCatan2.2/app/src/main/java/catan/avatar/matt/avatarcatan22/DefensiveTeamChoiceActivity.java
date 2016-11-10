package catan.avatar.matt.avatarcatan22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class DefensiveTeamChoiceActivity extends AppCompatActivity {
    private List<Unit> unitList = UnitListDataProvider.getUnitsList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defensive_team_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button continueButt2 = (Button) findViewById(R.id.continueButt2);
        continueButt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DefensiveTeamChoiceActivity.this, SettlementChoiceActivity.class);
                startActivity(intent);
            }
        });
        ListView chooseOffensiveHeroes = (ListView) findViewById(R.id.listView);
        TeamChoiceAdapter adapter = new TeamChoiceAdapter(this,R.layout.list_unit, unitList);
        chooseOffensiveHeroes.setAdapter(adapter);
    }
}
