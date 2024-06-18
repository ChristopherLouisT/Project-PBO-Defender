package com.mygdx.gamepbo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Monster extends Character implements Draw {
    private Vector2 position;
    private Texture idle;

    public Monster(int HP, int physicalAttack, Vector2 position) {
        super(HP, physicalAttack);
        this.position = position;
    }// Monster Close Range

    public Monster(int HP, int magicAttack, double attackSpeed, Vector2 position) {
        super(HP, magicAttack, attackSpeed);
        this.position = position;
    }// Monster Long Range

    public Monster (int HP, int physicalAttack, int physicalDefPercentage, int magicDefPercentage, Vector2 position) {
        super(HP, physicalAttack,physicalDefPercentage,magicDefPercentage);
        this.position = position;
    }// Boss

    @Override
    public int Attack() {
        if (getMagicAttack() > 0) return getMagicAttack();
        return getPhysicalAttack();
    }

    @Override
    public void Attacked (int damage) {
        if (getHP() - damage <= 0) setHP(0);
        else setHP(getHP() - damage);
    }

    @Override
    public void Draw(SpriteBatch batch) {

    }

    public void Move(float graphicsDeltaTime) {

    }

    @Override
    public int getHP() {
        return super.getHP();
    }

    @Override
    public int getPhysicalAttack() {
        return super.getPhysicalAttack();
    }

    @Override
    public int getMagicAttack() {
        return super.getMagicAttack();
    }

    @Override
    public double getAttackSpeed() {
        return super.getAttackSpeed();
    }

    @Override
    public void setHP(int HP) {
        super.setHP(HP);
    }

    @Override
    public void setPhysicalAttack(int physicalAttack) {
        super.setPhysicalAttack(physicalAttack);
    }

    @Override
    public void setMagicAttack(int magicAttack) {
        super.setMagicAttack(magicAttack);
    }

    @Override
    public void setAttackSpeed(double attackSpeed) {
        super.setAttackSpeed(attackSpeed);
    }
}
