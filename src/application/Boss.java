package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Boss extends Enemy {
	public static final int BOSS_HP = 10000;
	public static final int BOSS_STR = 30;
	public static final int BOSS_DEF = 0;
	
	public static final float BOSS_SPEED = 0.02f;
	public static final float BOSS_WIDTH = 180;
	public static final float BOSS_HEIGHT = 180;
	public static final float BOSS_AGGRO_RANGE = 5;
	
	public static final double BOSS_MELEE_TIMER = 0.5;
	
	protected Timer rangeAbilityTimerSwirl;
	protected Timer rangeAbilityTimerAim;
	protected Timer spawnAbilityTimer;
	protected ArrayList<Projectile> projectiles;
	
	private int phase = 0;
	
	public Boss(float xPos, float yPos ) 
	{
		super( BOSS_HP, BOSS_STR, BOSS_DEF, xPos, yPos, BOSS_WIDTH, BOSS_HEIGHT, BOSS_SPEED, Asset.dog, BOSS_MELEE_TIMER, BOSS_AGGRO_RANGE);
		rangeAbilityTimerSwirl = new Timer(3);
		rangeAbilityTimerAim = new Timer(2);
		spawnAbilityTimer = new Timer(1);
		projectiles = new ArrayList<Projectile>(8);
		//Need to change hitbox a little bit, because the picture is too big
		hitBox = new Rectangle( xPos * Tile.TILEWIDTH, yPos * Tile.TILEHEIGHT, width - 30, height - 30 );
	}
	
	@Override
	public void update(Character character) {
		if (phase == 0) {
			if (getHp() < getMaxHP()*.66) {
				phase = 1;
			}
		}else if (phase == 1) {
			if (getHp() < getMaxHP()*.33) {
				phase = 2;
			}
		}
		super.update(character);
		rangeAbilityTimerSwirl.tick();
		rangeAbilityTimerAim.tick();
		spawnAbilityTimer.tick();
		for( int i = 0; i < projectiles.size(); i++ )
		{
			Projectile p = projectiles.get( i );
			
			if( p != null )
			{
				p.update();
			}
			
			//first check: should we despawn from distance?
			if( Utility.getDistance( p.initialXPos, p.initialYPos, p.xPos, p.yPos ) > 6 )
			{
				projectiles.remove( i );
			}//then check: did we hit an enemy?
			else {
				boolean didHit = false;
				
					//if the character exists, is alive, and we collide with it, then remove the projectile and damage the character
				if ( character != null && character.isAlive() && Utility.collidesWithGameObject(p, character)) {
					character.takeDmg(p);//takeDmg is in Entity
					projectiles.remove(i);
					didHit = true;
				}
				
				if (!didHit) {//didn't hit an enemy? Let's see if we are in a wall
					String tileOn = map.getTile((int)p.xPos, (int)p.yPos);//potential room for error here if we are out of bounds for some reason
					if (!tileOn.equals(".")) {//if we are not in one of these tiles, then remove the projectile
						projectiles.remove(i);
					}
				}
			}
		}
		
		if( !rangeAbilityTimerSwirl.isOnCooldown() )
		{
			float [][] swirlDirections = {
					{-1,0},
					{-1,1},
					{0,-1},
					{0,1},
					{1,0},
					{1,-1},
					{-1,-1},
					{1,1}
			};
			for (int i = 0; i < swirlDirections.length; i++) {
				float xDir = swirlDirections[i][0];
				float yDir = swirlDirections[i][1];
				Projectile p = new BossSlowProjectile(this.xPos,this.yPos,xDir*.1f,yDir*.1f);
				projectiles.add(p);
			}
			rangeAbilityTimerSwirl.setOnCooldown( true );
		}
		if (!rangeAbilityTimerAim.isOnCooldown() && phase >= 1) {
			
			float dx = character.xPos - xPos; //get difference in x position
			float dy = character.yPos - yPos; //get difference in y position
			float [] direction = {dx, dy}; //turn that into a vector
			float magnitude = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); //get the magnitude of the vector
			if (magnitude > 0) { //if magnitude == 0, we are not moving, so we can ignore
				direction[0] = (dx / magnitude) ; //normalize the vector
				direction[1] = (dy / magnitude) ; //same but for y
			}
			Projectile p = new BossBigProjectile(this.xPos,this.yPos,direction[0]*.1f,direction[1]*.1f);
			projectiles.add(p);
			rangeAbilityTimerAim.setOnCooldown(true);
		}
		if (!spawnAbilityTimer.isOnCooldown() && phase >= 2) {
			Entity e = new ZippyEnemy( xPos , yPos  );
			Game.pendingEnemies.add( e ); //don't add directly to enemies bc that might give us a ConcurrentModificationException
			spawnAbilityTimer.setOnCooldown(true);
		}
	}
	
	@Override
	public void render( GraphicsContext gc )
	{
		super.render(gc);
		//Render all bullets that have been fired
		for( int i = 0; i < projectiles.size(); i++ )
		{
			Projectile p = projectiles.get( i );
			
			if( p != null )
			{
				p.render( gc );
			}
			
		}
	};
	
}
