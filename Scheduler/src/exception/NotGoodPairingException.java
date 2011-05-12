package exception;

public class NotGoodPairingException extends SchedulerException {

	public NotGoodPairingException() {
		super();
	}

	public NotGoodPairingException(String s) {
		super(s);
	}
	
	public NotGoodPairingException(Exception e) {
		super(e);
	}
}
