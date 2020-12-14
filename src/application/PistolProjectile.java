package application;

public class PistolProjectile extends Projectile
{
	public static final float PISTOL_PROJECTILE_WIDTH = 20;
	public static final float PISTOL_PROJECTILE_HEIGHT = 20;
	public static final int PISTOL_PROJECTILE_DAMAGE = 8;

	public PistolProjectile(float xpos, float ypos, float xVelocity, float yVelocity ) 
	{
		super(xpos, ypos, xVelocity, yVelocity, PISTOL_PROJECTILE_WIDTH, PISTOL_PROJECTILE_HEIGHT, Asset.bullet2, PISTOL_PROJECTILE_DAMAGE);
		
	}

}
