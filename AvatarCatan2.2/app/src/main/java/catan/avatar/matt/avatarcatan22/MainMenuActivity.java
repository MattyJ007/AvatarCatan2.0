package catan.avatar.matt.avatarcatan22;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button units = (Button) findViewById(R.id.button2);
        Button rules = (Button) findViewById(R.id.button3);
        Button newBattle = (Button) findViewById(R.id.button);
        Button loadBattle = (Button) findViewById(R.id.button5);
        Button timer = (Button) findViewById(R.id.button4);
        units.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UnitListDataProvider.setUnitData();
                Intent intent = new Intent(MainMenuActivity.this, UnitsActivity.class);
                startActivity(intent);
            }
        });
        rules.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });
        newBattle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ArmiesDataProvider.setArmies();
                UnitListDataProvider.setUnitData();
                Intent intent = new Intent(MainMenuActivity.this, OffensiveTeamChoiceActivity.class);
                startActivity(intent);
            }
        });
        loadBattle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UnitListDataProvider.setUnitData();
                Intent intent = new Intent(MainMenuActivity.this, LoadBattleActivity.class);
                startActivity(intent);
            }
        });
        timer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });
        //**gets unit info sorted before anything needs it. Runs new thread.
    }
}
