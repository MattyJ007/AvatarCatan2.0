package catan.avatar.matt.avatarcatan22;

import java.util.List;

public class AssembleTeam extends Thread{
    private static List<Unit> teamUnits;
    private static Unit unit;
    public AssembleTeam(List<Unit> attackingTeamUnits, Unit unit) {
        AssembleTeam.teamUnits = attackingTeamUnits;
        AssembleTeam.unit = unit;
    }
    @Override
    public void run() {
        synchronized (teamUnits){
            synchronized (unit){
                addUnit(teamUnits, unit);
            }
        }
    }
    private static void addUnit(List<Unit> teamUnits, Unit unit){
        synchronized (teamUnits) {
            synchronized (unit) {
                Unit unit1;
                try {
                    if (unit.getHero() == 0) {
                        unit1 = (Unit) unit.clone();
                        teamUnits.add(unit1);
                    } else if (!AttackingTeamDataProvider.getAttackingTeamUnits().contains(unit) && !DefendingTeamDataProvider.getDefendingTeamUnits().contains(unit)) {
                        teamUnits.add(unit);
                    }
                } catch (CloneNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
