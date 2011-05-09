package exception;

public class MissingManagementInfoException extends MissingInfoException {
	
	public MissingManagementInfoException() {
		super();
	}

	public MissingManagementInfoException(String s) {
		super(s);
	}
	
	public MissingManagementInfoException(Exception e) {
		super(e);
	}
}
