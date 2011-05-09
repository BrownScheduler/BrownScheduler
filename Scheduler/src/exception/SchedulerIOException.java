package exception;

public class SchedulerIOException extends SchedulerException {
	
	public SchedulerIOException() {
		super();
	}

	public SchedulerIOException(String s) {
		super(s);
	}
	
	public SchedulerIOException(Exception e) {
		super(e);
	}
}
