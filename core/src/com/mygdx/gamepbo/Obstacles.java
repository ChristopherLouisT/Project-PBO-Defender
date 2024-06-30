package com.mygdx.gamepbo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obstacles implements Draw{
    Sprite crystal;

    public Obstacles () {
        crystal = new Sprite(new Texture("Obstacles/Black_crystal1.png"));
        crystal.setScale(2f);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        float y = 490;
        for (int i = 0; i < 20; i++) {
            crystal.setPosition(180, y - (20*i));
            crystal.draw(batch);
        }
    }
}
