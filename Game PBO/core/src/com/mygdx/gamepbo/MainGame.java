package com.mygdx.gamepbo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, background;
	float stateTime;
	Player player;
	Monster golem,minotaur,minotaur2,minotaur3,minotaur4,minotaur5;
	Array<Monster> monsters;
	Obstacles limiterWall;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("img/Background.png");
		player = new Player();
		golem = new Monster(100,10,2.0,new Vector2(800,300)
				,"Monster/Golem/attack/Golem_attack_spritesheet.png"
				, "Monster/Golem/walking/Golem_walking_spritesheet.png"
				,"Monster/Golem/idleblink/Golem_idle_blink_spritesheet.png"
				,12,18,12);
		minotaur = new Monster(200,15,1.5,new Vector2(800,200)
				,"Monster/Minotaur/attack/Minotaur_attack_spritesheet.png"
				, "Monster/Minotaur/walking/Minotaur_walk_spritesheet.png"
				,"Monster/Minotaur/idleblink/Minotaur-idle_blink-spritesheet.png"
				,12,18,12);
		minotaur2 = new Monster(200,15,1.5,new Vector2(800,199)
				,"Monster/Minotaur/attack/Minotaur_attack_spritesheet.png"
				, "Monster/Minotaur/walking/Minotaur_walk_spritesheet.png"
				,"Monster/Minotaur/idleblink/Minotaur-idle_blink-spritesheet.png"
				,12,18,12);
		minotaur3 = new Monster(200,15,1.5,new Vector2(800,198)
				,"Monster/Minotaur/attack/Minotaur_attack_spritesheet.png"
				, "Monster/Minotaur/walking/Minotaur_walk_spritesheet.png"
				,"Monster/Minotaur/idleblink/Minotaur-idle_blink-spritesheet.png"
				,12,18,12);
		minotaur4 = new Monster(200,15,1.5,new Vector2(800,197)
				,"Monster/Minotaur/attack/Minotaur_attack_spritesheet.png"
				, "Monster/Minotaur/walking/Minotaur_walk_spritesheet.png"
				,"Monster/Minotaur/idleblink/Minotaur-idle_blink-spritesheet.png"
				,12,18,12);
		minotaur5 = new Monster(200,15,1.5,new Vector2(800,196)
				,"Monster/Minotaur/attack/Minotaur_attack_spritesheet.png"
				, "Monster/Minotaur/walking/Minotaur_walk_spritesheet.png"
				,"Monster/Minotaur/idleblink/Minotaur-idle_blink-spritesheet.png"
				,12,18,12);

		monsters = new Array<Monster>();
		monsters.add(golem);
		monsters.add(minotaur);
		monsters.add(minotaur2);
		monsters.add(minotaur3);
		monsters.add(minotaur4);
		monsters.add(minotaur5);

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
//		golem.Draw(batch);
//		minotaur.Draw(batch);
//		minotaur2.Draw(batch);
//		minotaur3.Draw(batch);
//		minotaur4.Draw(batch);
//		minotaur5.Draw(batch);

//		for (Iterator<Monster> monsterIterator = monsters.iterator(); monsterIterator.hasNext();){
//			Monster monsterCheck = monsterIterator.next();
//			System.out.println(monsterCheck.getHP());
//			if(monsterCheck.monster.getBoundingRectangle().overlaps(player.arrows.getBoundingRectangle())){
//				monsterCheck.Attacked(200);
//			}
//			if(monsterCheck.getHP() == 0){
//				monsterIterator.remove();
//			}
//		}
		for (int i = 0; i <monsters.size ; i++) {
			monsters.get(i).Draw(batch);
		}

		for (int i = 0; i < monsters.size; i++) {
			System.out.println(monsters.get(i).getHP());
			System.out.println(monsters.get(i).position.x);
			if(monsters.get(i).monsterSprite.getBoundingRectangle().overlaps(player.arrows.getBoundingRectangle())){
				monsters.get(i).Attacked(100);
			}
			if(monsters.get(i).getHP() == 0){
				monsters.removeIndex(i);
			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
