package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.List;

public class AttackingTeamDataProvider{
    private static  List<Unit> attackingTeamUnits = new ArrayList<>();

    public static void setTeamMember(Unit unit) {
        AssembleTeam aTeamThread = new AssembleTeam(attackingTeamUnits, unit);
        aTeamThread.start();
    }
    public static void removeTeamMember(Unit unit){
        synchronized (attackingTeamUnits){
            attackingTeamUnits.remove(attackingTeamUnits.indexOf(unit));
        }
    }
    static List<Unit> getAttackingTeamUnits() {
        return attackingTeamUnits;
    }
}
