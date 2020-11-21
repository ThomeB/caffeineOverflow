package application;


public class Projectile extends GameObject
{
	public static final float DEFAULT_PROJECTILE_WIDTH = 15;
	public static final float DEFAULT_PROJECTILE_HEIGHT = 10;
	public static final float DEFAULT_PROJECTILE_VELOCITY = 0.2f;
	public static final int DEFAULT_PROJECTILE_DAMAGE = 5;

	
	protected float maxVelocity;
	protected float xTarget;
	protected float yTarget;
	protected float xVelocity;
	protected float yVelocity;
	protected float xMove;
	protected float yMove;
	
	protected int damage;


	public Projectile(float xpos, float ypos, float xTarget, float yTarget ) 
	{
		super(xpos, ypos, DEFAULT_PROJECTILE_WIDTH, DEFAULT_PROJECTILE_HEIGHT, Asset.bullet);
		
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		maxVelocity = DEFAULT_PROJECTILE_VELOCITY;
		damage = DEFAULT_PROJECTILE_DAMAGE;
		
		findVelocity();
		
	}
	
	
	public void update()
	{
		
		xPos += xMove;
		yPos += yMove;
		
	}
	
	public void move()
	{
		
	}
	
	public void moveInput()
	{
		
	}
	
	public void findVelocity()
	{
		float maxMovement = 1.0f;
		float xDistanceFromTarget = Math.abs( xTarget - xPos * Tile.TILEWIDTH);
		float yDistanceFromTarget = Math.abs( yTarget - yPos * Tile.TILEHEIGHT);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		
		xVelocity = xPercentOfMovement;
		yVelocity = maxMovement - xPercentOfMovement;
		
		if( xTarget < xPos * Tile.TILEWIDTH )
		{
			xVelocity *= -1;
			System.out.println("hi");
		}
		if( yTarget < yPos * Tile.TILEHEIGHT )
		{
			yVelocity *= -1;
			System.out.println("hey");
		}
		
		xMove = maxVelocity * xVelocity;
		yMove = maxVelocity * yVelocity;
	}
	
	

}
