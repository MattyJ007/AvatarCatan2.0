package catan.avatar.matt.avatarcatan22;

import android.view.View;

public class Unit implements Cloneable{
    String name,image, minRequirement, bendingType;
    byte attack6,attack20,evasion,defense,intelligence,numberOfAttacks,type, hero, gold;
    float life;
    byte blocking =0;
    View view;

    public Unit(String name, String image, byte type, String minRequirement, byte attack6,byte attack20,byte numberOfAttacks, byte defense,byte evasion,byte intelligence,float life,byte gold,byte hero, String bendingType) {
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
        this.gold = gold;
        this.minRequirement = minRequirement;
        this.bendingType = bendingType;
    }

    public Unit(String name, String image,byte type, byte attack6, byte attack20, byte numberOfAttacks,byte defense,byte evasion, byte intelligence, float life, byte hero ) {
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Unit(this.name,this.image,this.type,this.attack6,this.attack20,this.numberOfAttacks, this.defense,this.evasion,this.intelligence,this.life,this.hero);
    }

    public Unit(){
        name = "Default Unit";
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

    public float getLife() {
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

    public byte getGold() {
        return gold;
    }

    public byte getHero() {
        return hero;
    }

    public byte getBlocking() {
        return blocking;
    }

    public void setBlocking(byte blocking) {
        this.blocking = blocking;
    }

    public void setLife(float life) {
        this.life = life;
    }

    public String getStats(){
        return ("Attack D6: " + attack6 + "\nAttack D20: " + attack20 + "\nDefense: "+defense + "\nEvasion: "+evasion+"\nLife: "+life+"\nIntelligence: "+intelligence+"\nNumber of Attacks: "+numberOfAttacks+"\nBending: " + bendingType + "\n\nMinimum Requirement: "+ minRequirement +"\n");

    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
