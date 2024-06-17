package com.mygdx.gamepbo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player implements Draw {
    private int HP;
    private int Mana;
    Texture idle;
    Texture walk;
    Texture shot, arrow;
    Sprite healthBar,manaBar, Container1Bar, Container2Bar;
    Sprite player, arrows;
    TextureRegion [] _idleFrames;
    TextureRegion [] _walkFrames;
    TextureRegion [] _shotFrames;
    Animation <TextureRegion> playerAnimation;
    Vector2 position, arrowPosition;
    float stateTime;

    public Player () {
        idle = new Texture("Player/Idle.png");
        walk = new Texture("Player/Walk.png");
        shot = new Texture("Player/Shots_1.png");
        arrow = new Texture("Player/Arrow.png");
        Container1Bar = new Sprite(new Texture("img/ContainerBar.png"));
        Container2Bar = new Sprite(new Texture("img/ContainerBar.png"));
        healthBar = new Sprite(new Texture("img/healthBar.png"));
        manaBar = new Sprite(new Texture("img/healthBar.png"));

        healthBar.setColor(Color.RED);
        Container1Bar.setScale(1);
        healthBar.setScale(1.5f);
        manaBar.setColor(Color.CYAN);manaBar.setScale(1.5f);

        HP = 1000;
        Mana = 100;
        position = new Vector2(-20, Gdx.graphics.getHeight() / 2);
        arrowPosition = new Vector2(position.x, -100);

        TextureRegion [][] idleFrames = TextureRegion.split(idle, idle.getWidth() / 6, idle.getHeight() / 1);
        TextureRegion [][] walkFrames = TextureRegion.split(walk, walk.getWidth() / 8, walk.getHeight() / 1);
        TextureRegion [][] shotFrames = TextureRegion.split(shot, shot.getWidth() / 5, shot.getHeight() / 1);
        _idleFrames = new TextureRegion[6];
        _walkFrames = new TextureRegion[8];
        _shotFrames = new TextureRegion[5];

        _idleFrames = inputFrames(idleFrames,_idleFrames);
        _walkFrames = inputFrames(walkFrames,_walkFrames);
        _shotFrames = inputFrames(shotFrames, _shotFrames);

        arrows = new Sprite(arrow);
        arrows.setScale(1.5f);

        playerAnimation = new Animation<>(.3f, _idleFrames);
        System.out.println(playerAnimation);
        stateTime = 0f;
    }

    @Override
    public void Draw(SpriteBatch batch) {
        float speed = 300;
        TextureRegion currentIdleState = playerAnimation.getKeyFrame(stateTime, true);
        player = new Sprite(currentIdleState);
        player.setScale(1.5f);
        Update(Gdx.graphics.getDeltaTime(), speed);
//        if (currentIdleState != null) batch.draw(currentIdleState,position.x,position.y);
        player.setPosition(position.x, position.y);
        player.draw(batch);
        arrows.setPosition(arrowPosition.x,arrowPosition.y - 5);
        arrows.draw(batch);
        Container1Bar.setPosition(10,750);
        Container2Bar.setPosition(10,700);
        healthBar.setPosition(10,770);
        manaBar.setPosition(10,720);
        Container1Bar.setSize((1000*0.15f), 50);
        Container2Bar.setSize((1000*0.15f), 50);
        healthBar.setSize((HP * 0.1f), 10);
        manaBar.setSize(((Mana * 10) * 0.1f), 10);
        healthBar.draw(batch);
        Container1Bar.draw(batch);
        manaBar.draw(batch);
        Container2Bar.draw(batch);
    }

    public void Update (float deltaTime, float speed) {
        playerAnimation = new Animation<>(.3f, _idleFrames);
        stateTime += Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playerAnimation = new Animation<>(.2f, _walkFrames);
            position.y += deltaTime * speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerAnimation = new Animation<>(.2f, _walkFrames);
            position.y -= deltaTime * speed;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!(Mana - 30 < 0)) Mana -= 30;
            else Mana = 0;
        }
        if (Gdx.input.isTouched()) {
            playerAnimation = new Animation<>(.1f, _shotFrames);
            if (!(arrowPosition.x > position.x + 90 && arrowPosition.x < 1600 + 64)) {
                arrowPosition.y = position.y;
                arrowPosition.x = position.x + 80;
                if (!(HP - 100 < 0)) HP -= 100;
                else HP = 0;
            }
        }
        arrowPosition.x += deltaTime * 1000;
        if (position.y < 125) position.y = 125;
        if (position.y > 520) position.y = 520;

    }

    public static TextureRegion [] inputFrames (TextureRegion [][] source, TextureRegion [] targetFrame) {
        int idx = 0;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                targetFrame[idx] = source[i][j];
                idx++;
            }
        }
        return targetFrame;
    }
}
