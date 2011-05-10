package exception;

public class SchedulerException extends Exception {
	
	public SchedulerException() {
		super();
	}

	public SchedulerException(String s) {
		super(s);
	}
	
	public SchedulerException(Exception e) {
		super(e);
	}
}
