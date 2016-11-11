package catan.avatar.matt.avatarcatan22;
public class ArmiesDataProvider {

    private static Armies armies;

    public static void setArmies(){
        armies = new Armies();
    }
    public static Armies getArmies(){
        return armies;
    }
}
