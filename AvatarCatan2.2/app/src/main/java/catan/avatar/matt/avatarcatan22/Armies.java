package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.List;

public class Armies {
    public Armies(){
        defendingTeamUnits = new ArrayList<>();
        attackingTeamUnits = new ArrayList<>();
    }

    private List<Unit> defendingTeamUnits;

    private List<Unit> attackingTeamUnits;

    public void setTeamMember(Unit unit, int team) {
        if (team == 0){
            AssembleTeam dTeamThread = new AssembleTeam(defendingTeamUnits, unit);
            dTeamThread.start();
        }
        else{
            AssembleTeam aTeamThread = new AssembleTeam(attackingTeamUnits, unit);
            aTeamThread.start();
        }
    }
    public void removeTeamMember(Unit unit, int team){
        if(team == 0){
            synchronized (defendingTeamUnits){
                defendingTeamUnits.remove(defendingTeamUnits.indexOf(unit));
            }
        }
        else {
            synchronized (attackingTeamUnits){
                attackingTeamUnits.remove(attackingTeamUnits.indexOf(unit));
            }
        }
    }

    public List<Unit> getAttackingTeamUnits() {
        return attackingTeamUnits;
    }
    public List<Unit> getDefendingTeamUnits() {
        return defendingTeamUnits;
    }
}
