
public class Position {
	double x;
	double y;
	
	public Position(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Position() {
		super();
		this.x = 0;
		this.y = 0;
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getDistance(Position p) {
        double xDistance = this.x - p.x;
        double yDistance = this.y - p.y;
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    public double getAngle(Position p) {
        double xDistance = p.x - this.x;
        double yDistance = p.y - this.y;
        return Math.atan2(yDistance, xDistance);
    }
}
