package catan.avatar.matt.avatarcatan22;

import android.graphics.Color;

public class HandleUnitLongClick {

    public static void setUnit(Unit unit, int team) {
        if(unit.getStatus().contains((byte) 2)){
            System.out.println(unit.getName() + " is Stunned");
        }
        else if (unit.getStatus().contains((byte) 3)){
            System.out.println(unit.getName() + " is Paralysed");
        }
        else {
            if(unit.getAbility().equals("Heal")){
                DataProviderBattle.setIsHealing(true);
                unit.getView().setBackgroundColor(Color.parseColor("#30718f"));
                DataProviderBattle.setCurrentAttackingUnit(unit);
            }
        }
    }
}
