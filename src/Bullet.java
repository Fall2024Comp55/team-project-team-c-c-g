import acm.graphics.GImage;

public class Bullet {
	private GImage bulletIcon;
	private Position bulletPosition; 
	private Position clickedLocation;
	
	public Bullet (GImage bulletIcon, Position startPosition, Position targetPosition) {
		this.bulletIcon = bulletIcon;
		this.bulletPosition = startPosition;
		this.clickedLocation = targetPosition;
		
	}
	public void bulletMovementPath(int X, int Y) {
		double differenceX = clickedLocation.getX() - bulletPosition.getX();
		double differenceY = clickedLocation.getY() - bulletPosition.getY();
		
		double distance = Math.sqrt(differenceX * differenceX + differenceY * differenceY);
	
	}
	
	public void targetLocation(Position position) {
		
	}
	
}
