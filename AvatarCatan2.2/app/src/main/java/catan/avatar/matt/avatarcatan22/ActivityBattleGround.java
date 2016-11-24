package catan.avatar.matt.avatarcatan22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class ActivityBattleGround extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_ground);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridView offensiveTeamGrid = (GridView) findViewById(R.id.gridView);
        final AdapterBattleGround adapter = new AdapterBattleGround(this, R.layout.grid_block, DataProviderArmies.getArmies().getAttackingTeamUnits());
        offensiveTeamGrid.setAdapter(adapter);

        GridView defensiveTeamGrid = (GridView) findViewById(R.id.gridView2);
        final AdapterBattleGround adapter1 = new AdapterBattleGround(this, R.layout.grid_block, DataProviderArmies.getArmies().getDefendingTeamUnits());
        defensiveTeamGrid.setAdapter(adapter1);

        ControllerBattleGround.setOffensiveTeamText((TextView) findViewById(R.id.textView));
        ControllerBattleGround.setDefensiveTeamText((TextView) findViewById(R.id.textView4));

        DataProviderBattle.setBattleHandlerVariables();

        offensiveTeamGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = DataProviderArmies.getArmies().getAttackingTeamUnits().get(position);
                DataProviderBattle.setUnit(unit, 1);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }
        });
        defensiveTeamGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = DataProviderArmies.getArmies().getDefendingTeamUnits().get(position);
                DataProviderBattle.setUnit(unit, 0);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }
        });
    }
}
