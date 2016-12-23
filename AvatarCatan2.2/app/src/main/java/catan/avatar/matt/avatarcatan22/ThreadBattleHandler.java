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
            ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " is Dead\n");
            ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " is Dead\n");
        } else if (getCurrentAttackingUnit() == null) {
            //**Attackers turn
            if (isAttackerTurn()) {
                //**Check which teams unit was selected
                if (team == 1) {
                    if (!getUnitsFinishedAttacking().contains(unit)) {
                        //** Selected Unit is the now attacking
                        setCurrentAttackingUnit(unit);
                        ControllerBattleGround.getDefInfoText().setText(unit.getName() + " is attacking\n");
                        ControllerBattleGround.getOffensiveTeamText().setText("Choose unit to attack:\n");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " has already attacked\n");
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
                        ControllerBattleGround.getAttInfoText().setText(unit.getName() + " is attacking\n");
                        ControllerBattleGround.getDefensiveTeamText().setText("Choose unit to attack:\n");
                        unit.getView().setBackgroundColor(Color.parseColor("#bafff8"));
                    } else {
                        ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " has already attacked\n");
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
                if (team == 0) {
                    ControllerBattleGround.getDefensiveTeamText().setText(unit.getName() + " deselected\n");
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText(unit.getName() + " deselected\n");

                }
                //**removes any units that may have been chosen to be attacked
                setCurrentDefendingUnits(new ArrayList<Unit>());
            }
            //**Offensive team turn - therefore need to select unit from defending side to be attacked
            else if (isAttackerTurn()) {
                if (team == 0) {
                    getCurrentAttackingUnit().setNumberOfAttacksUsed((byte)(getCurrentAttackingUnit().getNumberOfAttacksUsed()+ 1));
                    if(!getCurrentDefendingUnits().contains(unit)){
                        getCurrentDefendingUnits().add(unit);
                    }
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    if (getCurrentAttackingUnit().getNumberOfAttacksUsed() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        attack(getCurrentAttackingUnit(), getCurrentDefendingUnits(), false);
                    }
                } else {
                    ControllerBattleGround.getOffensiveTeamText().setText(R.string.cant_attack_ownTeam);
                }
            }
            //**Defender team turn
            else {
                if (team == 1) {
                    getCurrentAttackingUnit().setNumberOfAttacksUsed((byte)(getCurrentAttackingUnit().getNumberOfAttacksUsed()+ 1));
                    if(!getCurrentDefendingUnits().contains(unit)){
                        getCurrentDefendingUnits().add(unit);
                    }
                    unit.getView().setBackgroundColor(Color.parseColor("#FFFFE2DB"));
                    if (getCurrentAttackingUnit().getNumberOfAttacksUsed() == getCurrentAttackingUnit().getNumberOfAttacks()) {
                        attack(getCurrentAttackingUnit(), getCurrentDefendingUnits(), false);
                    }
                } else {
                    ControllerBattleGround.getDefensiveTeamText().setText(R.string.cant_attack_ownTeam);
                }
            }
        }
    }

    //***************************************************************************************************************************************************************************

    private static float rollDieForAttack(Unit currentAttackingUnit) {
        Random rand = new Random();
        float totalAttack = 0;
        float attackPerUnit;
        currentAttackingUnit.getDamage();
        totalAttack += rand.nextInt((int) (currentAttackingUnit.getDamage() * 0.4)) + (currentAttackingUnit.getDamage() * 0.8);
        attackPerUnit = totalAttack / (float) getCurrentDefendingUnits().size();
        return attackPerUnit;
    }

    private static void applyDamage(Unit defender, float attack, boolean retaliation, Unit attacker) {
        Random rand = new Random();
        int settlementEvasionBonus = 0;

        //** Needs adjustment for retaliation
        if (team == 0 && !retaliation) {
            settlementEvasionBonus = DataProviderSettlementDefence.getSettlementEvasionBonus();
        }
        if (team == 1 && retaliation) {
            settlementEvasionBonus = DataProviderSettlementDefence.getSettlementEvasionBonus();
        }
        int evasion = (int) ((1.0- ((float)attacker.getAccuracy()/(attacker.getAccuracy()+defender.getEvasion()))) * 100);
//        System.out.println(defender.getName() + " Evasion: " + evasion);
        if (evasion > 60) {
            evasion = 60;
        }
        evasion += settlementEvasionBonus;
        int probabilityOfHit = (rand.nextInt(100) + 1);
//        System.out.println("Evasion + settlement bonus: " + evasion + "\nProbability of hit: " + probabilityOfHit);
        if (probabilityOfHit < (evasion)) {
            ControllerBattleGround.getDefensiveTeamText().append(defender.getName() + " evades " + attacker.getName() + "\n");
            ControllerBattleGround.getOffensiveTeamText().append(defender.getName() + " evades " + attacker.getName() + "\n");
            if (!retaliation) {
                ArrayList<Unit> attackingUnit = new ArrayList<>();
                attackingUnit.add(getCurrentAttackingUnit());
                attack(defender, attackingUnit, true);

            }
            if (!getUnitsFinishedAttacking().contains(getCurrentAttackingUnit())) {
                getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
            }

        } else {
            byte actualDamge = (byte) Math.round(((float) (1 - (defender.getDefense() / 100.0)) * attack));
//            System.out.println("Attack: " + attack + "\nActual Damage: " + actualDamge);
            if (actualDamge == 0) {
                actualDamge = (byte) 1;
            }
            defender.setLife((byte) (defender.getLife() - actualDamge));
            ControllerBattleGround.getOffensiveTeamText().append(attacker.getName() + " hits " + defender.getName() + " for " + actualDamge + "\n");
            ControllerBattleGround.getDefensiveTeamText().append(attacker.getName() + " hits " + defender.getName() + " for " + actualDamge + "\n");
            if (defender.getLife() < 1) {
                if (!retaliation) {
                    if (isAttackerTurn()) {
                        if (!getDeadDefendingUnits().contains(defender)) {
                            getDeadDefendingUnits().add(defender);
//                            System.out.println(defender.getName() + " defender dead.");

                        }
                    } else {
                        if (!getDeadAttackingUnits().contains(defender)) {
                            getDeadAttackingUnits().add(defender);
//                            System.out.println(defender.getName() + " attacker dead.");

                        }
                    }
                    if (!getUnitsFinishedAttacking().contains(getCurrentAttackingUnit())) {
                        getUnitsFinishedAttacking().add(getCurrentAttackingUnit());
//                        System.out.println(getCurrentAttackingUnit().getName() + " finished attacking.");

                    }
                } else {
                    if (!isAttackerTurn()) {
                        if (!getDeadDefendingUnits().contains(defender)) {
                            getDeadDefendingUnits().add(defender);
//                            System.out.println(defender.getName() + " defender dead.");

                        }
                    } else {
                        if (!getDeadAttackingUnits().contains(defender)) {
                            getDeadAttackingUnits().add(defender);
//                            System.out.println(defender.getName() + " attacker dead.");

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
//                        System.out.println(getCurrentAttackingUnit().getName() + " finished attacking.");
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
                applyDamage(getCurrentAttackingUnit(), retaliateAttack, retaliation, attacking);
            } else {
                applyDamage(defender, attackPerUnit, retaliation, attacking);
            }
        }

        if (!retaliation) {
            attacking.getView().setBackgroundColor(Color.parseColor("#ffffff"));
            for (Unit defendingUnit : defending) {
                defendingUnit.getView().setBackgroundColor(Color.parseColor("#ffffff"));
            }
            if (isAttackerTurn()) {
                if ((getUnitsFinishedAttacking().size() + getDeadAttackingUnits().size()) == DataProviderArmies.getArmies().getAttackingTeamUnits().size()) {
                    setAttackerTurn(false);
                    ControllerBattleGround.getAttInfoText().clearComposingText();
                    ControllerBattleGround.getCenterText().setRotation(0);
                    getUnitsFinishedAttacking().clear();

                }
            } else {
                if ((getUnitsFinishedAttacking().size() + getDeadDefendingUnits().size()) == DataProviderArmies.getArmies().getDefendingTeamUnits().size()) {
                    setAttackerTurn(true);
                    ControllerBattleGround.getDefInfoText().clearComposingText();
                    ControllerBattleGround.getCenterText().setRotation(180);
                    getUnitsFinishedAttacking().clear();
                }
            }
            defending.clear();
            getCurrentAttackingUnit().setNumberOfAttacksUsed((byte) 0);
            setCurrentAttackingUnit(null);
        }
    }
}
