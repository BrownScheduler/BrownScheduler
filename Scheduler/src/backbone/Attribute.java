package backbone;

public abstract class Attribute extends Gruttribute{
	private String title;

	private float conflictMagnitude();

	public Attribute(String title) {
		this.title = title;
	}

	public boolean isEditable() {
		return !(this instanceof NotEditable);
	}

	public enum Type {
		INT, STRING, BOOLEAN, DOUBLE, MEMBER, GROUPING
	}

	public abstract Type getType();

	public boolean isConflict() {
		return conflict;
	}

	public String getTitle() {
		return title;
	}
}
