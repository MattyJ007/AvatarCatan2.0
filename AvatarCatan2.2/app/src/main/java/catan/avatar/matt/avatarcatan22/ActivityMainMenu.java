package catan.avatar.matt.avatarcatan22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityMainMenu extends AppCompatActivity {
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
                DataProviderUnitList.setUnitData();
                Intent intent = new Intent(ActivityMainMenu.this, ActivityUnits.class);
                startActivity(intent);
            }
        });
        rules.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this, ActivityRules.class);
                startActivity(intent);
            }
        });
        newBattle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataProviderArmies.setArmies();
                DataProviderUnitList.setUnitData();
                Intent intent = new Intent(ActivityMainMenu.this, ActivityOffensiveTeamChoice.class);
                startActivity(intent);
            }
        });
        loadBattle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataProviderUnitList.setUnitData();
                Intent intent = new Intent(ActivityMainMenu.this, ActivityLoadBattle.class);
                startActivity(intent);
            }
        });
        timer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this, ActivityTimer.class);
                startActivity(intent);
            }
        });
        //**gets unit info sorted before anything needs it. Runs new thread.
    }
}
