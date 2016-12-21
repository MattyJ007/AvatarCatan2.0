package catan.avatar.matt.avatarcatan22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
        final AdapterBattleGround attackGridAdapter = new AdapterBattleGround(this, R.layout.grid_block, DataProviderArmies.getArmies().getAttackingTeamUnits());
        offensiveTeamGrid.setAdapter(attackGridAdapter);

        GridView defensiveTeamGrid = (GridView) findViewById(R.id.gridView2);
        final AdapterBattleGround defenseGridAdapter = new AdapterBattleGround(this, R.layout.grid_block, DataProviderArmies.getArmies().getDefendingTeamUnits());
        defensiveTeamGrid.setAdapter(defenseGridAdapter);

        final Button attYes = (Button) findViewById(R.id.button15);
        final Button defYes = (Button) findViewById(R.id.button14);

        ControllerBattleGround.setAttackingArmyGrid(offensiveTeamGrid);
        ControllerBattleGround.setDefendingArmyGrid(defensiveTeamGrid);

        ControllerBattleGround.setOffensiveTeamText((TextView) findViewById(R.id.attArmyMain));
        ControllerBattleGround.setDefensiveTeamText((TextView) findViewById(R.id.defArmyMain));
        ControllerBattleGround.setAttInfoText((TextView) findViewById(R.id.attViewInfo));
        ControllerBattleGround.setDefInfoText((TextView) findViewById(R.id.defViewInfo));
        ControllerBattleGround.setCenterText((TextView) findViewById(R.id.centerText));

        ControllerBattleGround.setAttackingTeamYes(attYes);
        ControllerBattleGround.setDefendingTeamYes(defYes);

        DataProviderBattle.setBattleHandlerVariables();

        offensiveTeamGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = DataProviderArmies.getArmies().getAttackingTeamUnits().get(position);
                DataProviderBattle.setUnit(unit, 1);
                attackGridAdapter.notifyDataSetChanged();
                defenseGridAdapter.notifyDataSetChanged();
            }
        });
        defensiveTeamGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = DataProviderArmies.getArmies().getDefendingTeamUnits().get(position);
                DataProviderBattle.setUnit(unit, 0);
                attackGridAdapter.notifyDataSetChanged();
                defenseGridAdapter.notifyDataSetChanged();
            }
        });

        attYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        defYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.battle_menu, menu);
        return true;
    }
}
