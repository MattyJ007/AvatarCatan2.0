package catan.avatar.matt.avatarcatan22;

import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ControllerBattleGround {
    private static TextView offensiveTeamText;
    private static TextView defensiveTeamText;
    private static TextView centerText;
    private static TextView attInfoText;
    private static TextView defInfoText;
    private static Button defendingTeamYes;
    private static Button attackingTeamYes;
    private static GridView attackingArmyGrid;
    private static GridView defendingArmyGrid;

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

    public static Button getDefendingTeamYes() {
        return defendingTeamYes;
    }

    public static void setDefendingTeamYes(Button defendingTeamYes) {
        ControllerBattleGround.defendingTeamYes = defendingTeamYes;
    }

    public static Button getAttackingTeamYes() {
        return attackingTeamYes;
    }

    public static void setAttackingTeamYes(Button attackingTeamYes) {
        ControllerBattleGround.attackingTeamYes = attackingTeamYes;
    }

    public static GridView getAttackingArmyGrid() {
        return attackingArmyGrid;
    }

    public static void setAttackingArmyGrid(GridView attackingArmyGrid) {
        ControllerBattleGround.attackingArmyGrid = attackingArmyGrid;
    }

    public static GridView getDefendingArmyGrid() {
        return defendingArmyGrid;
    }

    public static void setDefendingArmyGrid(GridView defendingArmyGrid) {
        ControllerBattleGround.defendingArmyGrid = defendingArmyGrid;
    }

    public static TextView getAttInfoText() {
        return attInfoText;
    }

    public static void setAttInfoText(TextView attInfoText) {
        ControllerBattleGround.attInfoText = attInfoText;
    }

    public static TextView getDefInfoText() {
        return defInfoText;
    }

    public static void setDefInfoText(TextView defInfoText) {
        ControllerBattleGround.defInfoText = defInfoText;
    }

    public static TextView getCenterText() {
        return centerText;
    }

    public static void setCenterText(TextView centerText) {
        ControllerBattleGround.centerText = centerText;
    }
}
