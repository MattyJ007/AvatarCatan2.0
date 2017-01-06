package catan.avatar.matt.avatarcatan22;

import android.graphics.Color;

import java.util.Random;

public class HandleUnitLongClick {

    public static void setUnit(Unit unit, int team) {
        if (unit.getStatus().contains((byte) 2)) {
            System.out.println(unit.getName() + " is Stunned");
        } else if (unit.getStatus().contains((byte) 3)) {
            System.out.println(unit.getName() + " is Paralysed");
        } else {
            if (unit.getAbility().equals("Heal")) {
                DataProviderBattle.setIsHealing(true);
                unit.getView().setBackgroundColor(Color.parseColor("#30718f"));
                DataProviderBattle.setCurrentAttackingUnit(unit);
            }
            if (unit.getAbility().equals("Summon Spirit")) {
                DataProviderBattle.getUnitsFinishedAttacking().add(unit);
                unit.setNumberOfAttacksUsed(unit.getNumberOfAttacks());
                unit.setAbility("Summon Spirit(Used)");
                Random rand = new Random();
                if (rand.nextInt(2) == 0) {
                    for (int i = 0; i <= (rand.nextInt(2) + 1); i++) {
                        try {
                            Unit lesserSpirit = (Unit) (ThreadAssembleUnitList.getMainUnitsList().get(11)).clone();
                            DataProviderBattle.getUnitsFinishedAttacking().add(lesserSpirit);
                            lesserSpirit.setNumberOfAttacksUsed(lesserSpirit.getNumberOfAttacks());

                            if (DataProviderBattle.isAttackerTurn()) {
                                DataProviderArmies.getArmies().getAttackingTeamUnits().add(lesserSpirit);
                            } else {
                                DataProviderArmies.getArmies().getDefendingTeamUnits().add(lesserSpirit);
                            }
                        } catch (CloneNotSupportedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    try {
                        Unit spirit = (Unit) (ThreadAssembleUnitList.getMainUnitsList().get(29).clone());
                        DataProviderBattle.getUnitsFinishedAttacking().add(spirit);
                        spirit.setNumberOfAttacksUsed(spirit.getNumberOfAttacks());

                        if (DataProviderBattle.isAttackerTurn()) {
                            DataProviderArmies.getArmies().getAttackingTeamUnits().add(spirit);
                        } else {
                            DataProviderArmies.getArmies().getDefendingTeamUnits().add(spirit);
                        }
                    } catch (CloneNotSupportedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                Attack.checkTurn();
            }
        }
    }
}
