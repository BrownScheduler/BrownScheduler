package exception;

public class InvalidStateException extends SchedulerException {
	
	public InvalidStateException() {
		super();
	}

	public InvalidStateException(String s) {
		super(s);
	}
	
	public InvalidStateException(Exception e) {
		super(e);
	}
}
