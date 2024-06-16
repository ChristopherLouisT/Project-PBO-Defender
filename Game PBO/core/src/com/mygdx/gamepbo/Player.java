package com.mygdx.gamepbo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player implements Draw {
    Texture idle;
    Texture walk;
    Texture shot, arrow;
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
        shot = new Texture("Player/Shot_1.png");
        arrow = new Texture("Player/Arrow.png");

        position = new Vector2(-20, Gdx.graphics.getHeight() / 2);
        TextureRegion [][] idleFrames = TextureRegion.split(idle, idle.getWidth() / 6, idle.getHeight() / 1);
        TextureRegion [][] walkFrames = TextureRegion.split(walk, walk.getWidth() / 8, walk.getHeight() / 1);
        TextureRegion [][] shotFrames = TextureRegion.split(shot, shot.getWidth() / 14, shot.getHeight() / 1);
        _idleFrames = new TextureRegion[6];
        _walkFrames = new TextureRegion[8];
        _shotFrames = new TextureRegion[14];

        _idleFrames = inputFrames(idleFrames,_idleFrames);
        _walkFrames = inputFrames(walkFrames,_walkFrames);
        _shotFrames = inputFrames(shotFrames, _shotFrames);

        arrows = new Sprite(arrow);

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
        arrows.setScale(2);
        Update(Gdx.graphics.getDeltaTime(), speed);
//        if (currentIdleState != null) batch.draw(currentIdleState,position.x,position.y);
        player.setPosition(position.x, position.y);
        player.draw(batch);
        arrows.setPosition(800, player.getHeight() / 2);
        arrows.draw(batch);
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
        if (Gdx.input.isButtonPressed(0)) {
            playerAnimation = new Animation<>(.1f, _shotFrames);
            arrows.setX(arrows.getX() + deltaTime * speed);
        }
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
