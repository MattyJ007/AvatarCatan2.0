package catan.avatar.matt.avatarcatan22;

import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ControllerBattleGround {
    private static TextView offensiveTeamText;
    private static TextView defensiveTeamText;
    private static Button defendingTeamYes;
    private static Button defendingTeamNo;
    private static Button attackingTeamYes;
    private static Button attackingTeamNo;
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

    public static Button getDefendingTeamNo() {
        return defendingTeamNo;
    }

    public static void setDefendingTeamNo(Button defendingTeamNo) {
        ControllerBattleGround.defendingTeamNo = defendingTeamNo;
    }

    public static Button getAttackingTeamYes() {
        return attackingTeamYes;
    }

    public static void setAttackingTeamYes(Button attackingTeamYes) {
        ControllerBattleGround.attackingTeamYes = attackingTeamYes;
    }

    public static Button getAttackingTeamNo() {
        return attackingTeamNo;
    }

    public static void setAttackingTeamNo(Button attackingTeamNo) {
        ControllerBattleGround.attackingTeamNo = attackingTeamNo;
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
}
