package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;

class DataProviderBattle {
    private static boolean attackerTurn;
    private static Unit currentAttackingUnit;
    private static ArrayList<Unit> currentDefendingUnits;
    private static ArrayList<Unit> unitsFinishedAttacking;
    private static ArrayList<Unit> deadAttackingUnits;
    private static ArrayList<Unit> deadDefendingUnits;

    static void setUnit(Unit unit, int team) {
        ThreadBattleHandler.battleHandlerThread(unit, team);
    }

    static boolean isAttackerTurn() {
        return attackerTurn;
    }

    static void setAttackerTurn(boolean attackerTurn) {
        DataProviderBattle.attackerTurn = attackerTurn;
    }

    static void setBattleHandlerVariables() {
        //** Allows defender to go first
        attackerTurn = false;
        currentDefendingUnits = new ArrayList<>();
        unitsFinishedAttacking = new ArrayList<>();
        deadAttackingUnits = new ArrayList<>();
        deadDefendingUnits = new ArrayList<>();

    }

    static Unit getCurrentAttackingUnit() {
        return currentAttackingUnit;
    }

    static void setCurrentAttackingUnit(Unit currentAttackingUnit) {
        DataProviderBattle.currentAttackingUnit = currentAttackingUnit;
    }

    static ArrayList<Unit> getCurrentDefendingUnits() {
        return currentDefendingUnits;
    }

    static void setCurrentDefendingUnits(ArrayList<Unit> currentDefendingUnits) {
        DataProviderBattle.currentDefendingUnits = currentDefendingUnits;
    }

    static ArrayList<Unit> getUnitsFinishedAttacking() {
        return unitsFinishedAttacking;
    }

    static ArrayList<Unit> getDeadAttackingUnits() {
        return deadAttackingUnits;
    }

    static ArrayList<Unit> getDeadDefendingUnits() {
        return deadDefendingUnits;
    }

}
