public class Point {

	private Point nNeighbor;
	private Point wNeighbor;
	private Point eNeighbor;
	private Point sNeighbor;
	private float nVel;
	private float eVel;
	private float wVel;
	private float sVel;
	private float pressure;
	private int sinInput;

	private static Integer[] types = {0, 1, 2};
	private int type;

	public Point() {
		clear();
		type = 0;
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		pressure = 0;
		nVel = 0;
		sVel = 0;
		wVel = 0;
		eVel = 0;
		// not sure if needed
		type = 0;
	}

	// Va(x,t + 1) = Va(x,t) − (P(x + dxa,t) − P(x,t))
	public void updateVelocity() {
		if (type == 0)
		{
			nVel = nVel - (nNeighbor.pressure - pressure);
			eVel = eVel - (eNeighbor.pressure - pressure);
			sVel = sVel - (sNeighbor.pressure - pressure);
			wVel = wVel - (wNeighbor.pressure - pressure);
		}
	}

	// P(x,t + 1) = P(x,t) − c^2 * ∑ a ( Va(x,t + 1) )
	// assumption c^2 = 0.5
	public void updatePressure() {
		if (type == 0)
			pressure = (float) (pressure - 0.5 * (nVel + eVel + sVel + wVel));
		if (type == 2)
		{
			double radians = Math.toRadians(sinInput);
			pressure = (float) (Math.sin(radians));
			// and update sinInput
			sinInput = (sinInput + 30) % 360;
		}
	}

	public float getPressure() {
		return pressure;
	}

	public static Integer[] getTypes() {
		return types;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setNeighbors(Point[][] points, int x, int y)
	{
		points[x][y].nNeighbor = points[x][y + 1];
		points[x][y].eNeighbor = points[x + 1][y];
		points[x][y].sNeighbor = points[x][y - 1];
		points[x][y].wNeighbor = points[x - 1][y];
	}
}