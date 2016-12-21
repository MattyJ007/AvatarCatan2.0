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
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setAttackerTurn;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentAttackingUnit;
import static catan.avatar.matt.avatarcatan22.DataProviderBattle.setCurrentDefendingUnits;
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
        } else if (getCurrentAttackingUnit() == null) {
            //**Attackers turn
            if (isAttackerTurn()) {
                //**Check which teams unit was selected
                if (team == 1) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        ControllerBattleGround.getDefInfoText().setText(unit.getName() + " is attacking");
                        ControllerBattleGround.getOffensiveTeamText().setText("Choose unit to attack");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " has already attacked");
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText("Not your turn");
                }
            }
            //**Defenders turn
            else {
                if (team == 0) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        ControllerBattleGround.getAttInfoText().setText(unit.getName() + " is attacking");
                        ControllerBattleGround.getDefensiveTeamText().setText("Choose unit to attack");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " has already attacked");
                    }
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText("Not your turn");
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
                        attack(getCurrentAttackingUnit(), getCurrentDefendingUnits(), false);
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
                        attack(getCurrentAttackingUnit(), getCurrentDefendingUnits(), false);
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText(R.string.cant_attack_ownTeam);
                }
            }
        }
    }

    private static float rollDieForAttack(Unit currentAttackingUnit) {
        Random rand = new Random();
        float totalAttack = 0;
        float attackPerUnit;
        for (int i = 0; i < currentAttackingUnit.getAttack6(); i++) {
            totalAttack += rand.nextInt(6) + 1;
        }
        for (int i = 0; i < currentAttackingUnit.getAttack20(); i++) {
            totalAttack += rand.nextInt(20) + 1;
        }
        attackPerUnit = totalAttack / (float) currentAttackingUnit.getNumberOfAttacks();
        return attackPerUnit;
    }

    private static void applyDamage(Unit defender, float attack, boolean retaliation, String attackerName) {
        Random rand = new Random();
        int settlementEvasionBonus = 0;

        //** Needs adjustment for retaliation
        if (team == 0 && !retaliation) {
            settlementEvasionBonus = DataProviderSettlementDefence.getSettlementEvasionBonus();
        }
        if (team == 1 && retaliation){
            settlementEvasionBonus = DataProviderSettlementDefence.getSettlementEvasionBonus();
        }
        if ((rand.nextInt(100) + 1) < (defender.getEvasion() + settlementEvasionBonus)) {
            ControllerBattleGround.getDefensiveTeamText().setText(defender.getName() + " evaded an attack");
            ControllerBattleGround.getOffensiveTeamText().setText(defender.getName() + " evaded an attack");
            if (!retaliation) {
                ArrayList<Unit> attackingUnit = new ArrayList<>();
                attackingUnit.add(getCurrentAttackingUnit());
                attack(defender, attackingUnit, true);
            } else {
                if (!getUnitsFinishedAttacking().contains(getCurrentAttackingUnit())) {
                    getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                }
            }
        } else {

            byte damage = (byte) Math.round(((float) (1 - (defender.getDefense() / 100.0)) * attack));
            if (damage == 0) {
                damage = (byte) 1;
            }
            defender.setLife((byte) (defender.getLife() - damage));
            if (retaliation) {
                ControllerBattleGround.getAttInfoText().setText(attackerName + " hits " + defender.getName() + " for " + damage);
                ControllerBattleGround.getDefInfoText().setText(attackerName + " hits " + defender.getName() + " for " + damage);
            } else {
                ControllerBattleGround.getOffensiveTeamText().setText(attackerName + " hits " + defender.getName() + " for " + damage);
                ControllerBattleGround.getDefensiveTeamText().setText(attackerName + " hits " + defender.getName() + " for " + damage);
            }
            if (defender.getLife() < 1) {
                if (!retaliation) {
                    if (team == 0) {
                        if(!getDeadDefendingUnits().contains(defender)){
                            getDeadDefendingUnits().add(defender);
                        }
                    } else {
                        if(!getDeadAttackingUnits().contains(defender)){
                            getDeadAttackingUnits().add(defender);
                        }
                    }
                    if (!getUnitsFinishedAttacking().contains(getCurrentAttackingUnit())) {
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                    }
                } else {
                    if (team == 1 && !getDeadDefendingUnits().contains(defender)) {
                        if(getUnitsFinishedAttacking().contains(defender)){
                            getUnitsFinishedAttacking().remove(defender);
                        }
                        if(!getDeadDefendingUnits().contains(defender)){
                            getDeadDefendingUnits().add(defender);
                        }
                    } else if (team == 0 && !getDeadAttackingUnits().contains(defender)) {
                        if(getUnitsFinishedAttacking().contains(defender)){
                            getUnitsFinishedAttacking().remove(defender);
                        }
                        if(getDeadDefendingUnits().contains(defender)){
                            getDeadAttackingUnits().add(defender);
                        }
                    }
                }
            } else {
                if (!retaliation) {
                    ArrayList<Unit> attackingUnit = new ArrayList<>();
                    attackingUnit.add(getCurrentAttackingUnit());
                    attack(defender, attackingUnit, true);
                } else {
                    if (!getUnitsFinishedAttacking().contains(getCurrentAttackingUnit())) {
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
                    }
                }
            }
        }
    }

    private static void attack(Unit attacking, ArrayList<Unit> defending, boolean retaliation) {
        float attackPerUnit = rollDieForAttack(attacking);
        for (Unit defender : defending) {
            if (retaliation) {
                float retaliateAttack = rollDieForAttack(defender);
                attackPerUnit = retaliateAttack * defender.getNumberOfAttacks();
                applyDamage(getCurrentAttackingUnit(), retaliateAttack, retaliation, attacking.getName());
            } else {
                applyDamage(defender, attackPerUnit, retaliation, attacking.getName());
            }
        }

        if (!retaliation) {
            attacking.getView().setBackgroundColor(Color.parseColor("#ffffff"));
            for (Unit defendingUnit : defending) {
                defendingUnit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
            }
            System.out.println(" fin def att " + getUnitsFinishedAttacking().size() + " dead " + getDeadDefendingUnits().size() + " team " + DataProviderArmies.getArmies().getDefendingTeamUnits().size());

            System.out.println(" fin attack " + getUnitsFinishedAttacking().size() + " dead " + getDeadAttackingUnits().size() + " team " + DataProviderArmies.getArmies().getAttackingTeamUnits().size());
            if (isAttackerTurn()) {
                if ((getUnitsFinishedAttacking().size() + getDeadAttackingUnits().size()) == DataProviderArmies.getArmies().getAttackingTeamUnits().size()) {
                    setAttackerTurn(false);
                    ControllerBattleGround.getCenterText().setRotation(0);
                    setUnitsFinishedAttacking(new ArrayList<Unit>());
                }
            } else {
                if ((getUnitsFinishedAttacking().size() + getDeadDefendingUnits().size()) == DataProviderArmies.getArmies().getDefendingTeamUnits().size()) {
                    setAttackerTurn(true);

                    ControllerBattleGround.getCenterText().setRotation(180);
                    setUnitsFinishedAttacking(new ArrayList<Unit>());
                }
            }
            defending.clear();
            setCurrentAttackingUnit(null);
        }
    }
}
