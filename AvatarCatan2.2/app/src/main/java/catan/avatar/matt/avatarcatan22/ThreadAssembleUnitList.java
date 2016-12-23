package catan.avatar.matt.avatarcatan22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreadAssembleUnitList extends Thread{
    private static HashMap<String, List<Unit>> unitHashMap;
    private static List<Unit> mainUnitsList;

    public ThreadAssembleUnitList(List<Unit> mainUnitsList, HashMap<String, List<Unit>> unitHashMap){
        ThreadAssembleUnitList.mainUnitsList = mainUnitsList;
        ThreadAssembleUnitList.unitHashMap = unitHashMap;
    }
    @Override
    public void run() {
        synchronized (mainUnitsList){
            synchronized (unitHashMap) {
                setUnitsLists(unitHashMap, mainUnitsList);
            }
        }
    }
    private static String[] units = {"Fire Nation Guy,fng,8,Town (Earth),5,4,1,0,5,0,8,2,1,Taunt",
            "Smellerbee,sme,0,Village & Not Fire Nation,7,9,1,5,8,6,15,5,1,Taunt",
            "PipSqueak,pip,0,Village & Not Fire Nation,10,8,1,10,8,5,20,6,1,none",
            "The Hippo,hip,8,Town (Earth),12,7,1,20,5,0,22,7,1,Taunt",
            "Water Apprentice ,awa,4,Village (Water),11,9,1,0,9,1,16,7,0,none",
            "Fire Apprentice ,afi,1,Village (Fire),12,9,1,0,7,1,15,7,0,none",
            "Air Apprentice ,aai,11,Village (Air),10,10,1,0,12,2,15,7,0,none",
            "Earth Apprentice ,aea,8,Village (Earth),12,9,1,10,6,1,15,7,0,none",
            "Julee,jul,12,City,5,15,1,10,9,8,20,7,1,Stun",
            "Varrick,var,12,City,3,5,1,0,6,10,15,7,1,Stun",
            "Lesser Spirit,les,14,Summoned by Jinora or Guru Pathik,12,8,1,15,10,7,16,7,0,none",
            "The Boulder,bou,8,Town (Earth),13,8,1,15,7,1,23,8,1,Taunt",
            "Sokka,sok,0,Southern Water Tribe,11,12,1,5,11,9,20,9,1,Critical Hit",
            "Spirit Wan Shi Tong,kno,14,Village (Earth) on Dessert Tile & Knowledge Spirit Card ,10,14,1,0,11,10,25,10,1,none",
            "Long Shot,lon,12,Village & Not Fire Nation,9,19,1,0,9,8,15,10,1,Critical Hit",
            "Asami,asa,0,City,15,12,1,0,9,9,17,11,1,Stun",
            "Jet,jet,0,Village & Not Fire Nation,13,13,1,10,9,8,20,11,1,Critical Hit",
            "Suki,suk,0,Village,14,13,1,10,13,7,20,12,1,none",
            "June,jun,0,Village,13,13,1,15,13,7,23,12,1,Paralyse",
            "Mako,mak,2,City,13,14,1,5,13,6,22,12,1,none",
            "Mai,mai,12,City,12,16,1,5,15,7,20,13,1,Critical Hit",
            "Water Master ,mwa,5,Town (Water),13,13,2,0,12,6,23,13,0,Heal",
            "Earth Master ,mea,9,Town (Earth),14,12,1,30,10,5,27,13,0,none",
            "Air Master ,mair,11,Town (Air),12,13,2,0,15,6,25,13,0,none",
            "Fire Master ,mfi,2,Town (Fire),15,13,1,5,12,6,24,13,0,none",
            "Admiral Zhou,zho,1,Kill Moon Spirit Card,16,11,1,25,9,6,30,13,1,Instantly killed by any spirit",
            "Sword Master Piandao,pia,0,Town (Fire),12,15,2,0,14,9,20,13,1,Critical Hit",
            "Spirit,spi,14,Spirit Defender Card,13,12,2,20,15,8,23,14,0,none",
            "Bolin,bol,10,City,15,13,1,25,12,6,24,14,1,none",
            "Tenzin,ten,11,City (Any) & Village (Air),12,13,2,30,13,8,24,14,1,none",
            "Combustion Man,com,3,2 Cities (Fire + Any),25,10,1,40,12,3,9,14,1,none",
            "Ty Lee,tyl,13,City,3,17,1,5,18,7,15,15,1,Block Chi, Paralyse",
            "Chief Lin Be Fong,lin,9,City,14,13,2,35,11,6,23,15,1,none",
            "Jeong Jeong,jeo,1,Town & Not Fire Nation,16,14,2,10,15,8,15,15,1,none",
            "Tarlock,tar,7,City,15,15,2,15,14,5,17,16,1,none",
            "Paku,pak,5,City (Water),13,17,3,0,14,7,15,16,1,none",
            "Gharzan,gha,10,2 Cities (Fire + Earth),17,14,2,15,10,5,20,16,1,none",
            "P'Li,pli,3,2 Cities (Fire + Any),26,11,1,35,12,6,10,16,1,none",
            "Hama,ham,6,2 Villages (Fire + Water) & Not fire nation,16,16,3,25,13,9,5,17,1,Control",
            "Desna & Eska,twi,5,2 Cities (Water & Any),12,16,3,20,18,8,20,17,1,none",
            "Guru Pathik,pat,0,Air Temple,6,9,1,0,15,10,10,17,1,Summon Spirit",
            "Swamp Monster Huu,huu,4,Village (Water),13,11,2,65,10,8,25,17,1,none",
            "Gyatso,gya,11,Air Temple,13,17,4,0,14,10,15,18,1,none",
            "Jinora,jin,11,City,5,12,2,0,13,8,10,18,1,Summon Spirit",
            "Kuvira,kuv,9,3 Cities (1 Earth & 2 Any),15,16,2,30,12,9,25,18,1,none",
            "Zuko,zuk,1,City,16,14,2,35,11,7,30,18,1,Redirect Lightning",
            "Katara,kat,6,Southern Water Tribe,14,16,3,15,15,7,20,19,1,Heal",
            "Toph,top,9,City (Earth),17,15,2,25,15,7,25,19,1,Taunt",
            "Azula,azu,2,2 Cities (Fire),17,16,2,10,15,9,25,19,1,Taunt",
            "Spirit Hei Bai ,hei,14,Village containing a spirit portal & Hei Bai Assist Card,20,15,1,10,13,7,30,20,1,Steal Unit",
            "Bumi,bum,8,Oma Shu,17,16,3,20,15,8,17,20,1,none",
            "Dark Avatar Unalaq,una,15,Northern Water Tribe & Spirit Portal & Player does not control Avatar,23,14,1,15,11,6,30,20,1,none",
            "Ozia,ozi,2,Fire Nation Capital or Ember Island,19,17,2,25,16,8,25,23,1,none",
            "Iroh,iro,2,3 Cities (Fire + Water + Earth),19,17,2,25,16,9,25,23,1,Redirect Lightning",
            "Avatar Korra,kor,15,4 Settlements (Air +Earth + Fire + Water)  & City (Water or Fire),26,13,1,35,10,6,30,23,1,Heal",
            "Avatar Kuruk,kur,15,3 Settlements (Air + Earth+ Fire) & City (Water),22,16,2,10,15,4,25,23,1,none",
            "Zaheer,zah,11,Village (Air) & Spirit Portal Open & Player does not control the Avatar,22,15,3,0,16,8,26,24,1,none",
            "Amon,amo,7,4 Cities (1 Water + 3 Any) & Player does not control the Avatar,14,18,2,15,20,8,20,26,1,Take away Bending",
            "Avatar Wan,wan,15,4 Lion Turtle Towns,24,16,2,30,14,6,30,27,1,none",
            "Avatar Yan Chen,yan,15,3 Settlements (Earth+ Fire + Water) & an Air Temple,23,18,4,10,13,8,23,29,1,none",
            "Spirit Face Stealer Koh,koh,14,Spirit Portal Open & Koh Assist Card,30,13,1,60,9,7,40,31,1,none",
            "Avatar Roku,rok,15,3 Settlements (Air + Earth+Water) & (Fire Nation Capital or Ember Island),26,20,2,15,15,9,25,32,1,none",
            "Avatar Aang,aan,15,3 Settlements (Air + Earth+ Fire) & (Southern Air Temple or Southern Water Tribe),26,20,3,25,17,8,30,35,1,Taunt",
            "Avatar Kyoshi,kyo,15,3 Settlements (Air + Fire + Water) & (Ba Sing Se or Omashu),27,20,3,30,13,7,35,37,1,none",

    };
    private static void setUnitsLists(HashMap<String, List<Unit>> unitHashMap, List<Unit>mainUnitsList){
        synchronized (mainUnitsList){
            synchronized (unitHashMap) {
                HashMap<Byte, String> bendingtypes = new HashMap<>();
                {
                    bendingtypes.put((byte) 0, "No Bending - Melee");
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
                    bendingtypes.put((byte) 12, "No Bending - Ranged");
                    bendingtypes.put((byte) 13, "Chi Blocker");
                    bendingtypes.put((byte) 14, "Spirit");
                    bendingtypes.put((byte) 15, "All Elements");

                }
                String name, image, minRequirement, bengingType, ability;
                String[] unitStats;
                List<Unit> statsList;
                byte attack, accuracy, gold, hero, evasion, defense, life, intelligence, numberOfAttacks, type;
                Unit u;
                for (String unit1 : units) {
                    statsList = new ArrayList<>();
                    unitStats = unit1.split(",");
                    name = unitStats[0];
                    image = unitStats[1];
                    type = Byte.parseByte(unitStats[2]);
                    bengingType = bendingtypes.get(type);
                    minRequirement = unitStats[3];
                    attack = Byte.parseByte(unitStats[4]);
                    accuracy = Byte.parseByte(unitStats[5]);
                    numberOfAttacks = Byte.parseByte(unitStats[6]);
                    defense = Byte.parseByte(unitStats[7]);
                    evasion = Byte.parseByte(unitStats[8]);
                    intelligence = Byte.parseByte(unitStats[9]);
                    life = Byte.parseByte(unitStats[10]);
                    gold = Byte.parseByte(unitStats[11]);
                    hero = Byte.parseByte(unitStats[12]);
                    ability = unitStats[13];

                    u = new Unit(name, image, type, minRequirement, attack, accuracy, numberOfAttacks, defense, evasion, intelligence, life, gold, hero, bengingType, ability);
                    statsList.add(u);
                    mainUnitsList.add(u);
                    unitHashMap.put(name, statsList);
                }
            }
        }
    }
}
