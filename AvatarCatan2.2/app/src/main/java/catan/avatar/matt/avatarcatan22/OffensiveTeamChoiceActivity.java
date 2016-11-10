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

public class OffensiveTeamChoiceActivity extends AppCompatActivity {
    private List<Unit> unitList = UnitListDataProvider.getMainUnitsList();
    private List<Unit> attackingTeamUnits = AttackingTeamDataProvider.getAttackingTeamUnits();

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

        final ListView offensiveHeroes = (ListView) findViewById(R.id.listView2);
        final AttackingTeamChoiceAdapter adapter1 = new AttackingTeamChoiceAdapter(this,R.layout.list_unit_a,attackingTeamUnits);
        offensiveHeroes.setAdapter(adapter1);

        ListView chooseOffensiveHeroes = (ListView) findViewById(R.id.listView);
        AttackingTeamChoiceAdapter adapter = new AttackingTeamChoiceAdapter(this,R.layout.list_unit_a, unitList);
        chooseOffensiveHeroes.setAdapter(adapter);

        offensiveHeroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = attackingTeamUnits.get(position);
                AttackingTeamDataProvider.removeTeamMember(unit);
                adapter1.notifyDataSetChanged();
            }
        });

        chooseOffensiveHeroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = unitList.get(position);
                AttackingTeamDataProvider.setTeamMember(unit);
                adapter1.notifyDataSetChanged();
            }
        });
    }
}
