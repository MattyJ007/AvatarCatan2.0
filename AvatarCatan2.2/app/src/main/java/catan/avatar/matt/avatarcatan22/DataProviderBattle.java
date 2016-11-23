package catan.avatar.matt.avatarcatan22;

import android.view.View;

import java.util.ArrayList;

public class DataProviderBattle {
    private static boolean attackerTurn;
    private static Unit currentAttackingUnit;
    //    private static ArrayList<Unit> currentlyBlockingUnits;
    private static ArrayList<Unit> currentDefendingUnits;
    private static ArrayList<Unit> unitsFinishedAttacking;
    private static View currentAttackingUnitView;

    public static void setUnit(Unit unit, int team, View view) {
        ThreadBattleHandler.battleHandlerThread(unit, team, view);
    }

    public static View getCurrentAttackingUnitView() {
        return currentAttackingUnitView;
    }

    public static void setCurrentAttackingUnitView(View currentAttackingUnitView) {
        DataProviderBattle.currentAttackingUnitView = currentAttackingUnitView;
    }

    public static boolean isAttackerTurn() {
        return attackerTurn;
    }

    public static void setAttackerTurn(boolean attackerTurn) {
        DataProviderBattle.attackerTurn = attackerTurn;
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
        DataProviderBattle.currentAttackingUnit = currentAttackingUnit;
    }

//    public static ArrayList<Unit> getCurrentlyBlockingUnits() {
//        return currentlyBlockingUnits;
//    }
//
//    public static void setCurrentlyBlockingUnits(ArrayList<Unit> currentlyBlockingUnits) {
//        DataProviderBattle.currentlyBlockingUnits = currentlyBlockingUnits;
//    }

    public static ArrayList<Unit> getCurrentDefendingUnits() {
        return currentDefendingUnits;
    }

    public static void setCurrentDefendingUnits(ArrayList<Unit> currentDefendingUnits) {
        DataProviderBattle.currentDefendingUnits = currentDefendingUnits;
    }

    public static ArrayList<Unit> getUnitsFinishedAttacking() {
        return unitsFinishedAttacking;
    }

    public static void setUnitsFinishedAttacking(ArrayList<Unit> unitsFinishedAttacking) {
        DataProviderBattle.unitsFinishedAttacking = unitsFinishedAttacking;
    }
}
