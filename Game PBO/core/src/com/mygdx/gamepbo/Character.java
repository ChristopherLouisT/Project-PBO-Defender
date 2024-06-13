package com.mygdx.gamepbo;

public class Character {
    private int HP;
    private int mana;
    private int physicalAttack;
    private int magicAttack;
    private double attackSpeed;
    private double physicalDefPercentage;
    private double magicDefPercentange;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(int physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(int magicAttack) {
        this.magicAttack = magicAttack;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getPhysicalDefPercentage() {
        return physicalDefPercentage;
    }

    public void setPhysicalDefPercentage(double physicalDefPercentage) {
        this.physicalDefPercentage = physicalDefPercentage;
    }

    public double getMagicDefPercentange() {
        return magicDefPercentange;
    }

    public void setMagicDefPercentange(double magicDefPercentange) {
        this.magicDefPercentange = magicDefPercentange;
    }
}
