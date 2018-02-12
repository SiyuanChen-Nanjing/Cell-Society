package edges;

public class ToroidalEdge implements Edge {

	@Override
	public int calculateNewCoordinate(int coordinate, int change, int length) {
		return (coordinate + change + length) % length;
	}

}
