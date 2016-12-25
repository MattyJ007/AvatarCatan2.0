package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProviderUnitList {
    private static List<Unit> mainUnitsList = new ArrayList<>();
    public static void setUnitData() {
        if (mainUnitsList.isEmpty()) {
            ThreadAssembleUnitList thread = new ThreadAssembleUnitList(mainUnitsList
            );
            thread.start();
        }
    }
    static List<Unit> getMainUnitsList() {
        return mainUnitsList;
    }
}
