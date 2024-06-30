package com.mygdx.gamepbo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Skill {
    private int manaPrice;
    private int [] area;

    public Skill (int manaPrice, int xLengthArea, int yLengthArea) {
        this.manaPrice = manaPrice;
        this.area = new int[2];
        this.area[0] = xLengthArea;
        this.area[1] = yLengthArea;
    }
}
