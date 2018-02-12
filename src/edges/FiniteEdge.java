package edges;

public class FiniteEdge implements Edge {

	@Override
	public int calculateNewCoordinate(int coordinate, int change, int length) {
		if (coordinate + change >= 0 && coordinate + change < length) {
            return (coordinate + change);
        }
        return -1;

	}

}
