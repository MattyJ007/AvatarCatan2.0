package catan.avatar.matt.avatarcatan22;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;

import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentDefendingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getUnitsFinishedAttacking;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.isAttackerTurn;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setAttackerTurn;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentAttackingUnitView;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentDefendingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setUnitsFinishedAttacking;

public class ThreadBattleHandler {
    private static Unit unit;
    private static int team;

    public static void battleHandlerThread(Unit unit, int team, View view) {
        ThreadBattleHandler.unit = unit;
        ThreadBattleHandler.team = team;
        selectUnit(ThreadBattleHandler.unit, ThreadBattleHandler.team, view);
    }

    private static void selectUnit(Unit unit, int team, View view) {
        if (getCurrentAttackingUnit() == null) {
            //**Attackers turn
            if (isAttackerTurn()) {
                //**Check which teams unit was selected
                if (team == 1) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        setCurrentAttackingUnitView(view);
                        ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " is attacking");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getOffensiveTeamText().setText(unit.getName()+ " has already attacked");
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText("Not defensive army's turn");
                    //**Not defensive team turn
                }
            }
            //**Defenders turn
            else {
                if (team == 0) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        setCurrentAttackingUnitView(view);
                        ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " is attacking");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getDefensiveTeamText().setText(unit.getName()+ " has already attacked");
                    }
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText("Not offensive army's turn");
                    //**Not offensive team turn
                }
            }
        } else {
            //**Allows player to deselect attacking unit
            if (getCurrentAttackingUnit() == unit) {
                setCurrentAttackingUnit(null);
                unit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                if (team == 0){
                    ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " deselected");
                }
                else {
                    ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " deselected");

                }
                //**removes any units that may have been chosen to be attacked
                setCurrentDefendingUnits(new ArrayList<Unit>());
            }
            //**Offensive team turn - therefore need to select unit from defending side to be attacked
            else if (DataProviderBattle.isAttackerTurn()) {
                if (team == 0) {
                    getCurrentDefendingUnits().add(unit);
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    if (getCurrentDefendingUnits().size() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        ControllerBattleGround.getOffensiveTeamText().setText(getCurrentAttackingUnit().getName() + " Attacks");
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                        getCurrentAttackingUnit().getView().setBackgroundColor(Color.parseColor("#ffffff"));
                        for (Unit defendingUnit:getCurrentDefendingUnits()) {
                            defendingUnit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                        setCurrentDefendingUnits(new ArrayList<Unit>());
                        setCurrentAttackingUnit(null);
                        if (getUnitsFinishedAttacking().size() == DataProviderArmies.getArmies().getAttackingTeamUnits().size()) {
                            setAttackerTurn(false);
                            setUnitsFinishedAttacking(new ArrayList<Unit>());
                            ControllerBattleGround.getOffensiveTeamText().setText("Defensive army's turn");
                            ControllerBattleGround.getDefensiveTeamText().setText("Defensive Army");

                        }
                    }
                } else {
                    //** Can't attack own team
                    ControllerBattleGround.getOffensiveTeamText().setText("Can't attack own team");
                }
            }
            //**Defender team turn
            else {
                if (team == 1) {
                    getCurrentDefendingUnits().add(unit);
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    if (getCurrentDefendingUnits().size() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        ControllerBattleGround.getDefensiveTeamText().setText(getCurrentAttackingUnit().getName() + " Attacks");
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                        getCurrentAttackingUnit().getView().setBackgroundColor(Color.parseColor("#ffffff"));
                        for (Unit defendingUnit:getCurrentDefendingUnits()) {
                            defendingUnit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                        setCurrentDefendingUnits(new ArrayList<Unit>());
                        setCurrentAttackingUnit(null);
                        if (getUnitsFinishedAttacking().size() == DataProviderArmies.getArmies().getDefendingTeamUnits().size()) {
                            DataProviderBattle.setAttackerTurn(true);
                            setUnitsFinishedAttacking(new ArrayList<Unit>());
                            ControllerBattleGround.getOffensiveTeamText().setText("Offensive Army");
                            ControllerBattleGround.getDefensiveTeamText().setText("Offensive army's turn");
                        }
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText("Can't attack own team");
                    //**Can't attack own team
                }
            }
        }
    }
}
