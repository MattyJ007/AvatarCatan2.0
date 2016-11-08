package catan.avatar.matt.avatarcatan22;
public class Unit {
    String name,image;
    byte attack6,attack20,evasion,defense,life,intelligence,numberOfAttacks,type;
    boolean hero;

    public Unit(String name, String image, byte attack6, byte attack20, byte evasion, byte defense, byte life, byte intelligence, byte numberOfAttacks, byte type, boolean hero) {
        this.name = name;
        this.image = image;
        this.attack6 = attack6;
        this.attack20 = attack20;
        this.evasion = evasion;
        this.defense = defense;
        this.life = life;
        this.intelligence = intelligence;
        this.numberOfAttacks = numberOfAttacks;
        this.type = type;
        this.hero = hero;
    }

    public Unit(){
        name = "unit";
        attack6 = 1;
        attack20 = 0;
        evasion = 0;
        defense = 0;
        life = 5;
        intelligence = 0;
        numberOfAttacks = 1;
        type = 0;
        image = "";
    }

    public byte getAttack6() {
        return attack6;
    }

    public byte getAttack20() {
        return attack20;
    }

    public String getName() {
        return name;
    }

    public byte getEvasion() {
        return evasion;
    }

    public byte getDefense() {
        return defense;
    }

    public byte getLife() {
        return life;
    }

    public byte getIntelligence() {
        return intelligence;
    }

    public byte getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public byte getType() {
        return type;
    }

    public String getImage() {
        return image;
    }
}
