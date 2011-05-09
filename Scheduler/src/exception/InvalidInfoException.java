package exception;

public class InvalidInfoException extends InvalidStateException {
	
	public InvalidInfoException() {
		super();
	}

	public InvalidInfoException(String s) {
		super(s);
	}
	
	public InvalidInfoException(Exception e) {
		super(e);
	}
}
