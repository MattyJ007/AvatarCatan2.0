package catan.avatar.matt.avatarcatan22;

import java.util.HashMap;
import java.util.List;

public class ThreadAssembleUnitList extends Thread {
    private static List<Unit> mainUnitsList;

    public ThreadAssembleUnitList(List<Unit> mainUnitsList) {
        ThreadAssembleUnitList.mainUnitsList = mainUnitsList;
    }

    @Override
    public void run() {
        synchronized (mainUnitsList) {
            setUnitsLists(
                    mainUnitsList);
        }
    }

    private static String[] units = {"Fire Nation Guy,fng,8,Town (Earth),5,4,1,0,3,0,8,2,1,Taunt",
            "Guru Pathik,pat,0,Air Temple,6,9,1,0,9,10,10,5,1,Summon Spirit",
            "Fire Apprentice,afi,1,Village (Fire),6,9,1,0,5,1,15,5,0,none",
            "Earth Apprentice,aea,8,Village (Earth),6,9,1,10,4,1,15,5,0,none",
            "Air Apprentice,aai,11,Village (Air),5,10,1,0,8,2,15,5,0,none",
            "Water Apprentice,awa,4,Village (Water),6,9,1,0,6,1,16,5,0,none",
            "Varrick,var,12,City,3,5,1,0,4,8,15,5,1,Stun",
            "Smellerbee,sme,0,Village & Not Fire Nation,7,9,1,5,5,6,15,5,1,Crit",
            "Jinora,jin,11,City,5,12,2,0,8,8,10,6,1,Summon Spirit",
            "Julee,jul,12,City,5,15,1,10,6,7,20,7,1,Stun",
            "PipSqueak,pip,0,Village & Not Fire Nation,10,8,1,10,5,5,20,7,1,none",
            "Lesser Spirit,les,14,Summoned by Jinora or Guru Pathik,12,8,1,15,6,7,15,0,0,none",
            "The Hippo,hip,8,Town (Earth),12,7,1,20,3,0,22,8,1,Taunt",
            "Asami,asa,0,City,13,11,1,0,3,9,15,9,1,Stun",
            "The Boulder,bou,8,Town (Earth),13,8,1,15,5,1,23,9,1,Taunt",
            "Long Shot,lon,12,Village & Not Fire Nation,8,19,1,0,6,7,15,10,1,Crit",
            "Sokka,sok,0,Village (Water),11,12,1,5,7,9,20,10,1,Crit",
            "Ty Lee,tyl,13,City,7,17,1,5,11,7,15,10,1,Block Chi",
            "Air Master,mair,11,Town (Air),9,13,2,0,6,6,24,11,0,none",
            "Fire Master,mfi,2,Town (Fire),12,13,1,5,4,6,23,11,0,none",
            "Water Master,mwa,5,Town (Water),10,13,2,0,5,6,22,11,0,Heal",
            "Earth Master,mea,9,Town (Earth),12,12,1,20,4,5,25,11,0,none",
            "Jet,jet,0,Village & Not Fire Nation,13,13,1,10,6,11,20,12,1,Crit",
            "Suki,suk,0,Village,14,13,1,10,8,10,20,13,1,none",
            "Mako,mak,2,City,13,14,1,5,8,6,22,13,1,none",
            "June,jun,0,Village,13,13,1,15,8,7,23,13,1,Paralyse",
            "Hama,ham,6,2 Villages (Fire + Water) & Not fire nation,10,16,3,25,8,9,10,13,1,Heal",
            "Mai,mai,12,City,12,16,1,5,9,7,20,13,1,Crit",
            "Sword Master Piandao,pia,0,Town (Fire),12,15,2,0,9,9,20,14,1,Crit",
            "Spirit,spi,14,Spirit Defender Card,13,12,2,20,9,8,23,0,0,none",
            "Admiral Zhou,zho,1,Kill Moon Spirit Card,16,11,1,25,6,6,30,15,1,Instantly killed by any spirit",
            "Combustion Man,com,3,2 Cities (Fire + Any),25,10,1,40,8,3,9,15,1,none",
            "Tenzin,ten,11,City (Any) & Village (Air),12,13,2,30,8,8,24,15,1,none",
            "Swamp Monster Huu,huu,4,Swamp Village,9,11,2,65,6,8,20,15,1,none",
            "Bolin,bol,10,City,15,13,1,25,8,6,24,15,1,none",
            "Jeong Jeong,jeo,1,Town & Not Fire Nation,16,14,2,10,9,8,15,16,1,none",
            "Spirit Wan Shi Tong,wst,14,Village (Earth) & Knowledge Spirit Card ,14,15,1,0,9,10,30,0,1,none",
            "Chief Lin Be Fong,lin,9,City,14,13,2,35,7,6,23,16,1,none",
            "Tarlock,tar,7,City,15,15,2,15,9,5,17,16,1,none",
            "Paku,pak,5,City (Water),13,17,3,0,9,7,15,17,1,none",
            "P'Li,pli,3,2 Cities (Fire + Any),26,11,1,35,8,6,10,17,1,none",
            "Gharzan,gha,10,2 Cities (Fire + Earth),17,14,2,15,6,5,20,17,1,none",
            "Desna & Eska,twi,5,2 Cities (Water & Any),12,16,3,20,11,7,20,18,1,Heal",
            "Gyatso,gya,11,Air Temple,13,17,4,0,12,10,15,19,1,none",
            "Katara,kat,6,Village (Water),14,16,3,15,12,7,20,19,1,Heal",
            "Kuvira,kuv,9,3 Cities (1 Earth & 2 Any),15,16,2,30,8,9,25,20,1,none",
            "Zuko,zuk,1,City,16,14,2,35,7,7,30,20,1,Redirect Lightning",
            "Toph,top,9,City (Earth),17,15,2,25,9,7,25,20,1,Taunt",
            "Bumi,bum,8,Oma Shu,17,16,3,20,9,8,17,20,1,none",
            "Spirit Hei Bai ,hei,14,Village containing a spirit portal & Hei Bai Assist Card,20,15,1,10,8,7,30,0,1,Removes One Unit at the beginning of a battle - unit is returned if Hei Bai is defeated",
            "Azula,azu,2,2 Cities (Fire),17,16,2,10,12,9,25,21,1,Taunt",
            "Dark Avatar Unalaq,una,15,Northern Water Tribe & Spirit Portal & Player does not control Avatar,23,14,1,15,7,6,30,22,1,none",
            "Ozia,ozi,2,Fire Nation Capital or Ember Island,19,17,2,25,10,8,25,24,1,none",
            "Iroh,iro,2,3 Cities (Fire + Water + Earth),19,17,2,25,10,9,25,24,1,Redirect Lightning",
            "Avatar Kuruk,kur,15,3 Settlements (Air + Earth+ Fire) & City (Water),22,16,2,10,9,4,25,24,1,none",
            "Zaheer,zah,11,Village (Air) & Spirit Portal Open & Player does not control the Avatar,22,15,3,0,10,8,26,25,1,none",
            "Avatar Korra,kor,15,4 Settlements (Air +Earth + Fire + Water)  & City (Water or Fire),28,13,1,35,6,6,30,25,1,Heal",
            "Amon,amo,7,4 Cities (1 Water + 3 Any) & Player does not control the Avatar,18,18,2,15,20,8,25,26,1,Take away Bending",
            "Avatar Wan,wan,15,4 Lion Turtle Towns,24,16,2,30,9,6,30,29,1,none",
            "Avatar Yan Chen,yan,15,3 Settlements (Earth+ Fire + Water) & an Air Temple,23,18,4,10,11,8,23,30,1,none",
            "Spirit Face Stealer Koh,koh,14,Spirit Portal Open & Koh Assist Card,30,13,1,60,6,7,40,0,1,none",
            "Avatar Roku,rok,15,3 Settlements (Air + Earth+Water) & (Fire Nation Capital or Ember Island),27,20,2,15,12,8,28,35,1,none",
            "Avatar Aang,aan,15,3 Settlements (Air + Earth+ Fire + Water) & an Air Temple,26,20,3,20,15,8,30,38,1,Redirect Lightning",
            "Avatar Kyoshi,kyo,15,3 Settlements (Air + Fire + Water) & (Ba Sing Se or Omashu),29,20,2,30,8,7,35,39,1,none"
    };

    private static void setUnitsLists(
            List<Unit> mainUnitsList) {
        synchronized (mainUnitsList) {
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

            String name, image, minRequirement, bendingTypes, ability;
            String[] unitStats;
            byte attack, accuracy, gold, hero, evasion, defense, life, intelligence, numberOfAttacks, type;
            Unit u;
            for (String unit1 : units) {
                unitStats = unit1.split(",");
                name = unitStats[0];
                image = unitStats[1];
                type = Byte.parseByte(unitStats[2]);
                bendingTypes = bendingtypes.get(type);
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

                u = new Unit(name, image, type, minRequirement, attack, accuracy, numberOfAttacks, defense, evasion, intelligence, life, gold, hero, bendingTypes, ability);
                mainUnitsList.add(u);
            }
        }
    }
}
