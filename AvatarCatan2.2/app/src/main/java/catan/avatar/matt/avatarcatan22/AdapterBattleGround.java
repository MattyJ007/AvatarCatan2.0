package catan.avatar.matt.avatarcatan22;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class AdapterBattleGround extends ArrayAdapter<Unit>{
        private List<Unit> units;
        private Context ctx;

        public AdapterBattleGround(Context context, int resource, List<Unit> objects) {
            super(context, resource, objects);
            units = objects;
            ctx = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflator.inflate(R.layout.grid_block, parent, false);
            }
            Unit unit = units.get(position);

            ImageView unitPic = (ImageView) convertView.findViewById(R.id.imageView3);
            int resource = ctx.getResources().getIdentifier(unit.getImage(),"drawable",ctx.getPackageName());
            unitPic.setImageResource(resource);

            TextView nameText = (TextView) convertView.findViewById(R.id.gridname);
            nameText.setText(unit.getName());

            TextView numAttsText = (TextView) convertView.findViewById(R.id.gridattack);
            numAttsText.setText(DataProviderBattleGround.getNumAttacks(unit));

            TextView blocking = (TextView) convertView.findViewById(R.id.blocking);
            blocking.setText(DataProviderBattleGround.blocking(unit));

            TextView lifeText = (TextView) convertView.findViewById(R.id.gridlife);
            lifeText.setText(String.format(Locale.ENGLISH,"%.2f", unit.getLife()));
            if (unit.getLife()<5){
                lifeText.setTextColor(Color.parseColor("#ff6512"));
            }
            if (unit.getLife()<0.01){
                lifeText.setTextColor(Color.parseColor("#FFFF0000"));
                numAttsText.setText("-");
                blocking.setText(R.string.dead);
            }

            unit.setView(convertView);

            return convertView;
        }
}
