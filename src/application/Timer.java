package application;

public class Timer 
{
	private double timeInterval;
	private double elapsedTime;
	
	private long startTime;
	private long currentTime;
	
	private boolean onCooldown;
	

	/**************************
	        CONSTRUCTOR
	 **************************/
	public Timer( double intervalInSeconds )
	{
		this.timeInterval = intervalInSeconds * 1000;
		
		onCooldown = false;
		startTime = System.currentTimeMillis();
		currentTime = startTime;
		elapsedTime = 0;
	}
	
	
	/**************************
    	FUNCTIONS
	 **************************/
	public void tick()
	{
		//Only check elapsed time if the timer is on cooldown
		if( onCooldown )
		{
			currentTime = System.currentTimeMillis();
			elapsedTime = currentTime - startTime;
			
			if( elapsedTime > timeInterval )
			{
				onCooldown = false;
				startTime = System.currentTimeMillis();
				currentTime = startTime;
				
				//System.out.println( elapsedTime / 1000 );
			}
		}
	}
	
	public void reset()
	{
		startTime = System.currentTimeMillis();
		currentTime = System.currentTimeMillis();
		
	}
	
	
	
	
	
	/**************************
	     GETTERS / SETTERS 
	 **************************/


	public double getTimeInterval() {
		return timeInterval;
	}


	public void setTimeInterval(double timeInterval) {
		this.timeInterval = timeInterval;
	}


	public double getElapsedTime() {
		return elapsedTime;
	}


	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}


	public long getStartTime() {
		return startTime;
	}


	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


	public long getCurrentTime() {
		return currentTime;
	}


	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}


	public boolean isOnCooldown() {
		return onCooldown;
	}


	public void setOnCooldown(boolean onCooldown) {
		this.onCooldown = onCooldown;
	}
	

}
