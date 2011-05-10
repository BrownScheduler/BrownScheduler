package exception;

public class CSVException extends SchedulerIOException {
	
	public CSVException() {
		super();
	}

	public CSVException(String s) {
		super(s);
	}
	
	public CSVException(Exception e) {
		super(e);
	}
}
