package catan.avatar.matt.avatarcatan22;
public class DataProviderSettlementDefence {
    private static byte settlementEvasionBonus = 0;

    public static byte getSettlementEvasionBonus() {
        return settlementEvasionBonus;
    }

    public static void setSettlementEvasionBonus(byte settlementEvasionBonus) {
        DataProviderSettlementDefence.settlementEvasionBonus = settlementEvasionBonus;
    }
}
