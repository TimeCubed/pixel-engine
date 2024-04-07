package pixelengine.util;

@SuppressWarnings("unused")
public class Vector2 implements Cloneable {
	private double x, y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public void normalize() {
		// No infamous fast inverse square root algorithm here
		// Just pure unoptimized code
		
		double length = Math.sqrt(this.x * this.x + this.y * this.y);
		
		this.x /= length;
		this.y /= length;
	}
	
	@Override
	public Vector2 clone() throws AssertionError {
		try {
			return (Vector2) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("how??");
		}
	}
}