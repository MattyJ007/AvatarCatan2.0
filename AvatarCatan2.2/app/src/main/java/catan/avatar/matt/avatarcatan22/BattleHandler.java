package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;

public class BattleHandler {
    private static boolean attackerTurn;
    private static Unit currentAttackingUnit;
    //    private static ArrayList<Unit> currentlyBlockingUnits;
    private static ArrayList<Unit> currentDefendingUnits;
    private static ArrayList<Unit> unitsFinishedAttacking;

    public static void setUnit(Unit unit, int team) {
        BattleHandlerThread.battleHandlerThread(unit, team);
    }

    public static boolean isAttackerTurn() {
        return attackerTurn;
    }

    public static void setAttackerTurn(boolean attackerTurn) {
        BattleHandler.attackerTurn = attackerTurn;
    }

    public static void setBattleHandlerVariables() {
        attackerTurn = true;
//        currentlyBlockingUnits = new ArrayList<>();
        currentDefendingUnits = new ArrayList<>();
        unitsFinishedAttacking = new ArrayList<>();
    }

    public static Unit getCurrentAttackingUnit() {
        return currentAttackingUnit;
    }

    public static void setCurrentAttackingUnit(Unit currentAttackingUnit) {
        BattleHandler.currentAttackingUnit = currentAttackingUnit;
    }

//    public static ArrayList<Unit> getCurrentlyBlockingUnits() {
//        return currentlyBlockingUnits;
//    }
//
//    public static void setCurrentlyBlockingUnits(ArrayList<Unit> currentlyBlockingUnits) {
//        BattleHandler.currentlyBlockingUnits = currentlyBlockingUnits;
//    }

    public static ArrayList<Unit> getCurrentDefendingUnits() {
        return currentDefendingUnits;
    }

    public static void setCurrentDefendingUnits(ArrayList<Unit> currentDefendingUnits) {
        BattleHandler.currentDefendingUnits = currentDefendingUnits;
    }

    public static ArrayList<Unit> getUnitsFinishedAttacking() {
        return unitsFinishedAttacking;
    }

    public static void setUnitsFinishedAttacking(ArrayList<Unit> unitsFinishedAttacking) {
        BattleHandler.unitsFinishedAttacking = unitsFinishedAttacking;
    }
}
