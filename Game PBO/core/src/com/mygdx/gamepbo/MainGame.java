package com.mygdx.gamepbo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, background;
	float stateTime;
	Player player;
	Obstacles limiterWall;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("img/Background.png");
		player = new Player();
		limiterWall = new Obstacles();
		stateTime = 0f;
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(background,0,0,1600,800);
		limiterWall.Draw(batch);
		player.Draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
