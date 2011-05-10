package exception;

public class BackupException extends SchedulerIOException {
	
	public BackupException() {
		super();
	}

	public BackupException(String s) {
		super(s);
	}
	
	public BackupException(Exception e) {
		super(e);
	}
}
