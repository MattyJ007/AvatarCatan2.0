package catan.avatar.matt.avatarcatan22;
public class SettlementDefenceProvider {
    private static byte settlementEvasionBonus = 0;

    public static byte getSettlementEvasionBonus() {
        return settlementEvasionBonus;
    }

    public static void setSettlementEvasionBonus(byte settlementEvasionBonus) {
        SettlementDefenceProvider.settlementEvasionBonus = settlementEvasionBonus;
    }
}
