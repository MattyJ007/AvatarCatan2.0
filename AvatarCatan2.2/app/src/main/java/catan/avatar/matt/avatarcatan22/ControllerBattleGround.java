package catan.avatar.matt.avatarcatan22;

import android.widget.TextView;

public class ControllerBattleGround {
    private static TextView offensiveTeamText;
    private static TextView defensiveTeamText;

    public static TextView getOffensiveTeamText() {
        return offensiveTeamText;
    }

    public static void setOffensiveTeamText(TextView offensiveTeamText) {
        ControllerBattleGround.offensiveTeamText = offensiveTeamText;
    }

    public static TextView getDefensiveTeamText() {
        return defensiveTeamText;
    }

    public static void setDefensiveTeamText(TextView defensiveTeamText) {
        ControllerBattleGround.defensiveTeamText = defensiveTeamText;
    }
}
