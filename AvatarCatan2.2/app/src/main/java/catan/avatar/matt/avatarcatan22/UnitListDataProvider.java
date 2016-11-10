package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitListDataProvider {
    private static HashMap<String, List<Unit>> unitHashMap = new HashMap<>();
    private static List<Unit> mainUnitsList = new ArrayList<>();

    public static void setUnitData(){
        AssembleUnitList thread = new AssembleUnitList(mainUnitsList, unitHashMap);
        thread.start();
    }

    static HashMap<String, List<Unit>> getUnitHashMap(){
            return unitHashMap;
    }

    static List<Unit> getMainUnitsList() {
            return mainUnitsList;
    }
}
