package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;

import static catan.avatar.matt.avatarcatan22.BattleHandler.getCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.BattleHandler.getCurrentDefendingUnits;
import static catan.avatar.matt.avatarcatan22.BattleHandler.getUnitsFinishedAttacking;
import static catan.avatar.matt.avatarcatan22.BattleHandler.isAttackerTurn;
import static catan.avatar.matt.avatarcatan22.BattleHandler.setAttackerTurn;
import static catan.avatar.matt.avatarcatan22.BattleHandler.setCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.BattleHandler.setCurrentDefendingUnits;
import static catan.avatar.matt.avatarcatan22.BattleHandler.setUnitsFinishedAttacking;

public class BattleHandlerThread {
    private static Unit unit;
    private static int team;


    public static void battleHandlerThread(Unit unit, int team) {
        BattleHandlerThread.unit = unit;
        BattleHandlerThread.team = team;
        selectUnit(BattleHandlerThread.unit, BattleHandlerThread.team);
    }

    private static void selectUnit(Unit unit, int team) {
        if (getCurrentAttackingUnit() == null) {
            //**Attackers turn
            if (isAttackerTurn()) {
                //**Check which teams unit was selected
                if (team == 1) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        System.out.println(unit.getName() + " is attacking ");
                    } else {
                        System.out.println("Unit has already attacked");
                    }
                } else {
                    System.out.println("NOt defensive team turn");
                    //**Not defensive team turn
                }
            }
            //**Defenders turn
            else {
                if (team == 0) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        System.out.println(unit.getName() + " is attacking ");
                    } else {
                        System.out.println("Unit has already attacked");
                    }
                } else {
                    System.out.println("NOt offensive team turn");
                    //**Not offensive team turn
                }
            }
        } else {
            //**Allows player to deselect attacking unit
            if (getCurrentAttackingUnit() == unit) {
                setCurrentAttackingUnit(null);
                System.out.println(unit.getName() + " deselected");
                setCurrentDefendingUnits(new ArrayList<Unit>());
            }
            //**Offensive team turn - therefore need to select unit from defending side to be attacked
            else if (BattleHandler.isAttackerTurn()) {
                if (team == 0) {
                    getCurrentDefendingUnits().add(unit);
                    if (getCurrentDefendingUnits().size() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        System.out.println(getCurrentAttackingUnit().getName() + " Attacks " + getCurrentDefendingUnits());
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                        setCurrentDefendingUnits(new ArrayList<Unit>());
                        setCurrentAttackingUnit(null);
                        if (getUnitsFinishedAttacking().size() == ArmiesDataProvider.getArmies().getAttackingTeamUnits().size()) {
                            setAttackerTurn(false);
                            setUnitsFinishedAttacking(new ArrayList<Unit>());
                            System.out.println("Change turns");
                        }
                        //**Attack
                    }
                } else {
                    //** Can't attack own team
                    System.out.println("Can't attack own team");
                }
            }
            //**Defender team turn
            else {
                if (team == 1) {
                    getCurrentDefendingUnits().add(unit);
                    if (getCurrentDefendingUnits().size() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        System.out.println(getCurrentAttackingUnit().getName() + " Attacks " + getCurrentDefendingUnits());
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                        setCurrentDefendingUnits(new ArrayList<Unit>());
                        setCurrentAttackingUnit(null);
                        if (getUnitsFinishedAttacking().size() == ArmiesDataProvider.getArmies().getDefendingTeamUnits().size()) {
                            BattleHandler.setAttackerTurn(true);
                            setUnitsFinishedAttacking(new ArrayList<Unit>());
                            System.out.println("Change turns");
                        }
                        //**Attack
                    }
                } else {
                    System.out.println("Can't attack own team");
                    //**Can't attack own team
                }
            }
        }
    }
}
