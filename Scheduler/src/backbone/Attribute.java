package backbone;

public abstract class Attribute extends Gruttribute{
	private String title;

	protected float conflictMagnitude;

	public Attribute(String title) {
		this.title = title;
		conflictMagnitude = 0;
	}

	public boolean isEditable() {
		return !(this instanceof NotEditable);
	}

	public enum Type {
		INT, STRING, BOOLEAN, DOUBLE, MEMBER, GROUPING
	}

	public abstract Type getType();

	public String getTitle() {
		return title;
	}

	public float getConflictMagnitude() {
		return conflictMagnitude;
	}
}
