package catan.avatar.matt.avatarcatan22;

import java.util.List;

public class AssembleAttackTeam extends Thread{
    private static List<Unit> attackingTeamUnits;
    private static Unit unit;
    public AssembleAttackTeam(List<Unit> attackingTeamUnits, Unit unit) {
        AssembleAttackTeam.attackingTeamUnits = attackingTeamUnits;
        AssembleAttackTeam.unit = unit;
    }
    @Override
    public void run() {
        synchronized (attackingTeamUnits){
            synchronized (unit){
                addUnit(attackingTeamUnits, unit);
            }
        }
    }
    private static void addUnit(List<Unit> attackingTeamUnits, Unit unit){
        synchronized (attackingTeamUnits) {
            synchronized (unit) {
                Unit unit1;
                try {
                    if (unit.getHero() == 0) {
                        unit1 = (Unit) unit.clone();
                        attackingTeamUnits.add(unit1);
                    } else if (!attackingTeamUnits.contains(unit)) {
                        attackingTeamUnits.add(unit);
                    }
                } catch (CloneNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
