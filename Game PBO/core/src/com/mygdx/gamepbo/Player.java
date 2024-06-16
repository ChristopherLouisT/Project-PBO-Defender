package com.mygdx.gamepbo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player implements Draw {
    Texture idle;
    Texture walk;
    TextureRegion [] _idleFrames;
    TextureRegion [] _walkFrames;
    Animation <TextureRegion> playerAnimation;
    Vector2 position;
    float stateTime;

    public Player () {
        idle = new Texture("Player/Idle.png");
        walk = new Texture("Player/Walk.png");
        position = new Vector2(-20, Gdx.graphics.getHeight() / 2);
        TextureRegion [][] idleFrames = TextureRegion.split(idle, idle.getWidth() / 6, idle.getHeight() / 1);
        TextureRegion [][] walkFrames = TextureRegion.split(walk, walk.getWidth() / 8, walk.getHeight() / 1);
        _idleFrames = new TextureRegion[6];
        _walkFrames = new TextureRegion[8];

        _idleFrames = inputFrames(idleFrames,_idleFrames);
        _walkFrames = inputFrames(walkFrames,_walkFrames);

        playerAnimation = new Animation<>(.3f, _idleFrames);
        System.out.println(playerAnimation);
        stateTime = 0f;
    }

    @Override
    public void Draw(SpriteBatch batch) {
        float speed = 300;
        TextureRegion currentIdleState = playerAnimation.getKeyFrame(stateTime, true);
        Update(Gdx.graphics.getDeltaTime(), speed);
        if (currentIdleState != null) batch.draw(currentIdleState,position.x,position.y);
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
        if (position.y < 95) position.y = 95;
        if (position.y > 500) position.y = 500;
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
