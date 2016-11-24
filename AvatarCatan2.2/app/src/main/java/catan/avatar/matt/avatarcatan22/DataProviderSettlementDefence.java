package catan.avatar.matt.avatarcatan22;
public class DataProviderSettlementDefence {
    private static int settlementEvasionBonus = 0;

    public static int getSettlementEvasionBonus() {
        return settlementEvasionBonus;
    }

    public static void setSettlementEvasionBonus(int settlementEvasionBonus) {
        DataProviderSettlementDefence.settlementEvasionBonus = settlementEvasionBonus;
    }
}
