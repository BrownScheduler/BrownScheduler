package apda;

import backbone.Pairing;

/**
 * This class is a Grouping of pairings.
 * 
 * @author matt
 *
 */

public class MyRound extends backbone.Round{
	
	
	private Tourney _t;
	public final int roundNum;
	public MyRound(Tourney t, String i, int roundNumber) {
		super(i);
		_t = t;
		roundNum = roundNumber;
	}
	
	public boolean isFinished(){

		for(backbone.Pairing p : this.pairings){
			if(((MyPairing) p).isFinished()){
				return true;
			}
		}
		return false;
	}
	
	public int getRoundNum(){
		return 0;
	}
	
	public void addPairing(MyPairing p){
		this.pairings.add(p);
	}
	
	
	@Override
	public String toString(){
		
		String r = "";
		for(Pairing p : this.pairings){
			r = r + ((MyPairing) p).toString();
			r = r + "\n";
		}
		return r;
	}
	
	@Override
	public Pairing getBlank(){
		return new MyPairing(_t);
		
	}

}
