package catan.avatar.matt.avatarcatan22;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityCalculate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText village = (EditText) findViewById(R.id.village);
        final EditText town = (EditText) findViewById(R.id.town);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText hero = (EditText) findViewById(R.id.hero);
        final EditText unit = (EditText) findViewById(R.id.units);
        final TextView commandPoints = (TextView) findViewById(R.id.cp);
        final TextView victoryPOints = (TextView) findViewById(R.id.vp);
        final TextView income = (TextView) findViewById(R.id.income);
        Button calculate = (Button) findViewById(R.id.calculate);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vil;
                try{
                    vil = Integer.parseInt(village.getText().toString());
                }catch (NumberFormatException e){
                    vil = 0;
                }
                int tow;
                try{
                    tow = Integer.parseInt(town.getText().toString());
                }catch (NumberFormatException e){
                    tow = 0;
                }
                int cit;
                try{
                    cit = Integer.parseInt(city.getText().toString());
                }catch (NumberFormatException e){
                    cit = 0;
                }
                int her;
                try{
                    her = Integer.parseInt(hero.getText().toString());
                }catch (NumberFormatException e){
                    her = 0;
                }
                int uni;
                try{
                    uni = Integer.parseInt(unit.getText().toString());
                }catch (NumberFormatException e){
                    uni = 0;
                }
                int comP = 2;
                comP += (vil + (2 * tow) + (4 * cit) + her) - uni;
                int vp;
                vp = her + tow + (2 * cit);
                int inc;
                inc = (2 * vil) + (3 * tow) + (5 * cit);
                if (comP <0){
                    inc +=(comP*2);
                }

                income.setText(String.valueOf(inc));
                commandPoints.setText(String.valueOf(comP));
                victoryPOints.setText(String.valueOf(vp));
            }
        });


    }

}
