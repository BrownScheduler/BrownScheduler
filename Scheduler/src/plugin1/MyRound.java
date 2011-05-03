package plugin1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import old_backbone.Grouping;
import old_backbone.Pairing;


/**
 * This class is a Grouping of pairings.
 * 
 * @author matt
 *
 */
public class MyRound extends old_backbone.Round{
	
	private int _roundNum;
	
	public MyRound(int i) {
		super(i);
		_roundNum = i;
	}
	
	public boolean isFinished(){
		for(old_backbone.Pairing p : this.pairings){
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
