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

class Attack {
    private static int team = 0;
    private static float rollDieForAttack(Unit currentAttackingUnit) {
        Random rand = new Random();
        float totalAttack = 0;
        float damagePerUnit;
        currentAttackingUnit.getDamage();
        totalAttack += rand.nextInt((int) (currentAttackingUnit.getDamage() * 0.4)) + (currentAttackingUnit.getDamage() * 0.8);
        damagePerUnit = totalAttack / (float) getCurrentDefendingUnits().size();
        return damagePerUnit;
    }

    private static void applyDamage(Unit defender, float damage, boolean retaliation, Unit attacker) {
        if (attacker.getStatus().contains((byte) 2)){
            ControllerBattleGround.getDefensiveTeamText().append(attacker.getName() + " is Stunned");
            ControllerBattleGround.getOffensiveTeamText().append(attacker.getName() + " is Stunned");
        }else if(attacker.getStatus().contains((byte) 3)){
            ControllerBattleGround.getDefensiveTeamText().append(attacker.getName() + " is Paralysed");
            ControllerBattleGround.getOffensiveTeamText().append(attacker.getName() + " is Paralysed");
        }else {
            Random rand = new Random();
            int settlementEvasionBonus = 0;

            //** Needs adjustment for retaliation
            if (team == 0 && !retaliation) {
                settlementEvasionBonus = DataProviderSettlementDefence.getSettlementEvasionBonus();
            }
            if (team == 1 && retaliation) {
                settlementEvasionBonus = DataProviderSettlementDefence.getSettlementEvasionBonus();
            }
            float accuracy = attacker.getAccuracy();
            float defEvasion = defender.getEvasion();
            for (byte status : attacker.getStatus()) {
                if (status == (byte) 5) {
                    accuracy *= 0.8;
                }
            }
            for (byte status : defender.getStatus()) {
                if (status == (byte) 2 || status == (byte) 3) {
                    defEvasion = 1;
                }
            }
            int evasion = (int) ((1.0 - (accuracy / (accuracy + defEvasion))) * 100);
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
                    attack(defender, attackingUnit, true, team);

                }
            } else {
                byte actualDamage;
                float defense = defender.getDefense();
                for (byte status : attacker.getStatus()) {
                    if (status == (byte) 4) {
                        damage *= 0.25;
                    }
                    if (status == (byte) 5) {
                        damage *= 0.5;
                    }
                }
                if (defender.getStatus().contains((byte) 2)) {
                    defense -= 25;
                }
                actualDamage = (byte) Math.round(((float) (1 - (defense / 100.0)) * damage));
                if (attacker.getAbility().equals("Critical Hit")) {
                    if (rand.nextInt(20) + 1 == 20) {
                        String critHit = "Critical hit by " + attacker.getName();
                        ControllerBattleGround.getDefInfoText().setText(critHit);
                        ControllerBattleGround.getAttInfoText().setText(critHit);
                        actualDamage *= 2.5;
                    }
                } else if (defender.getAbility().equals("Redirect Lightning") && attacker.getType() == (byte) 2) {
                    String redirectLightning = defender.getName() + " redirects Lightning";
                    ControllerBattleGround.getDefInfoText().setText(redirectLightning);
                    ControllerBattleGround.getAttInfoText().setText(redirectLightning);

                    actualDamage /= 2;
                }
                if (actualDamage == 0) {
                    actualDamage = (byte) 1;
                }
                defender.setLife((byte) (defender.getLife() - actualDamage));
                ControllerBattleGround.getOffensiveTeamText().append(attacker.getName() + " hits " + defender.getName() + " for " + actualDamage + "\n");
                ControllerBattleGround.getDefensiveTeamText().append(attacker.getName() + " hits " + defender.getName() + " for " + actualDamage + "\n");
                implementAbility(attacker, defender);
                if (defender.getLife() < 1) {
                    defender.getStatus().clear();
                    defender.getStatus().add((byte) 0);
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
                        getUnitsFinishedAttacking().remove(defender);
                    }
                } else {
                    if (!retaliation) {
                        ArrayList<Unit> attackingUnit = new ArrayList<>();
                        attackingUnit.add(getCurrentAttackingUnit());
                        attack(defender, attackingUnit, true , team);
                    }
                }
            }
        }
    }

    private static void implementAbility(Unit attacker, Unit defender) {
        Random rand = new Random();
        int statusChange = rand.nextInt(100) + 1;
        switch (attacker.getAbility()) {
            case "none":
            case "Redirect Lightning":
            case "Critical Hit":

                break;
            case "Stun":
                if (statusChange >= 90) {
                    if (!defender.getStatus().contains((byte) 2)) {
                        defender.getStatus().add((byte) 2);
                    }
                    String stun = defender.getName() + " is Stunned";
                    ControllerBattleGround.getDefInfoText().setText(stun);
                    ControllerBattleGround.getAttInfoText().setText(stun);
                }
                break;
            case "Paralyse":
                if (statusChange >= 70) {
                    if (!defender.getStatus().contains((byte) 3)) {
                        defender.getStatus().add((byte) 3);
                    }
                    String paralysed = defender.getName() + " is Paralysed";
                    ControllerBattleGround.getDefInfoText().setText(paralysed);
                    ControllerBattleGround.getAttInfoText().setText(paralysed);
                }
                break;
            case "Take Away Bending":
                if (defender.getType() != 0 || defender.getType() != 12 || defender.getType() != 13 || defender.getType() != 14) {
                    if (statusChange >= 60) {
                        defender.getStatus().add((byte) 4);
                        String noBending = defender.getName() + " has no Bending";
                        ControllerBattleGround.getDefInfoText().setText(noBending);
                        ControllerBattleGround.getAttInfoText().setText(noBending);
                    }
                }
                break;
            case "Block Chi":
                if (defender.getType() != 14) {
                    if (statusChange >= 40) {
                        defender.getStatus().add((byte) 5);
                        String chiBlock = defender.getName() + "'s Chi is blocked";
                        if (ControllerBattleGround.getDefInfoText().getText().equals(defender.getName() + " is Stunned\n")) {
                            ControllerBattleGround.getDefInfoText().append(chiBlock);
                            ControllerBattleGround.getAttInfoText().append(chiBlock);
                        } else {
                            ControllerBattleGround.getDefInfoText().setText(chiBlock);
                            ControllerBattleGround.getAttInfoText().setText(chiBlock);
                        }
                    }
                }
                break;
            default:
                break;
        }


    }

    static void attack(Unit attacking, ArrayList<Unit> defending, boolean retaliation, int team1) {
        team = team1;
        float damagePerUnit = rollDieForAttack(attacking);
        for (Unit defender : defending) {
            if (retaliation) {
                float retaliateDamage = rollDieForAttack(defender);
                damagePerUnit = retaliateDamage * defender.getNumberOfAttacks();
                applyDamage(getCurrentAttackingUnit(), retaliateDamage, retaliation, attacking);
            } else {
                applyDamage(defender, damagePerUnit, retaliation, attacking);
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
                    resetUnitsAttacks();
                    ControllerBattleGround.getAttInfoText().clearComposingText();
                    ControllerBattleGround.getDefInfoText().clearComposingText();
                    ControllerBattleGround.getCenterText().setRotation(0);
                    getUnitsFinishedAttacking().clear();

                }
            } else {
                if ((getUnitsFinishedAttacking().size() + getDeadDefendingUnits().size()) == DataProviderArmies.getArmies().getDefendingTeamUnits().size()) {
                    setAttackerTurn(true);
                    resetUnitsAttacks();
                    ControllerBattleGround.getAttInfoText().clearComposingText();
                    ControllerBattleGround.getDefInfoText().clearComposingText();
                    ControllerBattleGround.getCenterText().setRotation(180);
                    getUnitsFinishedAttacking().clear();
                }
            }
            defending.clear();
//            getCurrentAttackingUnit().setNumberOfAttacksUsed((byte) 0);
            setCurrentAttackingUnit(null);
        }
    }

    private static void resetUnitsAttacks() {
        if (isAttackerTurn()) {
            for (Unit unit : DataProviderArmies.getArmies().getAttackingTeamUnits()) {
                if (!getDeadAttackingUnits().contains(unit)) {
                    unit.setNumberOfAttacksUsed((byte) 0);
                }
            }
        } else {
            for (Unit unit : DataProviderArmies.getArmies().getDefendingTeamUnits()) {
                unit.setNumberOfAttacksUsed((byte) 0);
            }
        }
    }
}
