package application;

public class ShotgunProjectile extends Projectile
{
	public static final float SHOTGUN_PROJECTILE_WIDTH = 40;
	public static final float SHOTGUN_PROJECTILE_HEIGHT = 40;
	public static final int SHOTGUN_PROJECTILE_DAMAGE = 8;

	public ShotgunProjectile(float xpos, float ypos, float xVelocity, float yVelocity ) 
	{
		super(xpos, ypos, xVelocity, yVelocity, SHOTGUN_PROJECTILE_WIDTH, SHOTGUN_PROJECTILE_HEIGHT, Asset.bullet, SHOTGUN_PROJECTILE_DAMAGE);
		
	}

}
