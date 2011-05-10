package exception;

public class MissingInputInfoException extends MissingInfoException {
	
	public MissingInputInfoException() {
		super();
	}

	public MissingInputInfoException(String s) {
		super(s);
	}
	
	public MissingInputInfoException(Exception e) {
		super(e);
	}
}
