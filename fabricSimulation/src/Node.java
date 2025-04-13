
public class Node {
	private Vector p;
	private Vector velocity;
	private Vector acceleration;
	private double mass;
	private Boolean movable;
	private Boolean curMovable;
	private Boolean linked;

	public Node(Vector p) {
		super();
		this.p = p;
		this.velocity = new Vector();
		this.acceleration = new Vector();
		this.movable = true;
		this.curMovable = this.movable;
		this.mass=0.2;
		this.linked = true;
	}
	public Node() {
		super();
		this.p = new Vector();
		this.velocity = new Vector();
		this.acceleration = new Vector();
		this.movable = true;
		this.curMovable = this.movable;
		this.mass=0.3;
		this.linked = true;
	}
	
	

	public Vector getP() {
		return p;
	}
	public void setP(Vector p) {
		this.p = p;
	}
	public Vector getVelocity() {
		return velocity;
	}
	public Vector setVelocity(Vector velocity) {
		return this.velocity = velocity;
	}
	public Vector getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}

	public Boolean getMovable() {
		return movable;
	}

	public void setMovable(Boolean movable) {
		this.movable = movable;
	}
	
	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public void applyForce(Vector force) {
	    if (!this.movable || !this.curMovable) return;
	    this.acceleration.add(Vector.div(force, this.mass));
	}

	
	// Euler Integration
	public void integrate(double dt) {
	    if (!this.movable || !this.curMovable) return;

	    // Update velocity: v += a * dt
	    Vector scaledAcceleration = Vector.mult(this.acceleration, dt);
	    this.velocity.add(scaledAcceleration);

	    // Update position: p += v * dt
	    Vector scaledVelocity = Vector.mult(this.velocity, dt);
	    this.p.add(scaledVelocity);

	    // Reset acceleration for the next frame
	    this.acceleration.mult(0);
	}


	public Boolean getLinked() {
		return linked;
	}

	public void setLinked(Boolean linked) {
		this.linked = linked;
	}

	public Boolean getCurMovable() {
		return curMovable;
	}

	public void setCurMovable(Boolean curMovable) {
		this.curMovable = curMovable;
	}


}
