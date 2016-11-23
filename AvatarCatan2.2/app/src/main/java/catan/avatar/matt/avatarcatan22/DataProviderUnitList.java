package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProviderUnitList {
    private static HashMap<String, List<Unit>> unitHashMap = new HashMap<>();
    private static List<Unit> mainUnitsList = new ArrayList<>();

    public static void setUnitData(){
        if (mainUnitsList.isEmpty()){
            ThreadAssembleUnitList thread = new ThreadAssembleUnitList(mainUnitsList, unitHashMap);
            thread.start();
        }
    }

    static HashMap<String, List<Unit>> getUnitHashMap(){
            return unitHashMap;
    }

    static List<Unit> getMainUnitsList() {
            return mainUnitsList;
    }
}
