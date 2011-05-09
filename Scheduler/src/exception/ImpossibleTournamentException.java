package exception;

public class ImpossibleTournamentException extends SchedulerException {
	
	public ImpossibleTournamentException() {
		super();
	}

	public ImpossibleTournamentException(String s) {
		super(s);
	}
	
	public ImpossibleTournamentException(Exception e) {
		super(e);
	}
}
