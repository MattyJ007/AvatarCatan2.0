package catan.avatar.matt.avatarcatan22;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettlementChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button set1 = (Button) findViewById(R.id.set1);
        Button set2 = (Button) findViewById(R.id.set2);
        Button set3 = (Button) findViewById(R.id.set3);
        Button set4 = (Button) findViewById(R.id.set4);

        set1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettlementChoiceActivity.this, BattleGroundActivity.class);
                startActivity(intent);
            }
        });
        set2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettlementChoiceActivity.this, BattleGroundActivity.class);
                startActivity(intent);
            }
        });
        set3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettlementChoiceActivity.this, BattleGroundActivity.class);
                startActivity(intent);
            }
        });
        set4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettlementChoiceActivity.this, BattleGroundActivity.class);
                startActivity(intent);
            }
        });
    }

}
