package catan.avatar.matt.avatarcatan22;

import java.util.List;

public class ThreadAssembleTeam extends Thread{
    private static List<Unit> teamUnits;
    private static Unit unit;

    public ThreadAssembleTeam(List<Unit> teamUnits, Unit unit) {
        ThreadAssembleTeam.teamUnits = teamUnits;
        ThreadAssembleTeam.unit = unit;
    }
    @Override
    public void run() {
        synchronized (teamUnits){
            synchronized (unit){
                addUnit(unit, teamUnits);
            }
        }
    }
    private static void addUnit(Unit unit, List<Unit> teamUnits){
        synchronized (teamUnits) {
            synchronized (unit) {
                    Unit unit1;
                    try {
                        if (unit.getHero() == 0) {
                            unit1 = (Unit) unit.clone();
                            teamUnits.add(unit1);
                        } else if (!DataProviderArmies.getArmies().getDefendingTeamUnits().contains(unit) && !DataProviderArmies.getArmies().getAttackingTeamUnits().contains(unit)){
                            teamUnits.add(unit);
                        }
                    } catch (CloneNotSupportedException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }
}
