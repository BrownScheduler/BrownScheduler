package basic;

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

public class DefaultRound extends backbone.Round{
	
	private int _roundNum;
	
	public DefaultRound(int i) {
		super(i);
		_roundNum = i;
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
		return _roundNum;
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

}