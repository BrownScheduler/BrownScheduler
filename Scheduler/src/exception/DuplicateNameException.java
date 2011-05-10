package exception;

public class DuplicateNameException extends InvalidInfoException {
	
	public DuplicateNameException() {
		super();
	}

	public DuplicateNameException(String s) {
		super(s);
	}
	
	public DuplicateNameException(Exception e) {
		super(e);
	}
}
