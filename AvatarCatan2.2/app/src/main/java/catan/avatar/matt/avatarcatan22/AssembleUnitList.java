package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssembleUnitList extends Thread{
    private static HashMap<String, List<Unit>> unitHashMap;
    private static List<Unit> mainUnitsList;

    public AssembleUnitList(List<Unit> mainUnitsList, HashMap<String, List<Unit>> unitHashMap){
        AssembleUnitList.mainUnitsList = mainUnitsList;
        AssembleUnitList.unitHashMap = unitHashMap;
    }
    @Override
    public void run() {
        synchronized (mainUnitsList){
            synchronized (unitHashMap) {
                setUnitsLists(unitHashMap, mainUnitsList);
            }
        }
    }
    private static String[] units = { "Fire Nation Guy,fng,8,Town (Earth),1,0,1,10,0,0,5,2,1",
            "Apprentice Water,awa,4,Village (Water),2,0,1,5,3,1,11,4,0",
            "Apprentice Earth,aea,8,Village (Earth),2,0,1,10,0,1,12,4,0",
            "Apprentice Air,aai,11,Village (Air),2,0,1,5,12,2,11,4,0",
            "Apprentice Fire,afi,1,Village (Fire),3,0,1,0,0,1,10,5,0",
            "Guru Pathik,gur,12,Air Temple,2,0,1,20,25,10,10,5,1",
            "Hippo,hip,8,Town (Earth),2,0,1,5,0,0,23,6,1",
            "Sokka,sok,12,Southern Water Tribe,2,0,1,10,6,9,20,6,1",
            "Boulder,bou,8,Town (Earth),2,0,1,5,0,1,23,6,1",
            "Asami,asa,12,City,0,1,1,0,6,9,15,6,1",
            "Suki,suk,12,Village,2,0,1,10,12,7,20,7,1",
            "Jet,jet,12,Village & Not Fire Nation,2,0,1,10,9,8,25,8,1",
            "June,jun,12,Village,2,0,1,10,12,7,25,8,1",
            "Jinora,jin,11,City,4,0,2,5,24,8,15,8,1",
            "Master Air,mair,11,Town (Air),4,0,2,10,30,6,15,8,0",
            "Master Fire,mfi,2,Town (Fire),2,1,1,5,3,6,15,9,0",
            "Master Earth,mea,9,Town (Earth),1,1,1,20,3,5,18,9,0",
            "Master Water,mwa,5,Town (Water),5,0,2,15,15,6,17,9,0",
            "Spirit,spi,14,Spirit Defender Card,6,0,2,30,24,8,10,10,0",
            "Mako,mak,2,City,0,1,1,20,28,6,20,10,1",
            "Bolin,bol,10,City,1,1,1,25,15,6,20,10,1",
            "Mai,mai,12,City,1,1,1,0,15,7,25,10,1",
            "Sword Master Piandao,pia,12,Town (Fire),2,1,2,25,9,9,20,11,1",
            "Swamp Monster,huu,4,Village (Water),4,0,2,50,6,8,25,12,1",
            "Katara,kat,6,Southern Water Tribe,6,0,3,20,18,7,20,12,1",
            "Combustion Man,com,3,2 Cities (Fire + Any),0,3,1,40,12,3,5,12,1",
            "Jeong Jeong,jeo,1,Town & Not Fire Nation,1,2,2,15,18,8,15,12,1",
            "Chief Lin Be Fong,lin,9,City,5,0,2,30,15,6,25,12,1",
            "Paku,pak,5,City (Water),5,1,3,15,9,7,15,12,1",
            "Ty Lee,tyl,13,City,0,2,1,20,21,7,20,13,1",
            "P'Li,pli,3,2 Cities (Fire + Any),0,3,1,0,15,6,10,13,1",
            "Tenzin,ten,11,City (Any) & Village (Air),4,1,3,20,12,8,20,13,1",
            "Admiral Zhou,zho,1,Kill Moon Spirit Card,0,2,1,20,6,6,25,13,1",
            "Knowledge Spirit,kno,14,Village (Earth) on Dessert Tile & Knowledge Spirit Card ,7,0,1,10,6,10,25,13,1",
            "Gharzan,gha,10,2 Cities (Fire + Earth),0,3,4,10,6,5,17,14,1",
            "Gyatso,gya,11,Air Temple,10,0,5,15,12,10,15,14,1",
            "Toph,top,9,City (Earth),1,2,2,10,21,7,25,14,1",
            "Zuko,zuk,1,City,0,2,2,30,12,7,30,15,1",
            "Azula,azu,2,2 Cities (Fire),1,2,2,10,32,9,25,16,1",
            "Kuvira,kuv,9,3 Cities (1 Earth & 2 Any),5,1,2,30,20,9,25,16,1",
            "Tarlock,tar,7,City,1,3,2,30,30,5,15,17,1",
            "Bumi,bum,8,Oma Shu,0,3,3,30,15,8,30,19,1",
            "Korra,kor,15,4 Settlements (Air +Earth + Fire + Water)  & City (Water or Fire),2,3,1,30,0,5,30,20,1",
            "Unalaq,una,15,Northern Water Tribe & Spirit Portal & Player does not control Avatar,2,3,1,25,7,6,30,20,1",
            "Ozia,ozi,2,Fire Nation Capital or Ember Island,2,3,3,30,15,8,30,21,1",
            "Iroh,iro,2,3 Cities (Fire + Water + Earth),2,3,3,30,15,9,30,21,1",
            "Zaheer,zah,11,Village (Air) & Spirit Portal Open & Player does not control the Avatar,6,2,3,30,24,8,26,22,1",
            "Hei Bai Spirit,hei,14,Village containing a spirit portal & Hei Bai Assist Card,0,4,1,25,15,7,30,22,1",
            "Avatar Kuruk,kur,15,3 Settlements (Air + Earth+ Fire) & City (Water),6,3,2,20,9,4,25,23,1",
            "Avatar Yan Chen,yan,15,3 Settlements (Earth+ Fire + Water) & an Air Temple,12,1,5,15,25,8,23,23,1",
            "Amon,amo,7,4 Cities (1 Water + 3 Any) & Player does not control the Avatar,0,4,4,50,40,8,19,23,1",
            "Aang,aan,15,3 Settlements (Air + Earth+ Fire) & (Southern Air Temple or Southern Water Tribe),4,3,3,20,24,7,30,23,1",
            "Avatar Wan,wan,15,4 Lion Turtle Towns,5,3,2,25,15,6,30,24,1",
            "Roku,rok,15,3 Settlements (Air + Earth+Water) & (Fire Nation Capital or Ember Island),3,4,3,25,18,9,25,24,1",
            "Face Stealer Koh,koh,14,Spirit Portal Open & Koh Assist Card,0,4,1,50,3,7,40,25,1",
            "Kyoshi,kyo,15,3 Settlements (Air + Fire + Water) & (Ba Sing Se or Omashu),3,4,3,35,9,7,35,27,1"
    };
    private static void setUnitsLists(HashMap<String, List<Unit>> unitHashMap, List<Unit>mainUnitsList){
        synchronized (mainUnitsList){
            synchronized (unitHashMap) {
                HashMap<Byte, String> bendingtypes = new HashMap<>();
                {
                    bendingtypes.put((byte) 1, "Fire");
                    bendingtypes.put((byte) 2, "Fire & Lightning");
                    bendingtypes.put((byte) 3, "Combustion");
                    bendingtypes.put((byte) 4, "Water");
                    bendingtypes.put((byte) 5, "Water & Ice");
                    bendingtypes.put((byte) 6, "Water, Ice & Blood");
                    bendingtypes.put((byte) 7, "Psychic Blood");
                    bendingtypes.put((byte) 8, "Earth");
                    bendingtypes.put((byte) 9, "Earth & Metal");
                    bendingtypes.put((byte) 10, "Earth & Lava");
                    bendingtypes.put((byte) 11, "Air");
                    bendingtypes.put((byte) 12, "None - Melee/Ranged/Hand to Hand Combat");
                    bendingtypes.put((byte) 13, "None - Chi Blocker");
                    bendingtypes.put((byte) 14, "None - Spirit");
                    bendingtypes.put((byte) 15, "All Elements & Spirit");

                }
                String name, image, minRequirement, bengingType;
                String[] unitStats;
                List<Unit> statsList;
                byte attack6, attack20, gold, hero, evasion, defense, life, intelligence, numberOfAttacks, type;
                Unit u;
                for (String unit1 : units) {
                    statsList = new ArrayList<>();
                    unitStats = unit1.split(",");
                    name = unitStats[0];
                    image = unitStats[1];
                    type = Byte.parseByte(unitStats[2]);
                    bengingType = bendingtypes.get(type);
                    minRequirement = unitStats[3];
                    attack6 = Byte.parseByte(unitStats[4]);
                    attack20 = Byte.parseByte(unitStats[5]);
                    numberOfAttacks = Byte.parseByte(unitStats[6]);
                    defense = Byte.parseByte(unitStats[7]);
                    evasion = Byte.parseByte(unitStats[8]);
                    intelligence = Byte.parseByte(unitStats[9]);
                    life = Byte.parseByte(unitStats[10]);
                    gold = Byte.parseByte(unitStats[11]);
                    hero = Byte.parseByte(unitStats[12]);
                    u = new Unit(name, image, type, minRequirement, attack6, attack20, numberOfAttacks, defense, evasion, intelligence, life, gold, hero, bengingType);
                    statsList.add(u);
                    mainUnitsList.add(u);
                    unitHashMap.put(name, statsList);
                }
            }
        }
    }
}
