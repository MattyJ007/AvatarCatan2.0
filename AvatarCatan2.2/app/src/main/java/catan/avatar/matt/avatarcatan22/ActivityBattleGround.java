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

import static catan.avatar.matt.avatarcatan22.ThreadBattleHandler.attack;

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
        Button attNo = (Button) findViewById(R.id.button13);
        final Button defYes = (Button) findViewById(R.id.button14);
        Button defNo = (Button) findViewById(R.id.button16);

        ControllerBattleGround.setAttackingArmyGrid(offensiveTeamGrid);
        ControllerBattleGround.setDefendingArmyGrid(defensiveTeamGrid);

        ControllerBattleGround.setOffensiveTeamText((TextView) findViewById(R.id.textView));
        ControllerBattleGround.setDefensiveTeamText((TextView) findViewById(R.id.textView4));

        ControllerBattleGround.setAttackingTeamNo(attNo);
        ControllerBattleGround.setAttackingTeamYes(attYes);
        ControllerBattleGround.setDefendingTeamNo(defNo);
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
                if(attYes.getText().equals("Yes")){
                    DataProviderBattle.setBlocking(true);
                    ControllerBattleGround.getAttackingArmyGrid().setEnabled(true);
                    ControllerBattleGround.getAttackingTeamYes().setText("Continue");
                    ControllerBattleGround.getAttackingTeamNo().setVisibility(View.INVISIBLE);
                }
                else {
                    DataProviderBattle.setBlocking(false);
                    ControllerBattleGround.getDefendingArmyGrid().setEnabled(true);
                    attack();
                    attackGridAdapter.notifyDataSetChanged();
                    defenseGridAdapter.notifyDataSetChanged();
                    ControllerBattleGround.getAttackingTeamYes().setText("Yes");
                    ControllerBattleGround.getAttackingTeamYes().setVisibility(View.INVISIBLE);
                }
            }
        });
        attNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataProviderBattle.setBlocking(false);
                ControllerBattleGround.getAttackingTeamYes().setVisibility(View.INVISIBLE);
                ControllerBattleGround.getAttackingTeamNo().setVisibility(View.INVISIBLE);
                attack();
                attackGridAdapter.notifyDataSetChanged();
                defenseGridAdapter.notifyDataSetChanged();
            }
        });
        defYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (defYes.getText().equals("Yes")){
                    DataProviderBattle.setBlocking(true);
                    ControllerBattleGround.getDefendingArmyGrid().setEnabled(true);
                    ControllerBattleGround.getDefendingTeamYes().setText("Continue");
                    ControllerBattleGround.getDefendingTeamNo().setVisibility(View.INVISIBLE);
                }
                else{
                    DataProviderBattle.setBlocking(false);
                    ControllerBattleGround.getAttackingArmyGrid().setEnabled(true);
                    attack();
                    attackGridAdapter.notifyDataSetChanged();
                    defenseGridAdapter.notifyDataSetChanged();
                    ControllerBattleGround.getDefendingTeamYes().setText("Yes");
                    ControllerBattleGround.getDefendingTeamYes().setVisibility(View.INVISIBLE);
                }
            }
        });
        defNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataProviderBattle.setBlocking(false);
                ControllerBattleGround.getDefendingTeamYes().setVisibility(View.INVISIBLE);
                ControllerBattleGround.getDefendingTeamNo().setVisibility(View.INVISIBLE);
                attack();
                attackGridAdapter.notifyDataSetChanged();
                defenseGridAdapter.notifyDataSetChanged();
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
