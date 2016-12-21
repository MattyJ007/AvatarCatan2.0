package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;

class DataProviderBattle {
    private static boolean attackerTurn;
    private static Unit currentAttackingUnit;
    private static ArrayList<Unit> currentlyBlockingUnits;
    private static ArrayList<Unit> currentDefendingUnits;
    private static ArrayList<Unit> unitsFinishedAttacking;
    private static ArrayList<Unit> deadAttackingUnits;
    private static ArrayList<Unit> deadDefendingUnits;
    private static boolean blocking;
    private static Unit currentUnitGettingBlocker;


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
        attackerTurn = true;
        currentlyBlockingUnits = new ArrayList<>();
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

    static ArrayList<Unit> getCurrentlyBlockingUnits() {
        return currentlyBlockingUnits;
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

    static void setUnitsFinishedAttacking(ArrayList<Unit> unitsFinishedAttacking) {
        DataProviderBattle.unitsFinishedAttacking = unitsFinishedAttacking;
    }

    static ArrayList<Unit> getDeadAttackingUnits() {
        return deadAttackingUnits;
    }

    public static void setDeadAttackingUnits(ArrayList<Unit> deadAttackingUnits) {
        DataProviderBattle.deadAttackingUnits = deadAttackingUnits;
    }

    static ArrayList<Unit> getDeadDefendingUnits() {
        return deadDefendingUnits;
    }

    public static void setDeadDefendingUnits(ArrayList<Unit> deadDefendingUnits) {
        DataProviderBattle.deadDefendingUnits = deadDefendingUnits;
    }

    static boolean isBlocking() {
        return blocking;
    }

    static void setBlocking(boolean blocking) {
        DataProviderBattle.blocking = blocking;
    }

    static Unit getCurrentUnitGettingBlocker() {
        return currentUnitGettingBlocker;
    }

    static void setCurrentUnitGettingBlocker(Unit currentUnitGettingBlocker) {
        DataProviderBattle.currentUnitGettingBlocker = currentUnitGettingBlocker;
    }
}
