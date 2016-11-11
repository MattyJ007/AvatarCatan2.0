package catan.avatar.matt.avatarcatan22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class DefensiveTeamChoiceActivity extends AppCompatActivity {
    private List<Unit> unitList = UnitListDataProvider.getMainUnitsList();


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
                if (!ArmiesDataProvider.getArmies().getDefendingTeamUnits().isEmpty()){
                    Intent intent = new Intent(DefensiveTeamChoiceActivity.this, SettlementChoiceActivity.class);
                    startActivity(intent);
                }
            }
        });
        ListView defensiveHeroes = (ListView) findViewById(R.id.listView4);
        final DefenseTeamChoiceAdapter adapter = new DefenseTeamChoiceAdapter(this,R.layout.list_unit, ArmiesDataProvider.getArmies().getDefendingTeamUnits());
        defensiveHeroes.setAdapter(adapter);

        ListView chooseDefensiveHeroes = (ListView) findViewById(R.id.listView3);
        final AttackingTeamChoiceAdapter adapter1 = new AttackingTeamChoiceAdapter(this,R.layout.list_unit, unitList);
        chooseDefensiveHeroes.setAdapter(adapter1);

        defensiveHeroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = ArmiesDataProvider.getArmies().getDefendingTeamUnits().get(position);
                ArmiesDataProvider.getArmies().removeTeamMember(unit, 0);
                adapter.notifyDataSetChanged();
            }
        });
        chooseDefensiveHeroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = unitList.get(position);
                ArmiesDataProvider.getArmies().setTeamMember(unit, 0);
                adapter1.notifyDataSetChanged();
            }
        });
    }
}
