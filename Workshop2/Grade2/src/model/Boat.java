package model;

/**
 * This is information class for boat
 */
public class Boat {

	private double length;
	private BoatType type;

	public enum BoatType {Sailboat(1), Motorsailer(2), Kayak(3), Other(4);
	private int selectCode;

	private BoatType(int in) {
			this.selectCode = in;
		}

		public int getCode() {
			return this.selectCode;
		}
	}


	public Boat(double length, BoatType type) {
		setLength(length);
		this.type = type;
	}

	//Getters and setters follows:
	public void setLength(double length) {
		if(length <= 0.0)
			throw new IllegalArgumentException();
		this.length = length;
	}

	public void setType(BoatType type) {
		this.type = type;
	}

	public double getLength() {
		return length;
	}

	public BoatType getType() {
		return type;
	}
}
