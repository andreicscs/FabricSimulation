public class Vector {
    private Position position;
    
    public Vector(double x, double y) {
        this.position = new Position(x, y);
    }

    public Vector() {
        this.position = new Position();
    }
    
    public double getX() {
        return position.getX();
    }

    public void setX(double x) {
        position.setX(x);
    }

    public double getY() {
        return position.getY();
    }

    public void setY(double y) {
        position.setY(y);
    }
    
    // Operations between vectors

    public void add(Vector b) {
        this.setX(this.getX() + b.getX());
        this.setY(this.getY() + b.getY());
    }

    public void add(double b) {
        this.setX(this.getX() + b);
        this.setY(this.getY() + b);
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Vector add(Vector a, double b) {
        return new Vector(a.getX() + b, a.getY() + b);
    }

    public void sub(Vector b) {
        this.setX(this.getX() - b.getX());
        this.setY(this.getY() - b.getY());
    }

    public void sub(double b) {
        this.setX(this.getX() - b);
        this.setY(this.getY() - b);
    }

    public static Vector sub(Vector a, Vector b) {
        return new Vector(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static Vector sub(Vector a, double b) {
        return new Vector(a.getX() - b, a.getY() - b);
    }

    public void mult(Vector b) {
        this.setX(this.getX() * b.getX());
        this.setY(this.getY() * b.getY());
    }

    public void mult(double b) {
        this.setX(this.getX() * b);
        this.setY(this.getY() * b);
    }

    public static Vector mult(Vector a, Vector b) {
        return new Vector(a.getX() * b.getX(), a.getY() * b.getY());
    }

    public static Vector mult(Vector a, double b) {
        return new Vector(a.getX() * b, a.getY() * b);
    }

    public void div(Vector b) {
        this.setX(this.getX() / b.getX());
        this.setY(this.getY() / b.getY());
    }

    public void div(double b) {
        this.setX(this.getX() / b);
        this.setY(this.getY() / b);
    }

    public static Vector div(Vector a, Vector b) {
        return new Vector(a.getX() / b.getX(), a.getY() / b.getY());
    }

    public static Vector div(Vector a, double b) {
        return new Vector(a.getX() / b, a.getY() / b);
    }
    
    // Decreases the magnitude by a percentage passed as a parameter
    public void perc(int p) {
        double factor = p / 100.0;
        this.setX(this.getX() * factor);
        this.setY(this.getY() * factor);
    }
    
    // Copies another vector
    public void copy(Vector toCopy) {
        this.setX(toCopy.getX());
        this.setY(toCopy.getY());
    }
    
    // Clones itself
    public Vector clone() {
        return new Vector(this.getX(), this.getY());
    }
    
    // Negates the vector
    public void neg() {
        this.setX(-this.getX());
        this.setY(-this.getY());
    }
    
    // Static method to negate a vector
    public static Vector neg(Vector a) {
        return new Vector(-a.getX(), -a.getY());
    }
    
    // Limits the magnitude of the vector
    public void limit(double lim) {
        if (this.getMag() > lim) {
            this.setMag(lim);
        }
    }
    
    // Calculates the direction the vector would have if it pointed towards the parameter passed
    public double points(Vector pos2) {
        return position.getAngle(pos2.position);
    }

    public double points(double x, double y) {
        return position.getAngle(new Position(x, y));
    }
    
    // Returns the direction of the vector
    public double points() {
        return position.getAngle(new Position(0, 0));
    }
    
    // Sets the magnitude
    public void setMag(double magnitude) {
        double angle = this.points();
        this.setX(Math.cos(angle) * magnitude);
        this.setY(Math.sin(angle) * magnitude);
    }

    // Static method to set the magnitude
    public static Vector setMag(Vector V, double magnitude) {
        double angle = V.points();
        return new Vector(Math.cos(angle) * magnitude, Math.sin(angle) * magnitude);
    }

    public double getDir() {
        return Math.toDegrees(Math.atan2(this.getY(), this.getX()));
    }

    // Sets the direction
    public void setDir(double angle) {
        double mag = this.getMag();
        this.setX(Math.cos(angle) * mag);
        this.setY(Math.sin(angle) * mag);
    }
    
    // Sets both direction and magnitude
    public void set(double angle, double magnitude) {
        this.setX(Math.cos(angle) * magnitude);
        this.setY(Math.sin(angle) * magnitude);
    }
    
    // Returns the magnitude
    public double getMag() {
        return position.getDistance(new Position(0, 0));
    }
    
    // Distance squared (without sqrt for performance)
    public static double distanceSQ(Vector a, Vector b) {
        double distanceX = b.getX() - a.getX();
        double distanceY = b.getY() - a.getY();
        return distanceX * distanceX + distanceY * distanceY;
    }
    
    public double distanceSQ(Vector b) {
        double distanceX = b.getX() - this.getX();
        double distanceY = b.getY() - this.getY();
        return distanceX * distanceX + distanceY * distanceY;
    }
    
    // Distance between two vectors
    public double distance(Vector b) {
        return position.getDistance(b.position);
    }

    public static double distance(Vector a, Vector b) {
        return a.position.getDistance(b.position);
    }
    
    // Dot product
    public static double dot(Vector a, Vector b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    // Normalize the vector
    public void normalize() {
        double mag = getMag();
        if (mag > 0) {
            this.setX(this.getX() / mag);
            this.setY(this.getY() / mag);
        }
    }

    public static Vector normalize(Vector V) {
        double mag = V.getMag();
        if (mag > 0) {
            return new Vector(V.getX() / mag, V.getY() / mag);
        }
        return V;
    }
}
