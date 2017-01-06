package catan.avatar.matt.avatarcatan22;

import android.view.View;

import java.util.ArrayList;

public class Unit implements Cloneable{
    String name,image, minRequirement, bendingType, ability;
    byte damage, accuracy,evasion,defense,intelligence,numberOfAttacks, numberOfAttacksUsed,type, hero, gold, life;
    ArrayList<Byte>status = new ArrayList<>();
    View view;

    public Unit(String name, String image, byte type, String minRequirement, byte damage, byte accuracy, byte numberOfAttacks, byte defense, byte evasion, byte intelligence, byte life, byte gold, byte hero, String bendingType, String ability) {
        this.name = name;
        this.image = image;
        this.damage = damage;
        this.accuracy = accuracy;
        this.evasion = evasion;
        this.defense = defense;
        this.life = life;
        this.intelligence = intelligence;
        this.numberOfAttacks = numberOfAttacks;
        this.type = type;
        this.hero = hero;
        this.gold = gold;
        this.ability = ability;
        this.minRequirement = minRequirement;
        this.bendingType = bendingType;
        this.status.add((byte) 1);
    }

    public Unit(String name, String image, byte type, byte damage, byte accuracy, byte numberOfAttacks, byte defense, byte evasion, byte intelligence, byte life, byte hero, String ability ) {
        this.name = name;
        this.image = image;
        this.damage = damage;
        this.accuracy = accuracy;
        this.evasion = evasion;
        this.defense = defense;
        this.life = life;
        this.intelligence = intelligence;
        this.numberOfAttacks = numberOfAttacks;
        this.type = type;
        this.hero = hero;
        this.ability = ability;
        this.status.add((byte) 1);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Unit(this.name,this.image,this.type,this.damage,this.accuracy,this.numberOfAttacks, this.defense,this.evasion,this.intelligence,this.life,this.hero, this.ability);
    }

//    public Unit(){
//        name = "Default Unit";
//        attack = 1;
//        accuracy = 0;
//        evasion = 0;
//        defense = 0;
//        life = 5;
//        intelligence = 0;
//        numberOfAttacks = 1;
//        type = 0;
//        image = "";
//    }

    public byte getDamage() {
        return damage;
    }

    public byte getAccuracy() {
        return accuracy;
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

    public byte getGold() {
        return gold;
    }

    public byte getHero() {
        return hero;
    }

    public void setLife(byte life) {
        this.life = life;
    }

    public String getStats(){
        return ("Damage: " + damage + "\nAccuracy: " + accuracy + "\nDefense: "+defense + "\nEvasion: "+evasion+"\nLife: "+life+"\nIntelligence: "+intelligence+"\nNumber of Attacks: "+numberOfAttacks+"\nBending: " + bendingType + "\nAbility: "+ ability+ "\n\nMinimum Requirement: "+ minRequirement +"\n");

    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public byte getNumberOfAttacksUsed() {
        return numberOfAttacksUsed;
    }

    public void setNumberOfAttacksUsed(byte numberOfAttacksUsed) {
        this.numberOfAttacksUsed = numberOfAttacksUsed;
    }

    public String getAbility() {
        return ability;
    }

    public ArrayList<Byte> getStatus() {
        return status;
    }


    public void setAbility(String ability) {
        this.ability = ability;
    }
}
