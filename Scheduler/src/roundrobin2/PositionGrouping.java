package roundrobin2;

public class PositionGrouping extends MyGrouping<Position>{

	public PositionGrouping(String name) {
		super(name);
		this._members.add(new Position("Forward", "Forward"));
		this._members.add(new Position("Midfield", "Midfield"));
		this._members.add(new Position("Defender", "Defender"));
		this._members.add(new Position("Goalie", "Goalie"));
	}

	@Override
	public Position getBlank() {
		// TODO Auto-generated method stub
		return new Position();
	}

}
