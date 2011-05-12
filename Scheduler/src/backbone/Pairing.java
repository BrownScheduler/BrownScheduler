package backbone;

public interface Pairing extends Unit {
	
	/**
	 * Gets the message explaining the current problem with the pairing
	 * @return the message
	 */
	public String getConflictMessage();
	
	/**
	 * Gets the magnitude of the current problem
	 * @return a value between -1 and 1. -1 being a huge problem and 1 being a huge asset
	 */
	public double getConflictScore();
}
