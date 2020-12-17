package application;

public class BossSlowProjectile extends Projectile
{
	public static final float BOSS_SLOW_PROJECTILE_WIDTH = 50;
	public static final float BOSS_SLOW_PROJECTILE_HEIGHT = 50;
	public static final int BOSS_SLOW_PROJECTILE_DAMAGE = 25;

	public BossSlowProjectile(float xpos, float ypos, float xVelocity, float yVelocity ) 
	{
		super(xpos, ypos, xVelocity, yVelocity, BOSS_SLOW_PROJECTILE_WIDTH, BOSS_SLOW_PROJECTILE_HEIGHT, Asset.bullet, BOSS_SLOW_PROJECTILE_DAMAGE);
		
	}

}
