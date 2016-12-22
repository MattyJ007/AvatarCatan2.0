package catan.avatar.matt.avatarcatan22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ActivitySettlementChoice extends AppCompatActivity implements View.OnClickListener {

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
        set1.setOnClickListener(this);
        set2.setOnClickListener(this);
        set3.setOnClickListener(this);
        set4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set1:
                break;
            case R.id.set2:
                DataProviderSettlementDefence.setSettlementEvasionBonus(5);
                break;
            case R.id.set3:
                DataProviderSettlementDefence.setSettlementEvasionBonus(10);
                break;
            case R.id.set4:
                DataProviderSettlementDefence.setSettlementEvasionBonus(20);
                break;
            default:
                break;
        }
        Intent intent = new Intent(this, ActivityBattleGround.class);
        startActivity(intent);
    }
}
