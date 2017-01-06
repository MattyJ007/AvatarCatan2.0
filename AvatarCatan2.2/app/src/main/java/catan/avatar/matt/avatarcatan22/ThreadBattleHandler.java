package catan.avatar.matt.avatarcatan22;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentDefendingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getDeadAttackingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getDeadDefendingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getUnitsFinishedAttacking;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.isAttackerTurn;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentDefendingUnits;

class ThreadBattleHandler {
    private static Unit unit;
    private static int team;

    static void battleHandlerThread(Unit unit, int team) {
        ThreadBattleHandler.unit = unit;
        ThreadBattleHandler.team = team;
        selectUnit(ThreadBattleHandler.unit, ThreadBattleHandler.team);
    }

    private static void selectUnit(Unit unit, int team) {
        if (getDeadDefendingUnits().contains(unit) || getDeadAttackingUnits().contains(unit)) {
            String dead = unit.getName() + " is Dead";
            ControllerBattleGround.getOffensiveTeamText().setText(dead);
            ControllerBattleGround.getDefensiveTeamText().setText(dead);
        } else if (DataProviderBattle.isHealing()) {
            Random rand = new Random();
            int heal = (rand.nextInt(5) + 1);
            if (team == 1 && isAttackerTurn()) {
                unit.setLife((byte) ((unit.getLife() + heal)));
                ControllerBattleGround.getOffensiveTeamText().setText(getCurrentAttackingUnit().getName() + " heals " + unit.getName() + " for " + heal + " life");
                ControllerBattleGround.getDefensiveTeamText().setText(getCurrentAttackingUnit().getName() + " heals " + unit.getName() + " for " + heal + " life");
                DataProviderBattle.setIsHealing(false);
                getCurrentAttackingUnit().setNumberOfAttacksUsed(getCurrentAttackingUnit().getNumberOfAttacks());
                getCurrentAttackingUnit().getView().setBackgroundColor(Color.parseColor("#ffffff"));
                getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                setCurrentAttackingUnit(null);
                Attack.checkTurn();
            } else if (team == 0 && !isAttackerTurn()) {
                unit.setLife((byte) ((unit.getLife() + heal)));
                ControllerBattleGround.getOffensiveTeamText().setText(getCurrentAttackingUnit().getName() + " heals " + unit.getName() + " for " + heal + " life");
                ControllerBattleGround.getDefensiveTeamText().setText(getCurrentAttackingUnit().getName() + " heals " + unit.getName() + " for " + heal + " life");
                DataProviderBattle.setIsHealing(false);
                getCurrentAttackingUnit().setNumberOfAttacksUsed(getCurrentAttackingUnit().getNumberOfAttacks());
                getCurrentAttackingUnit().getView().setBackgroundColor(Color.parseColor("#ffffff"));
                getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                setCurrentAttackingUnit(null);
                Attack.checkTurn();
            }
        } else if (getCurrentAttackingUnit() == null) {
            String isAtt = unit.getName() + " is attacking";
            String alreadyAttd = unit.getName() + " has already attacked";
            //**Attackers turn
            if (isAttackerTurn()) {
                //**Check which teams unit was selected
                if (team == 1) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        ControllerBattleGround.getDefInfoText().setText(isAtt);
                        ControllerBattleGround.getOffensiveTeamText().setText("Choose unit to attack:\n");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getOffensiveTeamText().setText(alreadyAttd);
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText("Not your turn\n");
                }
            }
            //**Defenders turn
            else {
                if (team == 0) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        ControllerBattleGround.getAttInfoText().setText(isAtt);
                        ControllerBattleGround.getDefensiveTeamText().setText("Choose unit to attack:\n");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getDefensiveTeamText().setText(alreadyAttd);
                    }
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText("Not your turn\n");
                }
            }
        } else {
            //**Allows player to deselect attacking unit
            if (getCurrentAttackingUnit() == unit) {
                setCurrentAttackingUnit(null);
                unit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                String deselect = unit.getName() + " deselected";
                if (team == 0) {
                    ControllerBattleGround.getDefensiveTeamText().setText(deselect);
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText(deselect);

                }
                //**removes any units that may have been chosen to be attacked
                setCurrentDefendingUnits(new ArrayList<Unit>());
            }
            //**Offensive team turn - therefore need to select unit from defending side to be attacked
            else if (isAttackerTurn()) {
                if (team == 0) {
                    getCurrentAttackingUnit().setNumberOfAttacksUsed((byte) (getCurrentAttackingUnit().getNumberOfAttacksUsed() + 1));
                    if (!getCurrentDefendingUnits().contains(unit)) {
                        getCurrentDefendingUnits().add(unit);
                    }
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    if (getCurrentAttackingUnit().getNumberOfAttacksUsed() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                        Attack.attack(getCurrentAttackingUnit(), getCurrentDefendingUnits(), false, team);
                        ControllerBattleGround.getDefensiveTeamText().clearComposingText();
                        ControllerBattleGround.getOffensiveTeamText().clearComposingText();
                    }
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText(R.string.cant_attack_ownTeam);
                }
            }
            //**Defender team turn
            else {
                if (team == 1) {
                    getCurrentAttackingUnit().setNumberOfAttacksUsed((byte) (getCurrentAttackingUnit().getNumberOfAttacksUsed() + 1));
                    if (!getCurrentDefendingUnits().contains(unit)) {
                        getCurrentDefendingUnits().add(unit);
                    }
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    if (getCurrentAttackingUnit().getNumberOfAttacksUsed() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                        Attack.attack(getCurrentAttackingUnit(), getCurrentDefendingUnits(), false, team);
                        ControllerBattleGround.getDefensiveTeamText().clearComposingText();
                        ControllerBattleGround.getOffensiveTeamText().clearComposingText();
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText(R.string.cant_attack_ownTeam);
                }
            }
        }
    }
}
