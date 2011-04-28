package backbone;

public abstract class Attribute extends Gruttribute{

	//attributes holding null need to be handled well
	public Object att;

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
		return false;
	}
}
