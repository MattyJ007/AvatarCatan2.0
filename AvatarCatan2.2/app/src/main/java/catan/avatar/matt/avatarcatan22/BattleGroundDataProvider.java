package catan.avatar.matt.avatarcatan22;
public class BattleGroundDataProvider {
    public static String getNumAttacks(Unit unit){
        String numAttacks = "";
        for (int i = 0; i<unit.getNumberOfAttacks(); i++){
            numAttacks += "●";
        }
        return numAttacks;
    }

    public static String blocking(Unit unit){
        if (unit.getBlocking() == 0){
            return "";
        }
        else {
            return "[BLOCKING]";
        }
    }
}
