package com.mygdx.gamepbo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import static com.mygdx.gamepbo.Player.inputFrames;

public class Monster extends Character implements Draw {
    private long lastIdleTime;
    private long idleStartTime;
    private float attackduration = 0;
    Vector2 position;
    private Texture attack,walk,idle_blink;
    Sprite monsterSprite;
    private enum State {
        WALKING, IDLE, ATTACKING
    }
    private State currentState;
    Animation<TextureRegion> monsterAnimation;

    TextureRegion[] _attackFrames,_walkFrames,_idle_blinkFrames;

    float stateTime;

    public Monster(int HP, int physicalAttack, double attackSpeed, Vector2 position,String attackSpritesheet,String walkSpritesheet,String idleSpritesheet
    ,int attackSpriteSheetSize,int walkSpriteSheetSize,int idleSpriteSheetSize) {
        super(HP, physicalAttack, attackSpeed);
        this.position = position;

        attack = new Texture(attackSpritesheet);
        walk = new Texture(walkSpritesheet);
        idle_blink = new Texture(idleSpritesheet);

        TextureRegion [][] attackFrames = TextureRegion.split(attack, attack.getWidth() / 12, attack.getHeight() / 1);
        TextureRegion [][] walkFrames = TextureRegion.split(walk, walk.getWidth() / 18, attack.getHeight() / 1);
        TextureRegion [][] idle_blinkFrames = TextureRegion.split(idle_blink, idle_blink.getWidth() / 12, idle_blink.getHeight() / 1);

        _attackFrames = new TextureRegion[attackSpriteSheetSize];
        _walkFrames = new TextureRegion[walkSpriteSheetSize];
        _idle_blinkFrames = new TextureRegion[idleSpriteSheetSize];

        _attackFrames = inputFrames(attackFrames,_attackFrames);
        _walkFrames = inputFrames(walkFrames,_walkFrames);
        _idle_blinkFrames = inputFrames(idle_blinkFrames,_idle_blinkFrames);

        currentState = State.WALKING;

        monsterAnimation = new Animation<>(.1f, _walkFrames);
        System.out.println(monsterAnimation);
        stateTime = 0f;
    }// Monster Close Range

    public Monster(int HP, double attackSpeed, int magicAttack, Vector2 position) {
        super(HP,attackSpeed, magicAttack);
        this.position = position;



        _attackFrames = new TextureRegion[12];
    }// Monster Long Range

    public Monster (int HP, int physicalAttack, double attackSpeed, int physicalDefPercentage, int magicDefPercentage, Vector2 position) {
        super(HP, physicalAttack, attackSpeed,physicalDefPercentage,magicDefPercentage);
        this.position = position;


        _attackFrames = new TextureRegion[12];
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
        TextureRegion currentState = monsterAnimation.getKeyFrame(stateTime, true);
        monsterSprite = new Sprite(currentState);
        monsterSprite.setScale(0.4f);
        Update(Gdx.graphics.getDeltaTime());
//        Update(Gdx.graphics.getDeltaTime(), speed);
//        if (currentIdleState != null) batch.draw(currentIdleState,position.x,position.y);
        monsterSprite.setPosition(position.x, position.y);
        monsterSprite.setFlip(true,false);
        monsterSprite.draw(batch);
//        System.out.println(monster.getX());
//        System.out.println(monster.getY());
    }

    public void Update(float graphicsDeltaTime) {
        float speed = 90;
        switch (currentState) {
            case WALKING:
                if (position.x > -20) {
                    monsterAnimation = new Animation<>(0.12f, _walkFrames);
                    stateTime += Gdx.graphics.getDeltaTime();
                    position.x -= graphicsDeltaTime * speed;
                } else {
                    currentState = State.IDLE;
                    idleStartTime = TimeUtils.nanoTime();
                    stateTime = 0;
                }
                break;

            case IDLE:
                if (TimeUtils.nanoTime() - idleStartTime <= 1500000000L) { // 1 second in nanoseconds
                    monsterAnimation = new Animation<>(0.12f, _idle_blinkFrames);
                    stateTime += Gdx.graphics.getDeltaTime();
                }
                else{
                    lastIdleTime = TimeUtils.nanoTime();
                    currentState = State.ATTACKING;
                    stateTime = 0;
                }
                break;

            case ATTACKING:
                monsterAnimation = new Animation<>(0.12f, _attackFrames);
                stateTime += Gdx.graphics.getDeltaTime();
                attackduration += Gdx.graphics.getDeltaTime();
                if(monsterAnimation.isAnimationFinished(stateTime)){
                    currentState = State.IDLE;
                    idleStartTime = TimeUtils.nanoTime();
                    stateTime = 0;
                }
                break;
        }
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
