package plugin1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import backbone.Grouping;
import backbone.Pairing;

/**
 * This class is a Grouping of pairings.
 * 
 * @author matt
 *
 */

public class MyRound extends backbone.Round{
	
	
	private Tourney _t;
	public MyRound(Tourney t, String i) {
		super(i);
		_t = t;
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
