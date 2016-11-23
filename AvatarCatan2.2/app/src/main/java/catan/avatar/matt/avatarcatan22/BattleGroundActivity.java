package catan.avatar.matt.avatarcatan22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class BattleGroundActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_ground);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridView offensiveTeamGrid = (GridView) findViewById(R.id.gridView);
        final BattleGroundAdapter adapter = new BattleGroundAdapter(this, R.layout.grid_block, ArmiesDataProvider.getArmies().getAttackingTeamUnits());
        offensiveTeamGrid.setAdapter(adapter);

        GridView defensiveTeamGrid = (GridView) findViewById(R.id.gridView2);
        final BattleGroundAdapter adapter1 = new BattleGroundAdapter(this, R.layout.grid_block, ArmiesDataProvider.getArmies().getDefendingTeamUnits());
        defensiveTeamGrid.setAdapter(adapter1);

        BattleHandler.setBattleHandlerVariables();

        offensiveTeamGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = ArmiesDataProvider.getArmies().getAttackingTeamUnits().get(position);
                BattleHandler.setUnit(unit, 1);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }
        });
        defensiveTeamGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = ArmiesDataProvider.getArmies().getDefendingTeamUnits().get(position);
                BattleHandler.setUnit(unit, 0);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }
        });
    }
}
