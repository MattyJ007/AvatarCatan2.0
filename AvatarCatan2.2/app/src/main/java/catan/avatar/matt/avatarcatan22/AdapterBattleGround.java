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

            String numAttacks = "";
            for (int i = 0; i<(unit.getNumberOfAttacks()-unit.getNumberOfAttacksUsed()); i++){
                numAttacks += "●";
            }
            for (int j = 0; j<(unit.getNumberOfAttacksUsed());j++){
                numAttacks += "-";
            }
            TextView numAttacksText = (TextView) convertView.findViewById(R.id.gridattack);
            numAttacksText.setText(numAttacks);

            TextView statusText = (TextView) convertView.findViewById(R.id.status);
            String statuses = "";
            for (byte status: unit.getStatus()){
                if(status == (byte) 1){
                    continue;
                }
                if (status == (byte) 2){
                    statuses += "[stunned]";
                }
                if (status == (byte) 3){
                    statuses += "[paralysed]";
                }
                if (status == (byte) 4){
                    statuses += "[no bending]";
                }
                if (status == (byte) 5){
                    statuses += "[blocked chi]";
                }
            }
            statusText.setTextColor(Color.parseColor("#FF9C0AF7"));
            if (unit.getStatus().contains((byte) 0)){
                statuses = "[DEAD]";
                statusText.setTextColor(Color.parseColor("#FFFF0000"));
            }
            statusText.setText(statuses);

            TextView lifeText = (TextView) convertView.findViewById(R.id.gridlife);
            lifeText.setText(String.valueOf(unit.getLife()));
            if (unit.getLife()<5){
                lifeText.setTextColor(Color.parseColor("#ff6512"));
            }
            if (unit.getLife()<1){
                lifeText.setTextColor(Color.parseColor("#FFFF0000"));
                numAttacksText.setText("-");
            }

            unit.setView(convertView);

            return convertView;
        }
}
