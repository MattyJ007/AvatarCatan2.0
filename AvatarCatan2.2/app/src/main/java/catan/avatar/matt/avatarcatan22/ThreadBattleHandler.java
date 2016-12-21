package catan.avatar.matt.avatarcatan22;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentDefendingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentUnitGettingBlocker;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getCurrentlyBlockingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getDeadAttackingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getDeadDefendingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.getUnitsFinishedAttacking;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.isAttackerTurn;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setAttackerTurn;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentDefendingUnits;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentUnitGettingBlocker;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setUnitsFinishedAttacking;

public class ThreadBattleHandler {
    private static Unit unit;
    private static int team;

    public static void battleHandlerThread(Unit unit, int team) {
        ThreadBattleHandler.unit = unit;
        ThreadBattleHandler.team = team;
        selectUnit(ThreadBattleHandler.unit, ThreadBattleHandler.team);
    }

    private static void selectUnit(Unit unit, int team) {
        if (getDeadDefendingUnits().contains(unit) || getDeadAttackingUnits().contains(unit)) {
            ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " is Dead");
            ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " is Dead");
        } else if (DataProviderBattle.isBlocking()) {
            if (DataProviderBattle.getCurrentUnitGettingBlocker() == null) {
                if (getCurrentDefendingUnits().contains(unit)) {
                    DataProviderBattle.setCurrentUnitGettingBlocker(unit);
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFD1C6"));
                } else if (getCurrentlyBlockingUnits().contains(unit)) {
                    System.out.println("That unit is already blocking");
                } else {
                    System.out.println("That unit is not being attacked...");
                }
            } else {
                if (getCurrentDefendingUnits().contains(unit)) {
                    System.out.println("that unit is already being attacked");
                } else if (DataProviderBattle.getCurrentlyBlockingUnits().contains(unit)) {
                    System.out.println("Each unit can only block one attack");
                } else {
                    System.out.println(unit.getName() + " is blocking for " + getCurrentUnitGettingBlocker().getName());
                    getCurrentUnitGettingBlocker().getView().setBackgroundColor(Color.parseColor("#ffffff"));
                    getCurrentlyBlockingUnits().add(unit);
                    unit.setBlocking((byte) 1);
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    getCurrentDefendingUnits().remove(getCurrentUnitGettingBlocker());
                    setCurrentUnitGettingBlocker(null);
                }
            }
        } else if (getCurrentAttackingUnit() == null) {
            //**Attackers turn
            if (isAttackerTurn()) {
                //**Check which teams unit was selected
                if (team == 1) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " is attacking");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " has already attacked");
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText("Not defensive army's turn");
                }
            }
            //**Defenders turn
            else {
                if (team == 0) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " is attacking");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " has already attacked");
                    }
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText("Not offensive army's turn");
                }
            }
        } else {
            //**Allows player to deselect attacking unit
            if (getCurrentAttackingUnit() == unit) {
                setCurrentAttackingUnit(null);
                unit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                if (team == 0) {
                    ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " deselected");
                } else {
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
//                        setBlockers();
                    }
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText(R.string.cant_attack_ownTeam);
                }
            }
            //**Defender team turn
            else {
                if (team == 1) {
                    getCurrentDefendingUnits().add(unit);
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    if (getCurrentDefendingUnits().size() == getCurrentAttackingUnit().getNumberOfAttacks()) {
//                        setBlockers();
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText(R.string.cant_attack_ownTeam);
                }
            }
        }
    }

//    private static void setBlockers() {
//        if (isAttackerTurn()) {
//            ControllerBattleGround.getDefendingTeamNo().setVisibility(View.VISIBLE);
//            ControllerBattleGround.getDefendingTeamYes().setVisibility(View.VISIBLE);
//        } else {
//            ControllerBattleGround.getAttackingTeamNo().setVisibility(View.VISIBLE);
//            ControllerBattleGround.getAttackingTeamYes().setVisibility(View.VISIBLE);
//        }
//        ControllerBattleGround.getAttackingArmyGrid().setEnabled(false);
//        ControllerBattleGround.getDefendingArmyGrid().setEnabled(false);
//    }

    private static void rollDieForAttack() {
        Random rand = new Random();
        float totalAttack = 0;
        float attackPerUnit;
        for (int i = 0; i < getCurrentAttackingUnit().getAttack6(); i++) {
            totalAttack += rand.nextInt(6) + 1;
        }
        for (int i = 0; i < getCurrentAttackingUnit().getAttack20(); i++) {
            totalAttack += rand.nextInt(20) + 1;
        }
        attackPerUnit = totalAttack / (float) getCurrentAttackingUnit().getNumberOfAttacks();
        for (Unit defender : getCurrentDefendingUnits()) {
            applyDamage(defender, attackPerUnit, false);
        }
//        for (Unit blocker : getCurrentlyBlockingUnits()) {
//            applyDamage(blocker, attackPerUnit, true);
//        }
    }

    private static void applyDamage(Unit defender, float attack, boolean block) {
//        if (!block) {
            Random rand = new Random();
            int settlementEvasionBonus = 0;
            if (team == 0) {
                settlementEvasionBonus = DataProviderSettlementDefence.getSettlementEvasionBonus();
            }
            if ((rand.nextInt(100) + 1) < (defender.getEvasion() + settlementEvasionBonus)) {
                //** add to app display
                System.out.println(defender.getName() + " evaded attack");
            }
            else {
                defender.setBlocking((byte) 0);
                defender.setLife((defender.getLife() - ((float) (1 - (defender.getDefense() / 100.0)) * attack)));
                if (defender.getLife() < 0.01) {
                    if (team == 0) {
                        getDeadDefendingUnits().add(defender);
                    } else {
                        getDeadAttackingUnits().add(defender);
                    }
                }
            }
//        } else {
//            defender.setBlocking((byte) 0);
//            defender.setLife((defender.getLife() - ((float) (1 - (defender.getDefense() / 100.0)) * attack)));
//            if (defender.getLife() < 0.01) {
//                if (team == 0) {
//                    getDeadDefendingUnits().add(defender);
//                } else {
//                    getDeadAttackingUnits().add(defender);
//                }
//            }
//        }
    }

    static void attack() {
        rollDieForAttack();
        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
        getCurrentAttackingUnit().getView().setBackgroundColor(Color.parseColor("#ffffff"));
        for (Unit defendingUnit : getCurrentDefendingUnits()) {
            defendingUnit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
        }
//        for (Unit blockingUnit: getCurrentlyBlockingUnits()){
//            blockingUnit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
//        }
        if (isAttackerTurn()) {
            ControllerBattleGround.getOffensiveTeamText().setText(getCurrentAttackingUnit().getName() + " Attacks");
            if ((getUnitsFinishedAttacking().size() + getDeadAttackingUnits().size()) == DataProviderArmies.getArmies().getAttackingTeamUnits().size()) {
                setAttackerTurn(false);
                setUnitsFinishedAttacking(new ArrayList<Unit>());
                ControllerBattleGround.getOffensiveTeamText().setText("Defensive army's turn");
                ControllerBattleGround.getDefensiveTeamText().setText("Defensive Army");
            }
        } else {
            ControllerBattleGround.getDefensiveTeamText().setText(getCurrentAttackingUnit().getName() + " Attacks");
            if ((getUnitsFinishedAttacking().size() + getDeadDefendingUnits().size()) == DataProviderArmies.getArmies().getDefendingTeamUnits().size()) {
                setAttackerTurn(true);
                setUnitsFinishedAttacking(new ArrayList<Unit>());
                ControllerBattleGround.getOffensiveTeamText().setText(R.string.Offensive_Army);
                ControllerBattleGround.getDefensiveTeamText().setText(R.string.Offensive_army_turn);
            }
        }
        getCurrentlyBlockingUnits().clear();
        getCurrentDefendingUnits().clear();
        setCurrentAttackingUnit(null);
//        ControllerBattleGround.getAttackingArmyGrid().setEnabled(true);
//        ControllerBattleGround.getDefendingArmyGrid().setEnabled(true);
    }
}
