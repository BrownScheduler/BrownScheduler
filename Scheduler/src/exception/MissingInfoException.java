package exception;

public class MissingInfoException extends InvalidInfoException {
	
	public MissingInfoException() {
		super();
	}

	public MissingInfoException(String s) {
		super(s);
	}
	
	public MissingInfoException(Exception e) {
		super(e);
	}
}
