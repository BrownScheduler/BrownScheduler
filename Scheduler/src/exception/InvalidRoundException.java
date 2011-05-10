package exception;

public class InvalidRoundException extends InvalidStateException {
	
	public InvalidRoundException() {
		super();
	}

	public InvalidRoundException(String s) {
		super(s);
	}
	
	public InvalidRoundException(Exception e) {
		super(e);
	}
}
