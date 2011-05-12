package apda;

import java.util.LinkedList;
import java.util.List;

import backbone.Attribute;
import backbone.DoubleAttribute;
import backbone.Grouping;
import backbone.StringAttribute;
import backbone.Unit;

public class Debater implements Unit {

	private Tourney _t;
	private String _name;
	private double[] _speaks;
	private double[] _ranks;
	
	public Debater(Tourney t, String name){
		_t = t;
		_name = name;
		_speaks = new double[t._totalRounds];
		_ranks = new double[t._totalRounds];
	}
	
	public void addSpeaks(double speaks, int roundNum){
		if(roundNum <= _speaks.length && roundNum >= 0)
			_speaks[roundNum] = speaks;
	}
	
	public void addRanks(double ranks, int roundNum){
		if(roundNum <= _ranks.length && roundNum >= 0)
			_ranks[roundNum] = ranks;
	}
	
	public double getSpeaks(){
		double speaks = 0.0;
		int toAvg = 0;
		for(double d : this._speaks){
			if(d < 0){
				toAvg += 1;
			}else speaks += d;
		}
		speaks += (speaks / this._speaks.length) * toAvg;
		return speaks;
	}
	public double getRanks(){
		double ranks = 0.0;
		int toAvg = 0;
		for(double d : this._ranks){
			if(d < 0){
				toAvg += 1;
			}else ranks += d;
		}
		ranks += (ranks / this._ranks.length) * toAvg;
		return ranks;
	}
	@Override
	public boolean deleteFromGrouping() {
		return false;
	}

	@Override
	public List<Attribute> getAttributes() {
		LinkedList<Attribute> atts = new LinkedList<Attribute>();
		atts.add(new StringAttribute("Name", _name));
		atts.add(new DoubleAttribute("Total Speaks", getSpeaks()));
		atts.add(new DoubleAttribute("Total Ranks", getRanks()));
		return atts;
	}

	//NEEDN'T BE A MEMBER OF ANYTHING
	@Override
	public Grouping getMemberOf() {
		DebaterGrouping debs = new DebaterGrouping(_t, "debs");
		debs.addMember(this);
		return debs;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if(attribute.getTitle().equals("Name ")){
			_name = ((StringAttribute)attribute).value;
		}
	}

	@Override
	public void setMemberOf(Grouping<Unit> g) {
	}

	@Override
	public void setName(String name) {
		_name = name;

	}

}
