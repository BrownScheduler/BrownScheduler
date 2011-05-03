package backbone;

import java.io.Serializable;

public abstract class Attribute implements Serializable {
	private String title;

	protected float conflictMagnitude;

	public Attribute(String title) {
		this.title = title;
		conflictMagnitude = 0;
	}

	public boolean isEditable() {
		return true;
	}

	public enum Type {
		INT, STRING, BOOLEAN, DOUBLE, MEMBER, GROUPING, UNIT
	}

	public abstract Type getType();

	public String getTitle() {
		return title;
	}

	public float getConflictMagnitude() {
		return conflictMagnitude;
	}
}
