package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.List;

public class DefendingTeamDataProvider {
    private static List<Unit> defendingTeamUnits = new ArrayList<>();

    public static void setTeamMember(Unit unit) {
        AssembleTeam aTeamThread = new AssembleTeam(defendingTeamUnits, unit);
        aTeamThread.start();
    }
    public static void removeTeamMember(Unit unit){
        synchronized (defendingTeamUnits){
            defendingTeamUnits.remove(defendingTeamUnits.indexOf(unit));
        }
    }
    static List<Unit> getDefendingTeamUnits() {
        return defendingTeamUnits;
    }
}
